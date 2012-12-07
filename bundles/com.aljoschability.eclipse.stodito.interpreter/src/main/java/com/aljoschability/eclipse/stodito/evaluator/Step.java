package com.aljoschability.eclipse.stodito.evaluator;

import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;

public interface Step {
	void execute(Notifier notifier) throws InterpreterException;
}
