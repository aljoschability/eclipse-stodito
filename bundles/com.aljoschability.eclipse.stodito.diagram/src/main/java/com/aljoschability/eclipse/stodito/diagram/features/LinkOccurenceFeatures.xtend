package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateConnectionFeature
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateConnectionContext

class CreateLinkOccurenceFeature extends CoreCreateConnectionFeature {
	new(IFeatureProvider fp) {
		super(fp)

		name = "Link"
		imageId = StoditoPackage.Literals::LINK.name
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
