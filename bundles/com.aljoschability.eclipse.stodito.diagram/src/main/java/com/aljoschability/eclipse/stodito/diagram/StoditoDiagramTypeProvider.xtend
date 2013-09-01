package com.aljoschability.eclipse.stodito.diagram

import com.aljoschability.eclipse.core.graphiti.editors.CoreDiagramTypeProvider

class StoditoDiagramTypeProvider extends CoreDiagramTypeProvider {
	new() {
		featureProvider = new StoditoFeatureProvider(this)
	}

	override protected createToolBehaviorProvider() {
		return new StoditoToolBehaviorProvider(this)
	}
}
