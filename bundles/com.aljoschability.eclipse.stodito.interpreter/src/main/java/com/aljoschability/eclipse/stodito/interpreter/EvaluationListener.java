package com.aljoschability.eclipse.stodito.interpreter;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;

public interface EvaluationListener {

	void change(Variable variable, Object oldValue, Object value);

	void create(Variable variable);

	void end(EObject node);

	void is(Expression expression, Variable value);

	void remove(Variable variable);

	void start(EObject node);

	void traversing(ControlFlow edge);
}
