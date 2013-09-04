package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

abstract class AbstractCreateOccurrenceFeature extends CoreCreateFeature {
	new(IFeatureProvider fp, String name) {
		super(fp)

		this.name = name
		description = "Create " + name
	}

	override canCreate(ICreateContext context) {
		return false;
	}

	override protected createElement(ICreateContext context) {
		return null;
	}
}
