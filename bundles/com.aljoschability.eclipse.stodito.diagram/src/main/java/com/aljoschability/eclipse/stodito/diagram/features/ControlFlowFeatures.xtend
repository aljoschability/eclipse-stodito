package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider

class ControlFlowCreateFeature extends CoreCreateEdgeFeature {
	new(IFeatureProvider fp) {
		super(fp, "Control Flow");
	}

	override protected getEClass() {
		return StoditoPackage.Literals.CONTROL_FLOW;
	}
}
