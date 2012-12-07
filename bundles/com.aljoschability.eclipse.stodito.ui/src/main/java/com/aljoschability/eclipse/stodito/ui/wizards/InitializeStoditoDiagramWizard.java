package com.aljoschability.eclipse.stodito.ui.wizards;

import org.eclipse.core.resources.IFile;

import com.aljoschability.eclipse.core.graphiti.wizards.AbstractInitializeDiagramWizard;
import com.aljoschability.eclipse.stodito.StoditoConstants;

public class InitializeStoditoDiagramWizard extends AbstractInitializeDiagramWizard {
	public InitializeStoditoDiagramWizard(IFile file) {
		super(file);

		setWindowTitle("Initialize Story Diagram");
		setDiagramPageTitle("Select Story Diagram Resource");
	}

	@Override
	protected String getDiagramFileExtension() {
		return StoditoConstants.FILE_DIAGRAM;
	}

	@Override
	protected String getModelFileExtension() {
		return StoditoConstants.FILE_MODEL;
	}
}
