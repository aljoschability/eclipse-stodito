package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;
import com.aljoschability.eclipse.stodito.StoryNode;

public class CreateStoryNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateStoryNodeFeature(IFeatureProvider fp) {
		super(fp, "Story Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		StoryNode bo = StoditoFactory.eINSTANCE.createStoryNode();

		// collect existing names
		Collection<String> names = new LinkedHashSet<String>();
		for (ActivityNode node : nodes) {
			if (node instanceof StoryNode) {
				names.add(((StoryNode) node).getName());
			}
		}

		bo.setName(createUniqueName("Story", names));

		return bo;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.STORY_NODE;
	}
}
