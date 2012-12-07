package com.aljoschability.eclipse.stodito.evaluator.impl;

import org.eclipse.emf.ecore.EClassifier;

import com.aljoschability.eclipse.stodito.evaluator.Variable;

public class VariableImpl implements Variable {
	private final EClassifier type;

	private final String name;
	private Object value;

	public VariableImpl(String name, EClassifier type) {
		this(name, type, null);
	}

	public VariableImpl(String name, EClassifier type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Variable");
		builder.append(' ');
		builder.append('[');
		if (getType() == null) {
			builder.append(getType());
		} else {
			builder.append(getType().getName());
		}
		builder.append(']');
		builder.append(':');
		builder.append(' ');
		builder.append(getName());
		builder.append('=');
		builder.append(getValue());

		return builder.toString();
	}

	@Override
	public EClassifier getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getValue() {
		return value;
	}

	public static Variable copy(Variable variable) {
		return new VariableImpl(variable.getName(), variable.getType(), variable.getValue());
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}
}
