package com.aljoschability.eclipse.stodito.interpreter;

import org.eclipse.emf.ecore.EClassifier;

public class Variable {
	private final String key;
	private final EClassifier type;
	private Object value;

	public Variable(String key, EClassifier type, Object value) {
		this.key = key;
		this.type = type;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public EClassifier getType() {
		return type;
	}

	public static Variable copy(Variable variable) {
		return new Variable(variable.getKey(), variable.getType(), variable.getValue());
	}
}
