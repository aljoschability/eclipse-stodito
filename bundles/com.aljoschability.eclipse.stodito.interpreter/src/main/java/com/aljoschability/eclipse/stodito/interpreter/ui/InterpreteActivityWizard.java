package com.aljoschability.eclipse.stodito.interpreter.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.Wizard;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.Parameter;

public class InterpreteActivityWizard extends Wizard {
	private Activity activity;
	private Map<Parameter, Object> bindings;
	private InputPage inputPage;
	private InterpretationPage interpretationPage;
	private ResourceSet resourceSet;

	public InterpreteActivityWizard(Activity activity) {
		this.activity = activity;

		setWindowTitle("Execute Activity");
		setHelpAvailable(false);
		setNeedsProgressMonitor(true);

		// initialize parameter bindings
		bindings = new LinkedHashMap<Parameter, Object>();
		for (Parameter parameter : activity.getParameters()) {
			bindings.put(parameter, null);
		}
	}

	@Override
	public void addPages() {
		if (!bindings.isEmpty()) {
			addPage(inputPage = new InputPage());
		}
		addPage(interpretationPage = new InterpretationPage());
	}

	public Activity getActivity() {
		return activity;
	}

	public Map<Parameter, Object> getBindings() {
		return bindings;
	}

	public Resource getResource() {
		if (inputPage != null) {
			return inputPage.getResource();
		}
		return null;
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	@Override
	public boolean performFinish() {
		// Interpreter interpreter = new Interpreter(new SystemEmitter());
		//
		// try {
		// Map<String, Variable> results = interpreter.execute(activity, bindings);
		//
		// System.out.println();
		// for (String key : results.keySet()) {
		// System.out.println(key + "=" + results.get(key));
		// }
		// } catch (InterpreterException e) {
		// e.printStackTrace();
		// }
		return true;
	}
}
