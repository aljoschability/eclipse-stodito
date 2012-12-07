package com.aljoschability.eclipse.stodito.evaluator;

import org.eclipse.emf.ecore.EClassifier;

public interface Notifier {
	void bind(String name, EClassifier type, Object value);
}
