package com.aljoschability.eclipse.stodito.evaluator;

import org.eclipse.emf.ecore.EClassifier;

public interface Variable {
	String getName();

	EClassifier getType();

	Object getValue();

	void setValue(Object value);
}
