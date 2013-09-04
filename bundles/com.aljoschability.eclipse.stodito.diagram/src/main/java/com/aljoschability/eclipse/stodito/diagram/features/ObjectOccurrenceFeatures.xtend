package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class CreateObjectOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	new(IFeatureProvider fp) {
		super(fp, "Object");

		imageId = StoditoPackage.Literals::OBJECT_OCCURRENCE.name
	}
}
