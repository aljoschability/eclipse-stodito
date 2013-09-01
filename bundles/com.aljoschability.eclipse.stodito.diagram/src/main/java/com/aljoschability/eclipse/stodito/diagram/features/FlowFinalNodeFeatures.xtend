package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.FlowFinalNode
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.diagram.util.ActivityExtensions
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

public class FlowFinalNodeCreateFeature extends CoreCreateFeature {
	extension ActivityExtensions = new ActivityExtensions

	new(IFeatureProvider fp) {
		super(fp)

		name = "Flow Final Node"
		description = "Create Flow Final Node"
		imageId = FlowFinalNode.simpleName
		largeImageId = FlowFinalNode.simpleName

		editable = true
	}

	override canCreate(ICreateContext context) {
		return context.activity != null
	}

	override createElement(ICreateContext context) {
		val node = StoditoFactory::eINSTANCE.createFlowFinalNode

		context.activity.nodes += node

		return node
	}
}
