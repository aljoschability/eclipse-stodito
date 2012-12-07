package com.aljoschability.eclipse.stodito.interpreter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.ActivityFinalNode;
import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.CallNode;
import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.GuardType;
import com.aljoschability.eclipse.stodito.InitialNode;
import com.aljoschability.eclipse.stodito.JunctionNode;
import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.PatternPart;
import com.aljoschability.eclipse.stodito.StatementNode;
import com.aljoschability.eclipse.stodito.StoryNode;
import com.aljoschability.eclipse.stodito.StructuredNode;
import com.aljoschability.eclipse.stodito.evaluator.Evaluator;
import com.aljoschability.eclipse.stodito.evaluator.Step;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.evaluator.impl.VariableImpl;
import com.aljoschability.eclipse.stodito.interpreter.AbortInterpretationException;
import com.aljoschability.eclipse.stodito.interpreter.AbstractNotifier;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationType;
import com.aljoschability.eclipse.stodito.interpreter.ExpressionEvaluator;
import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;
import com.aljoschability.eclipse.stodito.interpreter.Matcher;
import com.aljoschability.eclipse.stodito.interpreter.Scope;
import com.aljoschability.eclipse.stodito.interpreter.strategies.DefaultMatchingStrategyWithLog;
import com.aljoschability.eclipse.stodito.interpreter.strategies.LogReproducingMatchingStrategy;

public class EvaluatorOLDImpl extends AbstractNotifier implements Evaluator {
	private Step current;

	private final Map<ActivityNode, Scope> scopes;
	private final Map<PatternPart, Matcher> matchers;
	private ActivityNode nextNode;
	private ClassLoader loader;
	private Activity activity;
	private Collection<VariableImpl> results;
	private boolean run;
	private Map<Parameter, Object> parameters;
	private VariableImpl result;

	public EvaluatorOLDImpl(ClassLoader loader) {
		super(new EventEmitterImpl());
		this.loader = loader;

		scopes = new LinkedHashMap<ActivityNode, Scope>();
		matchers = new LinkedHashMap<PatternPart, Matcher>();
	}

	@Override
	public void evaluate(EvaluationType type) throws InterpreterException {
		if (type != null) {
			switch (type) {
			case RUN:
				result = execute(activity, parameters);
				break;

			default:
				break;
			}
		}
	}

	private VariableImpl execute(Activity activity, Map<Parameter, Object> parameters) throws InterpreterException {
		getEmitter().start(activity);

		// activity scope
		Scope scope = new Scope(getEmitter());

		// parameters
		for (Parameter parameter : parameters.keySet()) {
			scope.createVariable(parameter.getName(), parameter.getType(), parameters.get(parameter));
		}

		nextNode = getInitialNode(activity);

		scopes.put(nextNode, scope);

		ActivityNode lastNode = null;
		ActivityNode n = null;

		while (nextNode != null) {
			lastNode = nextNode;
			nextNode = null;

			try {
				n = executeActivityNode(lastNode, scopes.get(lastNode));
			} catch (AbortInterpretationException e) {
				if (!equals(e.getTargetInterpreter())) {
					throw e;
				}
			}

			if (nextNode == null) {
				nextNode = n;
			} else {
				System.out.println("external manipulation");
			}
		}

		// store variables
		Collection<Variable> variables = new ArrayList<Variable>();
		for (VariableImpl variable : scope.getVariables()) {
			variables.add(VariableImpl.copy(variable));
		}

		// evaluate return expression
		ActivityFinalNode finalNode = null;
		for (ActivityNode node : activity.getNodes()) {
			if (node instanceof ActivityFinalNode) {
				finalNode = (ActivityFinalNode) node;
			}
		}

		if (finalNode != null) {
			result = evaluate(finalNode.getExpression(), scope);
		} else {
			result = null;
		}

		getEmitter().end(activity);

		return result;
	}

	private InitialNode getInitialNode(Activity activity) throws InterpreterException {
		for (ActivityNode node : activity.getNodes()) {
			if (node instanceof InitialNode) {
				return (InitialNode) node;
			}
		}
		throw new InterpreterException();
	}

	private ActivityNode executeActivityNode(ActivityNode node, Scope scope) throws InterpreterException {
		if (node instanceof CallNode) {
			return executeCallNode((CallNode) node, scope);
		}
		if (node instanceof StoryNode) {
			return executeStoryNode((StoryNode) node, scope);
		}
		if (node instanceof StatementNode) {
			return executeStatementNode((StatementNode) node, scope);
		}
		if (node instanceof StructuredNode) {
			return executeStructuredNode((StructuredNode) node, scope);
		}

		if (node instanceof InitialNode) {
			return executeInitialNode((InitialNode) node, scope);
		}
		if (node instanceof JunctionNode) {
			return executeJunctionNode((JunctionNode) node, scope);
		}
		if (node instanceof ActivityFinalNode) {
			return executeActivityFinalNode((ActivityFinalNode) node, scope);
		}

		throw new InterpreterException();
	}

	private ActivityNode executeCallNode(CallNode node, Scope scope) throws InterpreterException {
		// TODO Auto-generated method stub
		return null;
	}

	private ActivityNode executeStoryNode(StoryNode node, Scope scope) throws InterpreterException {
		if (node.isForEach()) {
			return executeForEachFreshMatchStoryNode(node, scope);
		} else {
			return executeSingleMatchStoryNode(node, scope);
		}
	}

	protected ActivityNode executeForEachFreshMatchStoryNode(StoryNode node, Scope variablesScope)
			throws InterpreterException {
		getEmitter().start(node);

		/*
		 * Check if there is already a story pattern matcher for this foreach node
		 */
		PatternPart storyPattern = node.getPattern();

		Matcher storyPatternMatcher = matchers.get(storyPattern);

		if (storyPatternMatcher == null) {
			/*
			 * There is none, create a new one.
			 */
			storyPatternMatcher = createPatternPartMatcher(storyPattern, variablesScope);
			matchers.put(storyPattern, storyPatternMatcher);

			/*
			 * For the first run, the pattern matcher has to use a matching strategy that produces a log.
			 */
			DefaultMatchingStrategyWithLog matchingStrategy = new DefaultMatchingStrategyWithLog();

			storyPatternMatcher.setMatchingStrategy(matchingStrategy);
		} else if (storyPatternMatcher.getMatchingStrategy() instanceof DefaultMatchingStrategyWithLog) {
			/*
			 * Replace the story pattern matcher's matching strategy to make sure that objects are matched in the same
			 * order in the following iterations. This is important to avoid finding the same match multiple times if
			 * the matching order changes, e.g., because the cardinality of the instance links have changed.
			 */
			storyPatternMatcher.setMatchingStrategy(new LogReproducingMatchingStrategy(
					((DefaultMatchingStrategyWithLog) storyPatternMatcher.getMatchingStrategy()).getLog()));
		}

		ActivityNode nextNode = node;

		ControlFlow nextEdge = null;
		int count = node.getOutgoings().size();
		if (count < 1 || count > 2) {
			throw new InterpreterException();
		}

		if (storyPatternMatcher.findNextMatch()) {
			storyPatternMatcher.applyMatch();

			getEmitter().end(node);

			for (ControlFlow edge : node.getOutgoings()) {
				if (GuardType.SUCCESS.equals(edge.getGuard())) {
					getEmitter().end(node);
					nextEdge = edge;
					break;
				}
			}

			if (nextEdge != null) {
				nextNode = executeControlFlow(nextEdge, variablesScope);
			}
		} else {
			getEmitter().end(node);

			for (ControlFlow edge : node.getOutgoings()) {
				if (GuardType.FAILURE.equals(edge.getGuard())) {
					getEmitter().end(node);
					nextEdge = edge;
					break;
				}
			}
			nextNode = executeControlFlow(nextEdge, variablesScope);

			/*
			 * All matches have been found for this story pattern, the pattern matcher is not necessary anymore.
			 */
			matchers.remove(storyPattern);
		}

		return nextNode;
	}

	protected ActivityNode executeSingleMatchStoryNode(StoryNode node, Scope variablesScope)
			throws InterpreterException {
		getEmitter().start(node);

		Matcher storyPatternMatcher = createPatternPartMatcher(node.getPattern(), variablesScope);

		boolean success = storyPatternMatcher.findNextMatch();

		if (success) {
			storyPatternMatcher.applyMatch();
		}

		int count = node.getOutgoings().size();
		if (count < 1 || count > 2) {
			throw new InterpreterException();
		}

		if (success) {
			for (ControlFlow edge : node.getOutgoings()) {
				if (GuardType.SUCCESS.equals(edge.getGuard())) {
					getEmitter().end(node);
					return executeControlFlow(edge, variablesScope);
				}
			}
		} else {
			for (ControlFlow edge : node.getOutgoings()) {
				if (GuardType.FAILURE.equals(edge.getGuard())) {
					getEmitter().end(node);
					return executeControlFlow(edge, variablesScope);
				}
			}
		}

		throw new InterpreterException();
	}

	private Matcher createPatternPartMatcher(PatternPart pattern, Scope scope) {
		return new Matcher(getEmitter(), pattern, scope);
	}

	private ActivityNode executeStatementNode(StatementNode node, Scope scope) throws InterpreterException {
		getEmitter().start(node);

		evaluate(node.getExpression(), scope);

		if (node.getOutgoings().size() == 1) {
			getEmitter().end(node);
			return executeControlFlow(node.getOutgoings().get(0), scope);
		} else if (!node.getOutgoings().isEmpty()) {
			// TODO: look for the other outgoing edges
			return executeControlFlow(node.getOutgoings().get(0), scope);
		}

		throw new InterpreterException();
	}

	private ActivityNode executeInitialNode(InitialNode node, Scope scope) throws InterpreterException {
		getEmitter().start(node);
		getEmitter().end(node);
		return executeControlFlow(node.getOutgoings().get(0), scope);
	}

	private ActivityNode executeJunctionNode(JunctionNode node, Scope scope) throws InterpreterException {
		getEmitter().start(node);

		// merge node
		if (node.getOutgoings().size() == 1) {
			ControlFlow edge = node.getOutgoings().get(0);
			if (!GuardType.NONE.equals(edge.getGuard())) {
				throw new InterpreterException();
			}
			getEmitter().end(node);
			return executeControlFlow(edge, scope);
		}

		// collect boolean expressions
		Map<ControlFlow, Expression> expressions = new LinkedHashMap<ControlFlow, Expression>();
		for (ControlFlow edge : node.getOutgoings()) {
			if (GuardType.BOOL.equals(edge.getGuard())) {
				expressions.put(edge, edge.getExpression());
			} else if (!GuardType.ELSE.equals(edge.getGuard())) {
				throw new InterpreterException();
			}
		}

		// take first successfully executed expression
		for (ControlFlow edge : expressions.keySet()) {
			VariableImpl result = evaluate(expressions.get(edge), scope);

			if (Boolean.TRUE.equals(result.getValue())) {
				getEmitter().end(node);
				return executeControlFlow(edge, scope);
			}
		}

		// take [ELSE] edge
		ControlFlow next = null;
		for (ControlFlow edge : node.getOutgoings()) {
			if (GuardType.ELSE.equals(edge.getGuard())) {
				if (next == null) {
					next = edge;
				} else {
					throw new InterpreterException();
				}
			}
		}

		if (next != null) {
			getEmitter().end(node);
			return executeControlFlow(next, scope);
		}
		throw new InterpreterException();
	}

	private ActivityNode executeControlFlow(ControlFlow edge, Scope scope) throws InterpreterException {
		getEmitter().traversing(edge);
		return edge.getTarget();
	}

	private VariableImpl evaluate(Expression expression, Scope scope) throws InterpreterException {
		return evaluate(expression, scope, null, null);
	}

	private VariableImpl evaluate(Expression expression, Scope scope, EClassifier type, Object value)
			throws InterpreterException {
		getEmitter().start(expression);

		VariableImpl result = ExpressionEvaluator.evaluate(expression, scope, type, value);

		getEmitter().is(expression, result);

		getEmitter().end(expression);
		return result;
	}

	private ActivityNode executeStructuredNode(StructuredNode node, Scope scope) throws InterpreterException {
		// TODO Auto-generated method stub
		return null;
	}

	private ActivityNode executeActivityFinalNode(ActivityFinalNode node, Scope scope) throws InterpreterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariableImpl getResult() {
		return result;
	}

	@Override
	public void initialize(Activity activity, Map<Parameter, Object> parameters) {
		this.activity = activity;
		this.parameters = parameters;
	}
}
