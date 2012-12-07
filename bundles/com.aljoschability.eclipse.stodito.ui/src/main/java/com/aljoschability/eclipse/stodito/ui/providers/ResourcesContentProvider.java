package com.aljoschability.eclipse.stodito.ui.providers;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import com.aljoschability.eclipse.stodito.Activity;

public class ResourcesContentProvider extends ArrayContentProvider implements ITreeContentProvider {
	@Override
	public Object[] getElements(Object element) {
		return getChildren(element);
	}

	@Override
	public Object[] getChildren(Object element) {
		if (element instanceof Activity) {
			Collection<EPackage> children = new HashSet<EPackage>();

			// collect registered ePackages
			for (String nsUri : ((Activity) element).getPackageUris()) {
				EPackage registered = EPackage.Registry.INSTANCE.getEPackage(nsUri);
				if (registered != null) {
					children.add(registered);
				}
			}

			// collect referenced ePackages
			for (EObject reference : ((EAnnotation) element).getReferences()) {
				if (reference instanceof EPackage) {
					children.add((EPackage) reference);
				}
			}

			return children.toArray(new EPackage[children.size()]);
		}
		if (element instanceof EPackage) {
			Collection<EClassifier> children = ((EPackage) element).getEClassifiers();

			return children.toArray(new EClassifier[children.size()]);
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof EClassifier) {
			return ((EClassifier) element).getEPackage();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof EPackage;
	}
}
