package com.aljoschability.eclipse.stodito.ui.wizards;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.core.graphiti.wizards.AbstractCreateDiagramWizard;
import com.aljoschability.eclipse.stodito.StoditoConstants;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class NewStoditoDiagramWizard extends AbstractCreateDiagramWizard {
	public NewStoditoDiagramWizard() {
		super(StoditoConstants.FILE_MODEL, StoditoConstants.FILE_DIAGRAM);

		setWindowTitle("New Story Diagram");

		setModelPageTitle("Select Model Location");
		setModelPageDescription("Select the location for the story diagram model file.");

		setDiagramPageTitle("Select Diagram Location");
		setDiagramPageDescription("Select the location for the story diagram file.");
	}

	@Override
	protected EObject createModelContent() {
		return StoditoFactory.eINSTANCE.createActivity();
	}

	@Override
	protected String getDiagramTypeId() {
		return StoditoPackage.eNS_URI;
	}
}
