package com.aljoschability.eclipse.stodito.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

import com.aljoschability.eclipse.stodito.ArithmeticExpression;
import com.aljoschability.eclipse.stodito.ComparisonExpression;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.LiteralExpression;
import com.aljoschability.eclipse.stodito.LogicExpression;
import com.aljoschability.eclipse.stodito.ObjectAttributeExpression;
import com.aljoschability.eclipse.stodito.ObjectOccurrenceExpression;
import com.aljoschability.eclipse.stodito.OclExpression;
import com.aljoschability.eclipse.stodito.UnaryExpression;
import com.aljoschability.eclipse.stodito.evaluator.impl.VariableImpl;

public class ExpressionEvaluator {

	private static final List<EDataType> classifierSizeList = new ArrayList<EDataType>();

	static {
		classifierSizeList.add(EcorePackage.Literals.ESHORT);
		classifierSizeList.add(EcorePackage.Literals.ESHORT_OBJECT);
		classifierSizeList.add(EcorePackage.Literals.EBYTE);
		classifierSizeList.add(EcorePackage.Literals.EBYTE_OBJECT);
		classifierSizeList.add(EcorePackage.Literals.EINT);
		classifierSizeList.add(EcorePackage.Literals.EINTEGER_OBJECT);
		classifierSizeList.add(EcorePackage.Literals.EFLOAT);
		classifierSizeList.add(EcorePackage.Literals.EFLOAT_OBJECT);
		classifierSizeList.add(EcorePackage.Literals.ELONG);
		classifierSizeList.add(EcorePackage.Literals.ELONG_OBJECT);
		classifierSizeList.add(EcorePackage.Literals.EDOUBLE);
		classifierSizeList.add(EcorePackage.Literals.EDOUBLE_OBJECT);
	}

	public static VariableImpl evaluate(Expression expression, Scope scope, EClassifier type, Object value)
			throws InterpreterException {
		if (expression instanceof OclExpression) {
			throw new InterpreterException();
		}

		if (expression instanceof ArithmeticExpression) {
			return evaluate((ArithmeticExpression) expression, scope, type, value);
		}
		if (expression instanceof LogicExpression) {
			return evaluate((LogicExpression) expression, scope, type, value);
		}
		if (expression instanceof LiteralExpression) {
			return evaluate((LiteralExpression) expression, scope, type, value);
		}
		if (expression instanceof UnaryExpression) {
			return evaluate((UnaryExpression) expression, scope, type, value);
		}
		if (expression instanceof UnaryExpression) {
			return evaluate((UnaryExpression) expression, scope, type, value);
		}
		if (expression instanceof ComparisonExpression) {
			return evaluate((ComparisonExpression) expression, scope, type, value);
		}
		if (expression instanceof ObjectAttributeExpression) {
			return evaluate((ObjectAttributeExpression) expression, scope, type, value);
		}
		if (expression instanceof ObjectOccurrenceExpression) {
			return evaluate((ObjectOccurrenceExpression) expression, scope, type, value);
		}

		throw new InterpreterException();
	}

	private static VariableImpl evaluate(ArithmeticExpression expression, Scope scope, EClassifier type, Object value)
			throws InterpreterException {
		VariableImpl left = evaluate(expression.getLeft(), scope, type, value);
		VariableImpl right = evaluate(expression.getRight(), scope, type, value);

		BigDecimal operand1 = new BigDecimal(left.getValue().toString());
		BigDecimal operand2 = new BigDecimal(right.getValue().toString());

		BigDecimal result;

		switch (expression.getOperator()) {
		case PLUS:
			result = operand1.add(operand2);
			break;
		case MINUS:
			result = operand1.subtract(operand2);
			break;
		case TIMES:
			result = operand1.multiply(operand2);
			break;
		case DIVIDED:
			result = operand1.divide(operand2);
			break;
		case MODULO:
			result = operand1.remainder(operand2);
			break;
		case EXP:
			result = operand1.pow(operand2.intValueExact());
			break;
		default:
			throw new UnsupportedOperationException();
		}
		/*
		 * The return classifier is the largest of the two operand classifiers
		 */
		EClassifier classifier = classifierSizeList.get(Math.max(classifierSizeList.indexOf(left.getType()),
				classifierSizeList.indexOf(right.getType())));

		Object finalResult;

		if (classifier == EcorePackage.Literals.EBYTE || classifier == EcorePackage.Literals.EBYTE_OBJECT) {
			finalResult = result.byteValue();
		} else if (classifier == EcorePackage.Literals.EINT || classifier == EcorePackage.Literals.EINTEGER_OBJECT) {
			finalResult = result.intValue();
		} else if (classifier == EcorePackage.Literals.EDOUBLE || classifier == EcorePackage.Literals.EDOUBLE_OBJECT) {
			finalResult = result.doubleValue();
		} else if (classifier == EcorePackage.Literals.EFLOAT || classifier == EcorePackage.Literals.EFLOAT_OBJECT) {
			finalResult = result.floatValue();
		} else if (classifier == EcorePackage.Literals.ELONG || classifier == EcorePackage.Literals.ELONG_OBJECT) {
			finalResult = result.longValue();
		} else if (classifier == EcorePackage.Literals.ESHORT || classifier == EcorePackage.Literals.ESHORT_OBJECT) {
			finalResult = result.shortValue();
		} else {
			throw new UnsupportedOperationException();
		}

		return new VariableImpl(Constants.INTERNAL, classifier, finalResult);
	}

	private static VariableImpl evaluate(LogicExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) throws InterpreterException {

		VariableImpl leftVariable = evaluate(expression.getLeft(), variablesScope, contextClassifier, contextInstance);

		assert leftVariable != null;

		VariableImpl rightVariable = evaluate(expression.getRight(), variablesScope, contextClassifier, contextInstance);

		assert rightVariable != null;

		boolean operand1 = (Boolean) leftVariable.getValue();
		boolean operand2 = (Boolean) rightVariable.getValue();

		/*
		 * Compare values
		 */
		boolean result = false;

		switch (expression.getOperator()) {
		case AND: {
			result = operand1 && operand2;
			break;
		}
		case OR: {
			result = operand1 || operand2;
			break;
		}
		case EQUIVALENT: {
			result = operand1 && operand2 || !operand1 && !operand2;
			break;
		}
		case IMPLY: {
			result = operand1 && operand2 || !operand1;
			break;
		}
		case XOR: {
			result = operand1 && !operand2 || !operand1 && operand2;
			break;
		}
		default: {
			throw new UnsupportedOperationException();
		}
		}

		return new VariableImpl(Constants.INTERNAL, EcorePackage.Literals.EBOOLEAN, result);
	}

	private static VariableImpl evaluate(LiteralExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) {

		/*
		 * Let EcoreFactory create an Object of the appropriate Value type
		 */
		Object value = expression.getType().getEPackage().getEFactoryInstance()
				.createFromString(expression.getType(), expression.getValue());

		return new VariableImpl(Constants.INTERNAL, expression.getType(), value);
	}

	private static VariableImpl evaluate(UnaryExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) throws InterpreterException {
		VariableImpl result = evaluate(expression.getExpression(), variablesScope, contextClassifier, contextInstance);

		return new VariableImpl(Constants.INTERNAL, EcorePackage.Literals.EBOOLEAN, !(Boolean) result.getValue());
	}

	private static VariableImpl evaluate(ComparisonExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) throws InterpreterException {
		/*
		 * Evaluate left expression
		 */
		VariableImpl leftVariable = evaluate(expression.getLeft(), variablesScope, contextClassifier, contextInstance);

		/*
		 * Evaluate right expression
		 */
		VariableImpl rightVariable = evaluate(expression.getRight(), variablesScope, contextClassifier, contextInstance);

		/*
		 * Compare values
		 */
		Object operand1 = leftVariable.getValue();
		Object operand2 = rightVariable.getValue();

		boolean result = false;

		/*
		 * TODO: Is there a more elegant solution?
		 */
		int i;

		if (operand1 instanceof Number && operand2 instanceof Number) {
			/*
			 * If both operands are numbers, cast them to BigDecimals and compare them
			 */
			i = new BigDecimal(operand1.toString()).compareTo(new BigDecimal(operand2.toString()));

		} else if (operand1.getClass() == operand2.getClass()) {
			/*
			 * Otherwise, we can only compare Comparables of the same type, e.g. String and String.
			 */
			i = ((Comparable) operand1).compareTo(operand2);
		} else {
			throw new InterpreterException();
		}

		switch (expression.getOperator()) {
		case EQUALS: {
			result = i == 0;
			break;
		}
		case LESS: {
			result = i < 0;
			break;
		}
		case LESS_EQUAL: {
			result = i <= 0;
			break;
		}
		case GREATER: {
			result = i > 0;
			break;
		}
		case GREATER_EQUAL: {
			result = i >= 0;
			break;
		}
		case UNEQUAL: {
			result = i != 0;
			break;
		}
		case REGEX: {
			result = operand1.toString().matches(operand2.toString());
			break;
		}
		default: {
			throw new UnsupportedOperationException();
		}
		}

		return new VariableImpl(Constants.INTERNAL, EcorePackage.Literals.EBOOLEAN, result);
	}

	private static VariableImpl evaluate(ObjectAttributeExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) throws InterpreterException {
		try {

			Object result = null;

			if (contextInstance != null) {
				EAttribute attribute = expression.getAttribute();

				VariableImpl var = variablesScope.getVariable(expression.getOccurrence().getName());

				if (var != null) {
					result = ((EObject) var.getValue()).eGet(attribute);
				} else {
					// e.g. for negative object variables
					result = ((EObject) contextInstance).eGet(attribute);
				}
			} else {
				result = expression.getAttribute();
			}

			return new VariableImpl(Constants.INTERNAL, EcorePackage.Literals.EJAVA_OBJECT, result);
		} catch (Exception e) {
			e.printStackTrace();

			throw new InterpreterException();
		}
	}

	private static VariableImpl evaluate(ObjectOccurrenceExpression expression, Scope variablesScope,
			EClassifier contextClassifier, Object contextInstance) {
		String name = expression.getOccurrence().getName();

		VariableImpl var = variablesScope.getVariable(name);

		if (var == null) {
			var = new VariableImpl(Constants.INTERNAL, expression.getOccurrence().getType(), null);
		}

		return var;
	}
}
