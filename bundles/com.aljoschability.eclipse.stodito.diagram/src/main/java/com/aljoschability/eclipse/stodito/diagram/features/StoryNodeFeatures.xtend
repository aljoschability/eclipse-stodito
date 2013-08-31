package com.aljoschability.eclipse.stodito.diagram.features

import org.eclipse.graphiti.features.impl.AbstractAddFeature
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.impl.AbstractCreateFeature
import org.eclipse.graphiti.features.context.ICreateContext
import org.eclipse.graphiti.features.context.ITargetContext
import org.eclipse.graphiti.services.Graphiti
import com.aljoschability.eclipse.stodito.Activity
import org.eclipse.graphiti.services.ILinkService
import com.aljoschability.eclipse.stodito.StructuredNode
import com.aljoschability.eclipse.stodito.StoditoFactory
import org.eclipse.emf.ecore.EObject

class StoryNodeCreateFeature extends AbstractCreateFeature {
	extension StoryNodeExtensions = new StoryNodeExtensions

	new(IFeatureProvider fp) {
		super(fp, "Story Node", "Create Story Node")
	}

	override canCreate(ICreateContext context) {
		val container = context.container

		return container instanceof Activity || container instanceof StructuredNode
	}

	override create(ICreateContext context) {
		val container = context.container
		val element = StoditoFactory::eINSTANCE.createStoryNode

		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
}

class StoryNodeExtensions {
	extension ILinkService = Graphiti::linkService

	def EObject getContainer(ITargetContext context) {
		return context.targetContainer?.businessObjectForLinkedPictogramElement
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
