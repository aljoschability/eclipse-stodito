package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateFlowFinalNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateFlowFinalNodeFeature(IFeatureProvider fp) {
		super(fp, "Flow Final Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		return StoditoFactory.eINSTANCE.createFlowFinalNode();
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.FLOW_FINAL_NODE;
	}
}
