package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class CreateInclusionLinkOccurenceFeature extends CoreCreateEdgeFeature {
	new(IFeatureProvider fp) {
		super(fp, "Inclusion Link");
	}

	override protected getEClass() {
		return StoditoPackage.Literals.INCLUSION_LINK;
	}
}
