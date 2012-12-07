package com.aljoschability.eclipse.stodito.evaluator;

import java.util.Map;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.evaluator.impl.VariableImpl;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationType;
import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;

public interface Evaluator {
	void addListener(EvaluationListener listener);

	void evaluate(EvaluationType type) throws InterpreterException;

	VariableImpl getResult();

	void initialize(Activity activity, Map<Parameter, Object> bindings);

	void removeListener(EvaluationListener listener);
}
