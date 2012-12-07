package com.aljoschability.eclipse.stodito.interpreter.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;
import com.aljoschability.eclipse.stodito.interpreter.EventEmitter;

public class EventEmitterImpl implements EventEmitter {
	private final Collection<EvaluationListener> receivers;

	public EventEmitterImpl() {
		receivers = new ArrayList<EvaluationListener>();
	}

	@Override
	public void add(EvaluationListener receiver) {
		receivers.add(receiver);
	}

	@Override
	public void change(Variable variable, Object oldValue, Object value) {
		for (EvaluationListener receiver : receivers) {
			receiver.change(variable, oldValue, value);
		}
	}

	@Override
	public void create(Variable variable) {
		for (EvaluationListener receiver : receivers) {
			receiver.create(variable);
		}
	}

	@Override
	public void end(EObject node) {
		for (EvaluationListener receiver : receivers) {
			receiver.end(node);
		}
	}

	@Override
	public void is(Expression expression, Variable value) {
		for (EvaluationListener receiver : receivers) {
			receiver.is(expression, value);
		}
	}

	@Override
	public void remove(EvaluationListener receiver) {
		receivers.remove(receiver);
	}

	@Override
	public void remove(Variable variable) {
		for (EvaluationListener receiver : receivers) {
			receiver.remove(variable);
		}
	}

	@Override
	public void start(EObject node) {
		for (EvaluationListener receiver : receivers) {
			receiver.start(node);
		}
	}

	@Override
	public void traversing(ControlFlow edge) {
		for (EvaluationListener receiver : receivers) {
			receiver.traversing(edge);
		}
	}
}
