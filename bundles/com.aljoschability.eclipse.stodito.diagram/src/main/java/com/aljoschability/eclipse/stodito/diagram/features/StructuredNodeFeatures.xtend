package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.StructuredNode
import com.aljoschability.eclipse.stodito.diagram.util.ActivityExtensions
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

class StructuredNodeCreateFeature extends CoreCreateFeature {
	extension ActivityExtensions = new ActivityExtensions

	new(IFeatureProvider fp) {
		super(fp)

		name = "Structured Node"
		description = "Create Structured Node"
		imageId = StructuredNode.simpleName
		largeImageId = StructuredNode.simpleName

		editable = true
	}

	override canCreate(ICreateContext context) {
		return context.activity != null
	}

	override createElement(ICreateContext context) {
		val node = StoditoFactory::eINSTANCE.createStructuredNode

		context.activity.nodes += node

		return node
	}
}
