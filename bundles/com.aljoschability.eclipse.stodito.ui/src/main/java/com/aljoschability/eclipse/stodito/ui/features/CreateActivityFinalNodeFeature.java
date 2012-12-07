package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateActivityFinalNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateActivityFinalNodeFeature(IFeatureProvider fp) {
		super(fp, "Final Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		return StoditoFactory.eINSTANCE.createActivityFinalNode();
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.ACTIVITY_FINAL_NODE;
	}
}
