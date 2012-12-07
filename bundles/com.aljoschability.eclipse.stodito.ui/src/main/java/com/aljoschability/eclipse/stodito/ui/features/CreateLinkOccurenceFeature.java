package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateEdgeFeature;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateLinkOccurenceFeature extends CoreCreateEdgeFeature {
	public CreateLinkOccurenceFeature(IFeatureProvider fp) {
		super(fp, "Link");
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.LINK;
	}
}
