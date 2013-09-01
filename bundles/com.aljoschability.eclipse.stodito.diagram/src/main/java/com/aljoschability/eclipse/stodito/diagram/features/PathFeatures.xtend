package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class CreatePathFeature extends CoreCreateEdgeFeature {
	new(IFeatureProvider fp) {
		super(fp, "Path")
	}

	override protected getEClass() {
		return StoditoPackage.Literals::PATH
	}
}
