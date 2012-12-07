package com.aljoschability.eclipse.stodito.ui.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class EditExpressionDialog extends TitleAreaDialog {
	public EditExpressionDialog(Shell shell) {
		super(shell);
		setTitle("Edit Parameter Expression");
		setMessage("Edit the expression that should be bind to the parameter of the parameter.");
		setHelpAvailable(false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(new GridLayout(1, false));

		Group grpExpression = new Group(area, SWT.NONE);
		grpExpression.setLayout(new GridLayout(1, false));
		grpExpression.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpExpression.setText("Expression");

		return area;
	}
}
