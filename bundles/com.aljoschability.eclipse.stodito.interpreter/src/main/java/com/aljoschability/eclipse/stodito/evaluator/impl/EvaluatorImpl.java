package com.aljoschability.eclipse.stodito.evaluator.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.evaluator.Evaluator;
import com.aljoschability.eclipse.stodito.evaluator.Notifier;
import com.aljoschability.eclipse.stodito.evaluator.Step;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationListener;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationType;
import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;

public class EvaluatorImpl implements Evaluator, Notifier {
	private Activity activity;
	private Map<Parameter, Object> bindings;

	@Override
	public void evaluate(EvaluationType type) throws InterpreterException {
		switch (type) {
		case RUN:
			run();
			break;

		default:
			break;
		}
	}

	private void run() throws InterpreterException {
		Map<Parameter, Object> parameters = new LinkedHashMap<Parameter, Object>();
		for (Parameter parameter : activity.getParameters()) {
			parameters.put(parameter, bindings.get(parameter));
		}
		BindParametersSequence s1 = new BindParametersSequence(parameters);

		while (s1.hasNext()) {
			Step step = s1.next();
			step.execute(this);
		}
	}

	@Override
	public void addListener(EvaluationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bind(String name, EClassifier type, Object value) {
		System.out.println("bind " + name + " [" + type + "] to " + value);
	}

	@Override
	public VariableImpl getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(Activity activity, Map<Parameter, Object> bindings) {
		this.activity = activity;
		this.bindings = bindings;
	}

	@Override
	public void removeListener(EvaluationListener listener) {
		// TODO Auto-generated method stub

	}

}
