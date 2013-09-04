package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

public class CreateCollectionOccurrenceFeature extends AbstractCreateOccurrenceFeature {
	new(IFeatureProvider fp) {
		super(fp, "Collection")

		name = "Collection"
		description = "Create Collection Occurence"
		imageId = StoditoPackage.Literals::COLLECTION_OCCURRENCE.name
	}
}
