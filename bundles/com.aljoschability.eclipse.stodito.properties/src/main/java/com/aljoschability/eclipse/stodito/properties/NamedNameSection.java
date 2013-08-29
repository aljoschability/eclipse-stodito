package com.aljoschability.eclipse.stodito.properties;

import org.eclipse.emf.ecore.EStructuralFeature;

import com.aljoschability.eclipse.core.properties.graphiti.GraphitiElementAdapter;
import com.aljoschability.eclipse.core.properties.sections.AbstractTextSection;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class NamedNameSection extends AbstractTextSection {
	public NamedNameSection() {
		super(GraphitiElementAdapter.get());
	}

	@Override
	protected EStructuralFeature getFeature() {
		return StoditoPackage.Literals.NAMED__NAME;
	}
}
