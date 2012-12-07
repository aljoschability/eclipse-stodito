package com.aljoschability.eclipse.stodito.ui.wizards;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.core.emf.wizards.AbstractCreateModelWizard;
import com.aljoschability.eclipse.stodito.StoditoConstants;
import com.aljoschability.eclipse.stodito.StoditoFactory;

public class NewStoditoModelWizard extends AbstractCreateModelWizard {
	public NewStoditoModelWizard() {
		super("stories");

		setWindowTitle("New Story Diagram Model");
		setModelPageTitle("Create Story Diagram Model");
		setModelPageDescription("Create a new story diagram model file.");
	}

	@Override
	protected String getEditorId() {
		return StoditoConstants.EDITOR_ID_MODEL;
	}

	@Override
	protected EObject createModelContent() {
		return StoditoFactory.eINSTANCE.createActivity();
	}
}
