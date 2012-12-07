package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;

import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StatementNode;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateStatementNodeFeature extends AbstractCreateActivityNodeFeature {
	public CreateStatementNodeFeature(IFeatureProvider fp) {
		super(fp, "Statement Node");
	}

	@Override
	protected ActivityNode createNode(Collection<ActivityNode> nodes) {
		StatementNode bo = StoditoFactory.eINSTANCE.createStatementNode();

		// collect existing names
		Collection<String> names = new LinkedHashSet<String>();
		for (ActivityNode node : nodes) {
			if (node instanceof StatementNode) {
				names.add(((StatementNode) node).getName());
			}
		}

		bo.setName(createUniqueName("Statement", names));

		return bo;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.STATEMENT_NODE;
	}
}
