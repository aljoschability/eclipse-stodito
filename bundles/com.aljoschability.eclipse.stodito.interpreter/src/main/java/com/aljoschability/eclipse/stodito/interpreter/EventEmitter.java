package com.aljoschability.eclipse.stodito.interpreter;

import com.aljoschability.eclipse.stodito.Expression;
import com.aljoschability.eclipse.stodito.evaluator.Variable;

public interface EventEmitter extends EvaluationListener {

	void add(EvaluationListener receiver);

	@Override
	void is(Expression expression, Variable value);

	void remove(EvaluationListener receiver);
}
