package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateConnectionFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateConnectionContext

class ControlFlowCreateFeature extends CoreCreateConnectionFeature {
	new(IFeatureProvider fp) {
		super(fp)

		name = "Control Flow"
		description = "Create Control Flow"
		imageId = StoditoPackage.Literals.CONTROL_FLOW.name
		largeImageId = StoditoPackage.Literals.CONTROL_FLOW.name
	}

	override protected createElement(ICreateConnectionContext context) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override canCreate(ICreateConnectionContext context) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override canStartConnection(ICreateConnectionContext context) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
}
