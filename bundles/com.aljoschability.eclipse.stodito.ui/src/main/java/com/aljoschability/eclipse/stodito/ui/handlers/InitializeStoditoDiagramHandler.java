package com.aljoschability.eclipse.stodito.ui.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.IWizard;

import com.aljoschability.eclipse.core.graphiti.handlers.AbstractOpenInitializeDiagramWizardHandler;
import com.aljoschability.eclipse.stodito.ui.wizards.InitializeStoditoDiagramWizard;

public class InitializeStoditoDiagramHandler extends AbstractOpenInitializeDiagramWizardHandler {
	@Override
	protected IWizard createWizard(IFile file) {
		return new InitializeStoditoDiagramWizard(file);
	}
}
