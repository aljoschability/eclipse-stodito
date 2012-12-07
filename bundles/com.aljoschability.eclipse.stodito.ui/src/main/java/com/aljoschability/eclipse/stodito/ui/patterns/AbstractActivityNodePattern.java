package com.aljoschability.eclipse.stodito.ui.patterns;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import com.aljoschability.eclipse.core.graphiti.pattern.CorePattern;

public abstract class AbstractActivityNodePattern extends CorePattern {
	@Override
	public PictogramElement add(IAddContext context) {
		ContainerShape pe = PE.createContainerShape(context.getTargetContainer(), true);

		// create PE
		// create (invisible) frame
		// create
		// create rectangle

		GraphicsAlgorithmContainer ga = createNodeContainer(pe);

		return pe;
	}

	protected GraphicsAlgorithmContainer createNodeContainer(ContainerShape pe) {
		GraphicsAlgorithmContainer ga = GA.createRectangle(pe);

		return ga;
	}

	protected abstract String getHeaderImageKey();

	protected abstract String getHeaderTextValue();
}
