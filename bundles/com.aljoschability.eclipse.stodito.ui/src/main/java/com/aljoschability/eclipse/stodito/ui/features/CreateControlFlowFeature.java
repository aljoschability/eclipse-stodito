package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateControlFlowFeature extends CoreCreateEdgeFeature {
	public CreateControlFlowFeature(IFeatureProvider fp) {
		super(fp, "Control Flow");
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.CONTROL_FLOW;
	}
}
