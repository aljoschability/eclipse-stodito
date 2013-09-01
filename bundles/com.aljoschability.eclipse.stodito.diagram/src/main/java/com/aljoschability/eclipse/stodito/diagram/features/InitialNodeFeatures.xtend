package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.InitialNode
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.diagram.util.ActivityExtensions
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

public class InitialNodeCreateFeature extends CoreCreateFeature {
	extension ActivityExtensions = new ActivityExtensions

	new(IFeatureProvider fp) {
		super(fp)

		name = "Initial Node"
		description = "Create Initial Node"
		imageId = InitialNode.simpleName
		largeImageId = InitialNode.simpleName

		editable = true
	}

	override canCreate(ICreateContext context) {
		return context.activity != null
	}

	override createElement(ICreateContext context) {
		val node = StoditoFactory::eINSTANCE.createInitialNode

		context.activity.nodes += node

		return node
	}
}
