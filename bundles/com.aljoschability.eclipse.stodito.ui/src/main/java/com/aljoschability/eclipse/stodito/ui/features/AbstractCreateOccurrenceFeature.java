package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateNodeFeature;

public abstract class AbstractCreateOccurrenceFeature extends CoreCreateNodeFeature {
	public AbstractCreateOccurrenceFeature(IFeatureProvider fp, String name) {
		super(fp, name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return false;
	}

	@Override
	protected EObject createElement(ICreateContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
