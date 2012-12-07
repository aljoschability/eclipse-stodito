package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateCollectionOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	public CreateCollectionOccurrenceFeature(IFeatureProvider fp) {
		super(fp, "Collection");
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.COLLECTION_OCCURRENCE;
	}
}
