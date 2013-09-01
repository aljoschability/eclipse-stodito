package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class CreateObjectOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	new(IFeatureProvider fp) {
		super(fp, "Object");
	}

	override protected getEClass() {
		return StoditoPackage.Literals.OBJECT_OCCURRENCE;
	}
}
