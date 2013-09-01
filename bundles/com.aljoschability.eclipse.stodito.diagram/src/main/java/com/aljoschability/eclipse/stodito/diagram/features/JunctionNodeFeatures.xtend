package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.JunctionNode
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.diagram.util.ActivityExtensions
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

public class JunctionNodeCreateFeature extends CoreCreateFeature {
	extension ActivityExtensions = new ActivityExtensions

	new(IFeatureProvider fp) {
		super(fp)

		name = "Junction Node"
		description = "Create Junction Node"
		imageId = JunctionNode.simpleName
		largeImageId = JunctionNode.simpleName

		editable = true
	}

	override canCreate(ICreateContext context) {
		return context.activity != null
	}

	override createElement(ICreateContext context) {
		val node = StoditoFactory::eINSTANCE.createJunctionNode

		context.activity.nodes += node

		return node
	}
}
