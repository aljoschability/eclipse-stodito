package com.aljoschability.eclipse.stodito.interpreter;

import com.aljoschability.eclipse.stodito.evaluator.Evaluator;
import com.aljoschability.eclipse.stodito.evaluator.impl.EvaluatorImpl;

public final class ExecutionUtil {
	private ExecutionUtil() {
		// hide constructor
	}

	public static Evaluator createExecutor(ClassLoader loader) {
		return new EvaluatorImpl();
	}
}
