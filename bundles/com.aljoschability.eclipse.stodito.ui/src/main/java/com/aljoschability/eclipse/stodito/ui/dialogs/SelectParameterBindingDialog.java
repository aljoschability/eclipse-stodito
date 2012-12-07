package com.aljoschability.eclipse.stodito.ui.dialogs;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class SelectParameterBindingDialog extends AbstractTreeSelectionDialog<EObject> {
	private EClassifier eClassifier;
	private Resource resource;

	public SelectParameterBindingDialog() {
		super("Parameter Binding", "Select Parameter Value",
				"Select the element that should be used for the parameter.");
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setType(EClassifier eClassifier) {
		this.eClassifier = eClassifier;
	}

	@Override
	protected String getErrorMessage(Object element) {
		if (eClassifier != null && eClassifier.isInstance(element)) {
			return null;
		}
		return String.format("You have to select an element of the type '%1s'.", eClassifier);
	}

	@Override
	protected Object getInput() {
		return resource;
	}

	@Override
	protected ViewerFilter getViewerFilter() {
		return new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {
				if (eClassifier != null && eClassifier.isInstance(element)) {
					return true;
				}
				if (element instanceof Resource) {
					for (EObject content : ((Resource) element).getContents()) {
						if (select(viewer, element, content)) {
							return true;
						}
					}
				}
				if (element instanceof EObject) {
					for (EObject content : ((EObject) element).eContents()) {
						if (select(viewer, element, content)) {
							return true;
						}
					}
				}
				return false;
			}
		};
	}

	public void setSelectedElement(EObject value) {
		// TODO Auto-generated method stub
		
	}
}
