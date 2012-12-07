package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreatePathFeature extends CoreCreateEdgeFeature {
	public CreatePathFeature(IFeatureProvider fp) {
		super(fp, "Path");
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.PATH;
	}
}
