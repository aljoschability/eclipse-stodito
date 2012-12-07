package com.aljoschability.eclipse.stodito.ui.patterns;

import org.eclipse.emf.ecore.EClass;

import com.aljoschability.eclipse.core.graphiti.pattern.CorePattern;
import com.aljoschability.eclipse.stodito.StoditoPackage;
import com.aljoschability.eclipse.stodito.StoryNode;

public class StoryNodePattern extends CorePattern {
	public StoryNodePattern() {
		super();
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.STORY_NODE;
	}

	@Override
	protected boolean isBO(Object bo) {
		return bo instanceof StoryNode;
	}
}
