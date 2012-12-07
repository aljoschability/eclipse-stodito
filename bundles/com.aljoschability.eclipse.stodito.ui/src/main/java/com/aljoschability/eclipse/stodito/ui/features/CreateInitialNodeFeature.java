package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateInitialNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateInitialNodeFeature(IFeatureProvider fp) {
		super(fp, "Initial Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		return StoditoFactory.eINSTANCE.createInitialNode();
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.INITIAL_NODE;
	}
}
