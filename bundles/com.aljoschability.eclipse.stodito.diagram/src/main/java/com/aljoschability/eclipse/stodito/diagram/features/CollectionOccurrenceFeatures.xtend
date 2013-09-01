package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

public class CreateCollectionOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	new(IFeatureProvider fp) {
		super(fp, "Collection");
	}

	override protected getEClass() {
		return StoditoPackage.Literals.COLLECTION_OCCURRENCE;
	}
}
