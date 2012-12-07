package com.aljoschability.eclipse.stodito.ui.dialogs;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

public class AbstractTreeSelectionDialog<T> extends ElementTreeSelectionDialog {

	public AbstractTreeSelectionDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
		// TODO Auto-generated constructor stub
	}

	public AbstractTreeSelectionDialog(String windowTitle, String title, String message) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),null,null);
		
		// TODO Auto-generated constructor stub
	}

	protected String getErrorMessage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public T getElement() {
		return null;
	}

	protected Object getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	protected ViewerFilter getViewerFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	protected ITreeContentProvider getContentProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	protected ILabelProvider getLabelProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String getOkayText() {
		// TODO Auto-generated method stub
		return null;
	}

	protected ViewerComparator getViewerComparator() {
		// TODO Auto-generated method stub
		return null;
	}

}
