package com.aljoschability.eclipse.stodito.interpreter.util;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;
import com.aljoschability.eclipse.stodito.interpreter.util.ExecutionConsole.StreamType;

public class ConsoleEventReceiver implements EvaluationListener {
	private ExecutionConsole console;

	public ConsoleEventReceiver(ExecutionConsole console) {
		this.console = console;
	}

	@Override
	public void change(Variable variable, Object oldValue, Object value) {
		console.append(StreamType.DEBUG, "changed " + String.valueOf(variable));
	}

	@Override
	public void create(Variable variable) {
		console.append(StreamType.DEBUG, "created " + String.valueOf(variable));
	}

	@Override
	public void end(EObject node) {
		console.append(StreamType.DEBUG, "ended " + String.valueOf(node));
	}

	@Override
	public void is(Expression expression, Variable value) {
		console.append(StreamType.DEBUG, "evaluated to " + value + "(" + expression + ")");
	}

	@Override
	public void remove(Variable variable) {
		console.append(StreamType.DEBUG, "removed " + String.valueOf(variable));
	}

	@Override
	public void start(EObject node) {
		console.append(StreamType.DEBUG, "started " + String.valueOf(node));
	}

	@Override
	public void traversing(ControlFlow edge) {
		console.append(StreamType.DEBUG, String.valueOf(edge));
	}
}
