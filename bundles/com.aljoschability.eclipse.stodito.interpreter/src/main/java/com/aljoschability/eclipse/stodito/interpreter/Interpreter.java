package com.aljoschability.eclipse.stodito.interpreter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClassifier;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.CallNode;
import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.InitialNode;
import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.StatementNode;
import com.aljoschability.eclipse.stodito.StoryNode;
import com.aljoschability.eclipse.stodito.StructuredNode;

public class Interpreter extends AbstractNotifier {
	private final Map<ActivityNode, Scope> scopes;
	private final Map<Pattern, Matcher> matchers;
	private ActivityNode nextNode;

	public Interpreter(Emitter emitter) {
		super(null);
		// TODO: this class is destroyed
		// super(emitter);

		scopes = new LinkedHashMap<ActivityNode, Scope>();
		matchers = new LinkedHashMap<Pattern, Matcher>();
	}

	public Map<String, Variable> execute(Activity activity, Map<Parameter, Object> parameters)
			throws InterpreterException {
		// root scope
		Scope scope = new Scope(getEmitter());

		getEmitter().start(activity);

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
				if (!this.equals(e.getTargetInterpreter())) {
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
		Map<String, Variable> variables = new LinkedHashMap<String, Variable>();
		// for (Variable variable : scope.getVariables()) {
		// variables.put(variable.getKey(), Variable.copy(variable));
		// }

		// evaluate return expression
		System.out.println("return expression not evaluated");

		getEmitter().end(activity);

		return variables;
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
		// if (node instanceof DecisionNode) {
		// return executeDecisionNode((DecisionNode) node, scope);
		// }
		// if (node instanceof FinalNode) {
		// return executeFinalNode((FinalNode) node, scope);
		// }

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
		// Pattern storyPattern = node.getPattern();
		//
		// Matcher storyPatternMatcher = matchers.get(storyPattern);
		//
		// if (storyPatternMatcher == null) {
		// /*
		// * There is none, create a new one.
		// */
		// storyPatternMatcher = createStoryPatternMatcher(storyPattern, variablesScope);
		// matchers.put(storyPattern, storyPatternMatcher);
		//
		// /*
		// * For the first run, the pattern matcher has to use a matching strategy that produces a log.
		// */
		// DefaultMatchingStrategyWithLog matchingStrategy = new DefaultMatchingStrategyWithLog(facadeFactory);
		//
		// storyPatternMatcher.setMatchingStrategy(matchingStrategy);
		// } else if (storyPatternMatcher.getMatchingStrategy() instanceof DefaultMatchingStrategyWithLog) {
		// /*
		// * Replace the story pattern matcher's matching strategy to make sure that objects are matched in the same
		// * order in the following iterations. This is important to avoid finding the same match multiple times if
		// * the matching order changes, e.g., because the cardinality of the instance links have changed.
		// */
		// storyPatternMatcher.setMatchingStrategy(new LogReproducingMatchingStrategy(facadeFactory,
		// ((DefaultMatchingStrategyWithLog) storyPatternMatcher.getMatchingStrategy()).getLog()));
		// }
		//
		// ActivityNode nextNode = node;
		//
		// ActivityEdge nextEdge;
		// int count = node.getOutgoings().size();
		// if (count < 1 || count > 2) {
		// throw new InterpreterException();
		// }
		//
		// if (storyPatternMatcher.findNextMatch()) {
		// storyPatternMatcher.applyMatch();
		//
		// getEmitter().end(node);
		//
		// for (ActivityEdge edge : node.getOutgoings()) {
		// if (GuardType.SUCCESS.equals(edge.getGuard())) {
		// getEmitter().end(node);
		// nextEdge = edge;
		// break;
		// }
		// }
		//
		// if (nextEdge != null) {
		// nextNode = executeActivityEdge(nextEdge, variablesScope);
		// }
		// } else {
		// getEmitter().end(node);
		//
		// for (ActivityEdge edge : node.getOutgoings()) {
		// if (GuardType.FAILURE.equals(edge.getGuard())) {
		// getEmitter().end(node);
		// nextEdge = edge;
		// break;
		// }
		// }
		// nextNode = executeActivityEdge(nextEdge, variablesScope);
		//
		// /*
		// * All matches have been found for this story pattern, the pattern matcher is not necessary anymore.
		// */
		// matchers.remove(storyPattern);
		// }

		return nextNode;
	}

	protected ActivityNode executeSingleMatchStoryNode(StoryNode node, Scope variablesScope)
			throws InterpreterException {
		getEmitter().start(node);

		// Matcher storyPatternMatcher = createStoryPatternMatcher(node.getPattern(), variablesScope);
		//
		// boolean success = storyPatternMatcher.findNextMatch();
		//
		// if (success) {
		// storyPatternMatcher.applyMatch();
		// }
		//
		// int count = node.getOutgoings().size();
		// if (count < 1 || count > 2) {
		// throw new InterpreterException();
		// }
		//
		// if (success) {
		// for (ActivityEdge edge : node.getOutgoings()) {
		// if (GuardType.SUCCESS.equals(edge.getGuard())) {
		// getEmitter().end(node);
		// return executeActivityEdge(edge, variablesScope);
		// }
		// }
		// } else {
		// for (ActivityEdge edge : node.getOutgoings()) {
		// if (GuardType.FAILURE.equals(edge.getGuard())) {
		// getEmitter().end(node);
		// return executeActivityEdge(edge, variablesScope);
		// }
		// }
		// }

		throw new InterpreterException();
	}

	private Matcher createStoryPatternMatcher(Pattern pattern, Scope scope) {
		// return new Matcher(getEmitter(), pattern, scope);
		return null;
	}

	private ActivityNode executeStatementNode(StatementNode node, Scope scope) throws InterpreterException {
		getEmitter().start(node);

		evaluate(node.getExpression(), scope);

		if (node.getOutgoings().size() == 1) {
			getEmitter().end(node);
			// return executeActivityEdge(node.getOutgoings().get(0), scope);
		}

		throw new InterpreterException();
	}

	private ActivityNode executeInitialNode(InitialNode node, Scope scope) throws InterpreterException {
		getEmitter().start(node);
		getEmitter().end(node);
		// return executeActivityEdge(node.getOutgoings().get(0), scope);
		return null;
	}

	// private ActivityNode executeDecisionNode(DecisionNode node, Scope scope) throws InterpreterException {
	// getEmitter().start(node);
	//
	// // merge node
	// if (node.getOutgoings().size() == 1) {
	// ActivityEdge edge = node.getOutgoings().get(0);
	// if (!GuardType.NONE.equals(edge.getGuard())) {
	// throw new InterpreterException();
	// }
	// getEmitter().end(node);
	// return executeActivityEdge(edge, scope);
	// }
	//
	// // collect boolean expressions
	// Map<ActivityEdge, Expression> expressions = new LinkedHashMap<ActivityEdge, Expression>();
	// for (ActivityEdge edge : node.getOutgoings()) {
	// if (GuardType.BOOL.equals(edge.getGuard())) {
	// expressions.put(edge, edge.getExpression());
	// } else if (!GuardType.ELSE.equals(edge.getGuard())) {
	// throw new InterpreterException();
	// }
	// }
	//
	// // take first successfully executed expression
	// for (ActivityEdge edge : expressions.keySet()) {
	// Variable result = evaluate(expressions.get(edge), scope);
	//
	// if (Boolean.TRUE.equals(result.getValue())) {
	// getEmitter().end(node);
	// return executeActivityEdge(edge, scope);
	// }
	// }
	//
	// // take [ELSE] edge
	// ActivityEdge next = null;
	// for (ActivityEdge edge : node.getOutgoings()) {
	// if (GuardType.ELSE.equals(edge.getGuard())) {
	// if (next == null) {
	// next = edge;
	// } else {
	// throw new InterpreterException();
	// }
	// }
	// }
	//
	// if (next != null) {
	// getEmitter().end(node);
	// return executeActivityEdge(next, scope);
	// }
	// throw new InterpreterException();
	// }

	private ActivityNode executeActivityEdge(ControlFlow edge, Scope scope) throws InterpreterException {
		getEmitter().traversing(edge);
		return edge.getTarget();
	}

	private Variable evaluate(Expression expression, Scope scope) throws InterpreterException {
		return evaluate(expression, scope, null, null);
	}

	private Variable evaluate(Expression expression, Scope scope, EClassifier type, Object value)
			throws InterpreterException {
		getEmitter().start(expression);

		// Variable result = ExpressionEvaluator.evaluate(expression, scope, type, value);
		//
		// getEmitter().end(expression);
		// return result;
		return null;
	}

	private ActivityNode executeStructuredNode(StructuredNode node, Scope scope) throws InterpreterException {
		// TODO Auto-generated method stub
		return null;
	}

	// private ActivityNode executeFinalNode(FinalNode node, Scope scope) throws InterpreterException {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
