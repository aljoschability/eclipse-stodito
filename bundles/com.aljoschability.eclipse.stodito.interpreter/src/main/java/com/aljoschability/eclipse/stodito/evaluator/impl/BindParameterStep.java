package com.aljoschability.eclipse.stodito.evaluator.impl;

import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.evaluator.Notifier;
import com.aljoschability.eclipse.stodito.evaluator.Step;
import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;

public class BindParameterStep implements Step {
	private final Parameter parameter;
	private final Object value;

	public BindParameterStep(Parameter parameter, Object value) {
		this.parameter = parameter;
		this.value = value;
	}

	@Override
	public void execute(Notifier notifier) throws InterpreterException {
		notifier.bind(parameter.getName(), parameter.getType(), value);
	}
}
