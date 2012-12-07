package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;
import com.aljoschability.eclipse.stodito.StructuredNode;

public class CreateStructuredNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateStructuredNodeFeature(IFeatureProvider fp) {
		super(fp, "Structured Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		StructuredNode bo = StoditoFactory.eINSTANCE.createStructuredNode();

		// collect existing names
		Collection<String> names = new LinkedHashSet<String>();
		for (ActivityNode node : nodes) {
			if (node instanceof StructuredNode) {
				names.add(((StructuredNode) node).getName());
			}
		}

		bo.setName(createUniqueName("Structure", names));

		return bo;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.STRUCTURED_NODE;
	}
}
