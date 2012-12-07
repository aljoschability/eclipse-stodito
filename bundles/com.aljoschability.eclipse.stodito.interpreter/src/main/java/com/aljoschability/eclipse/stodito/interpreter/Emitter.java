package com.aljoschability.eclipse.stodito.interpreter;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;

public interface Emitter {
	void traversing(ControlFlow edge);

	void start(EObject node);

	void end(EObject node);

	void create(Variable variable);

	void remove(Variable variable);

	void change(Variable variable, Object oldValue, Object value);
}
