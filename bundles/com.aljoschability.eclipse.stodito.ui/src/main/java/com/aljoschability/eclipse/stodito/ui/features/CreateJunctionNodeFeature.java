package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateJunctionNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateJunctionNodeFeature(IFeatureProvider fp) {
		super(fp, "Junction Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		return StoditoFactory.eINSTANCE.createJunctionNode();
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.JUNCTION_NODE;
	}
}
