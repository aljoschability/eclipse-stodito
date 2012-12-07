package com.aljoschability.eclipse.stodito.ui.features;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateNodeFeature;
import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.StructuredNode;

public abstract class AbstractCreateActivityNodeFeature extends CoreCreateNodeFeature {
	public AbstractCreateActivityNodeFeature(IFeatureProvider fp, String name) {
		super(fp, name);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape pe = context.getTargetContainer();
		EObject bo = getBO(pe);

		return bo instanceof Activity || bo instanceof StructuredNode;
	}

	@Override
	protected EObject createElement(ICreateContext context) {
		ContainerShape cpe = context.getTargetContainer();
		EObject cbo = getBO(cpe);

		if (cbo instanceof Activity) {
			ActivityNode bo = createNode(((Activity) cbo).getNodes());
			((Activity) cbo).getNodes().add(bo);
			return bo;
		} else if (cbo instanceof StructuredNode) {
			ActivityNode bo = createNode(((StructuredNode) cbo).getNodes());
			((StructuredNode) cbo).getNodes().add(bo);
			return bo;
		}

		return null;
	}

	protected static String createUniqueName(String prefix, Collection<String> names) {
		String name = prefix;

		int i = 1;
		while (names.contains(name)) {
			name = prefix + i;
			i++;
		}

		return name;
	}

	protected abstract ActivityNode createNode(Collection<ActivityNode> nodes);
}
