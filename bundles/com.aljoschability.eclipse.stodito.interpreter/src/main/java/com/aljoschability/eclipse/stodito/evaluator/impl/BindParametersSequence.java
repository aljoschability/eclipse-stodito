package com.aljoschability.eclipse.stodito.evaluator.impl;

import java.util.Iterator;
import java.util.Map;

import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.evaluator.Sequence;
import com.aljoschability.eclipse.stodito.evaluator.Step;

public class BindParametersSequence implements Sequence {
	private final Map<Parameter, Object> parameters;
	private final Iterator<Parameter> iterator;

	public BindParametersSequence(Map<Parameter, Object> parameters) {
		this.parameters = parameters;
		iterator = parameters.keySet().iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Step next() {
		Parameter parameter = iterator.next();
		Object value = parameters.get(parameter);
		return new BindParameterStep(parameter, value);
	}
}
