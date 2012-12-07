package com.aljoschability.eclipse.stodito.ui.providers;

import org.eclipse.emf.ecore.resource.Resource;

import com.aljoschability.eclipse.stodito.Parameter;

public interface ParameterBinder {
	Resource getResource();

	Object getValue(Parameter parameter);

	void setError(String text);

	void setValue(Parameter element, Object value);
}
