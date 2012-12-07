package com.aljoschability.eclipse.stodito.interpreter.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectInputPage extends WizardPage {
	protected SelectInputPage() {
		super(SelectInputPage.class.getSimpleName());
	}

	@Override
	public void createControl(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);

		setControl(page);
	}
}
