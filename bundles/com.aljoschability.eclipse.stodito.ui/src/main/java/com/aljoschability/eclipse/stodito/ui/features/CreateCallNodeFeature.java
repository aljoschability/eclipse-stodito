package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.CallNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateCallNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateCallNodeFeature(IFeatureProvider fp) {
		super(fp, "Call Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		CallNode bo = StoditoFactory.eINSTANCE.createCallNode();

		// collect existing names
		Collection<String> names = new LinkedHashSet<String>();
		for (ActivityNode node : nodes) {
			if (node instanceof CallNode) {
				names.add(((CallNode) node).getName());
			}
		}

		bo.setName(createUniqueName("Call", names));

		return bo;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.CALL_NODE;
	}
}
