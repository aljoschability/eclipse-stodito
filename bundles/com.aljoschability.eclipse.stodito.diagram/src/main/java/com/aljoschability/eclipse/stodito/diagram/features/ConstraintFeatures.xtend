package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateFeature
import com.aljoschability.eclipse.stodito.ObjectOccurrence
import com.aljoschability.eclipse.stodito.StoditoFactory
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

public class CreateConstraintFeature extends CoreCreateFeature {
	new(IFeatureProvider fp) {
		super(fp)

		name = "Constraint"
		description = "Create Constraint"
	}

	override canCreate(ICreateContext context) {
		val cpe = context.targetContainer
		val cbo = cpe.businessObjectForPictogramElement

		return cbo instanceof ObjectOccurrence
	}

	override protected createElement(ICreateContext context) {
		val cpe = context.getTargetContainer()
		val cbo = cpe.businessObjectForPictogramElement as ObjectOccurrence

		val bo = StoditoFactory.eINSTANCE.createConstraint();

		cbo.getConstraints().add(bo);

		return bo;
	}
}
