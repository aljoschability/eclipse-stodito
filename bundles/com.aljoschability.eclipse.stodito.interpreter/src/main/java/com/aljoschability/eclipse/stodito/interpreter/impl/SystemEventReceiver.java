package com.aljoschability.eclipse.stodito.interpreter.impl;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;

public class SystemEventReceiver implements EvaluationListener {
	@Override
	public void change(Variable variable, Object oldValue, Object value) {
		System.out.println("changed variable: " + variable + " := " + value + " (was " + oldValue + ")");
	}

	@Override
	public void create(Variable variable) {
		System.out.println("created variable: " + variable);
	}

	@Override
	public void end(EObject node) {
		System.out.println("end " + node);
	}

	@Override
	public void is(Expression expression, Variable value) {
		System.out.println("evaluated to " + value + "(" + expression + ")");
	}

	@Override
	public void remove(Variable variable) {
		System.out.println("removed variable: " + variable);

	}

	@Override
	public void start(EObject node) {
		System.out.println("starting " + node);
	}

	@Override
	public void traversing(ControlFlow edge) {
		System.out.println("traversing " + edge);
	}
}
