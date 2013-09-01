package com.aljoschability.eclipse.stodito.diagram.features;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateNodeFeature
import com.aljoschability.eclipse.stodito.ObjectOccurrence
import com.aljoschability.eclipse.stodito.StoditoFactory
import com.aljoschability.eclipse.stodito.StoditoPackage
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext

public class CreateConstraintFeature extends CoreCreateNodeFeature {
	new(IFeatureProvider fp) {
		super(fp, "Constraint")
	}

	override canCreate(ICreateContext context) {
		val cpe = context.getTargetContainer();
		val cbo = getBO(cpe);

		return cbo instanceof ObjectOccurrence;
	}

	override protected createElement(ICreateContext context) {
		val cpe = context.getTargetContainer()
		val cbo = getBO(cpe) as ObjectOccurrence

		val bo = StoditoFactory.eINSTANCE.createConstraint();

		cbo.getConstraints().add(bo);

		return bo;
	}

	override protected getEClass() {
		return StoditoPackage.Literals.CONSTRAINT;
	}
}