package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateObjectOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	public CreateObjectOccurrenceFeature(IFeatureProvider fp) {
		super(fp, "Object");
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.OBJECT_OCCURRENCE;
	}
}
