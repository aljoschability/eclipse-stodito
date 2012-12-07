package com.aljoschability.eclipse.stodito.ui.wizards;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.wizard.Wizard;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.StoditoFactory;

public class NewStoryDiagramWizard extends Wizard {
	public NewStoryDiagramWizard() {
		// super("stories", "stories_diagram", "com.aljoschability.stories", StoditoPackage.Literals.ACTIVITY);
	}

	protected String getEditorId() {
		return "com.aljoschability.stories.editor";
	}

	protected EObject createModel() {
		Activity model = StoditoFactory.eINSTANCE.createActivity();

		model.setName("doSomething");
		model.getPackageUris().add(EcorePackage.eINSTANCE.getNsURI());

		return model;
	}

	protected void configure() {
		setWindowTitle("New Story Diagram");
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
}
