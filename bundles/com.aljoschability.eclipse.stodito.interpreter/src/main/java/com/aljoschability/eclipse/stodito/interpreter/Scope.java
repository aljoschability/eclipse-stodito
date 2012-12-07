package com.aljoschability.eclipse.stodito.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;

import com.aljoschability.eclipse.stodito.evaluator.impl.VariableImpl;

public class Scope extends AbstractNotifier {
	private final Scope parent;
	private final Map<String, VariableImpl> variables;

	public Scope(EventEmitter emitter) {
		this(emitter, null);
	}

	public Scope(EventEmitter emitter, Scope parent) {
		super(emitter);
		this.parent = parent;

		variables = new LinkedHashMap<String, VariableImpl>();
	}

	public VariableImpl createVariable(String key, EClassifier classifier, Object value) {
		deleteVariable(key);

		VariableImpl variable = new VariableImpl(key, classifier, value);

		variables.put(key, variable);

		getEmitter().create(variable);

		return variable;
	}

	public VariableImpl deleteVariable(String name) {
		VariableImpl variable = variables.remove(name);

		if (variable != null) {
			getEmitter().remove(variable);
		}

		return variable;
	}

	public Scope getParent() {
		return parent;
	}

	public VariableImpl getVariable(String key) {
		VariableImpl variable = variables.get(key);

		if (variable == null && parent != null) {
			return parent.getVariable(key);
		}

		return variable;
	}

	public Collection<VariableImpl> getVariables() {
		if (parent == null) {
			return Collections.unmodifiableCollection(variables.values());
		}

		Collection<VariableImpl> result = new ArrayList<VariableImpl>();

		result.addAll(parent.getVariables());
		result.addAll(variables.values());

		return Collections.unmodifiableCollection(result);
	}

	public boolean hasVariable(String key) {
		if (variables.containsKey(key)) {
			return true;
		}

		if (parent != null) {
			return parent.hasVariable(key);
		}

		return false;
	}

	public void mergeWithParent() {
		for (VariableImpl variable : variables.values()) {
			parent.variables.put(variable.getName(), variable);
		}

		variables.clear();
	}

	public void setVariableValue(String key, Object value) {
		VariableImpl variable = variables.get(key);

		Object oldValue = variable.getValue();

		variable.setValue(value);

		getEmitter().change(variable, oldValue, value);
	}
}
