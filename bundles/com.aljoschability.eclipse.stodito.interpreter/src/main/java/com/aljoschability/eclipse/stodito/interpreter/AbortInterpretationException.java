package com.aljoschability.eclipse.stodito.interpreter;

import com.aljoschability.eclipse.stodito.interpreter.impl.EvaluatorOLDImpl;

public class AbortInterpretationException extends InterpreterException {
	private static final long serialVersionUID = 1L;
	private EvaluatorOLDImpl interpreter;

	public AbortInterpretationException(EvaluatorOLDImpl interpreter) {
		this.interpreter = interpreter;
	}

	public EvaluatorOLDImpl getTargetInterpreter() {
		return interpreter;
	}
}
