package com.aljoschability.eclipse.stodito.interpreter.util;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;

public class VariablesEventReceiver implements EvaluationListener {

	@Override
	public void change(Variable variable, Object oldValue, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(Variable variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void end(EObject node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void is(Expression expression, Variable value) {
		System.out.println("evaluated to " + value + "(" + expression + ")");
	}

	@Override
	public void remove(Variable variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(EObject node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void traversing(ControlFlow edge) {
		// TODO Auto-generated method stub

	}

}
