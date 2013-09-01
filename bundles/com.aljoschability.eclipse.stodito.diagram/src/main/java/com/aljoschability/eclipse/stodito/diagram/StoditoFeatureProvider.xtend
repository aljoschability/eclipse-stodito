package com.aljoschability.eclipse.stodito.diagram

import com.aljoschability.eclipse.stodito.CallNode
import com.aljoschability.eclipse.stodito.StoryNode
import com.aljoschability.eclipse.stodito.diagram.features.CallNodeAddFeature
import com.aljoschability.eclipse.stodito.diagram.features.StoryNodeAddFeature
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider

class StoditoFeatureProvider extends DefaultFeatureProvider {
	new(IDiagramTypeProvider dtp) {
		super(dtp)
	}

	override getAddFeature(IAddContext context) {
		switch context.newObject {
			StoryNode: new StoryNodeAddFeature(this)
			CallNode: new CallNodeAddFeature(this)
		}
	}
}
