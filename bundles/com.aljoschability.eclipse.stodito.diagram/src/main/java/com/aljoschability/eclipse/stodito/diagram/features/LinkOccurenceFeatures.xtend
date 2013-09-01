package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class CreateLinkOccurenceFeature extends CoreCreateEdgeFeature {
	new(IFeatureProvider fp) {
		super(fp, "Link");
	}

	override	protected getEClass() {
		return StoditoPackage.Literals.LINK;
	}
}
