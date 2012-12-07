package com.aljoschability.eclipse.stodito.ui.editors;

import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import com.aljoschability.eclipse.core.graphiti.editors.CoreDiagramTypeProvider;

public class StoditoDiagramTypeProvider extends CoreDiagramTypeProvider {
	public StoditoDiagramTypeProvider() {
		setFeatureProvider(new StoditoFeatureProvider(this));
	}

	@Override
	protected IToolBehaviorProvider createToolBehaviorProvider() {
		return new StoditoToolBehaviorProvider(this);
	}
}
