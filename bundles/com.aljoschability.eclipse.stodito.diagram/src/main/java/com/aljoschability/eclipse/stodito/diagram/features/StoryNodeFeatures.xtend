package com.aljoschability.eclipse.stodito.diagram.features

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.StoryNode
import com.aljoschability.eclipse.stodito.diagram.util.ActivityExtensions
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.context.ICreateContext
import org.eclipse.graphiti.features.impl.AbstractAddFeature

class StoryNodeCreateFeature extends CoreCreateFeature {
	extension ActivityExtensions = new ActivityExtensions

	new(IFeatureProvider fp) {
		super(fp)

		name = "Story Node"
		description = "Create Story Node"
		imageId = StoryNode.simpleName
		largeImageId = StoryNode.simpleName

		editable = true
	}

	override canCreate(ICreateContext context) {
		return context.activity != null
	}

	override createElement(ICreateContext context) {
		val node = StoditoFactory::eINSTANCE.createStoryNode

		context.activity.nodes += node

		return node
	}
}

class StoryNodeAddFeature extends AbstractAddFeature {
	new(IFeatureProvider fp) {
		super(fp)
	}

	override add(IAddContext context) {

		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override canAdd(IAddContext context) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
}
