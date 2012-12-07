package com.aljoschability.eclipse.stodito.ui.editors;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;

import com.aljoschability.eclipse.core.graphiti.editors.CoreFeatureProvider;
import com.aljoschability.eclipse.stodito.ui.patterns.CallNodePattern;
import com.aljoschability.eclipse.stodito.ui.patterns.StoryNodePattern;

public class StoditoFeatureProvider extends CoreFeatureProvider {
	public StoditoFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);

		addPattern(new CallNodePattern());
		addPattern(new StoryNodePattern());
	}
}
