package com.aljoschability.eclipse.stodito.ui.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import com.aljoschability.eclipse.core.graphiti.features.CoreCreateNodeFeature;
import com.aljoschability.eclipse.stodito.AttributeAssignment;
import com.aljoschability.eclipse.stodito.ObjectOccurrence;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CreateAttributeAssignmentFeature extends CoreCreateNodeFeature {
	public CreateAttributeAssignmentFeature(IFeatureProvider fp) {
		super(fp, "Assignment");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape cpe = context.getTargetContainer();
		EObject cbo = getBO(cpe);

		return cbo instanceof ObjectOccurrence;
	}

	@Override
	protected EObject createElement(ICreateContext context) {
		ContainerShape cpe = context.getTargetContainer();
		ObjectOccurrence cbo = (ObjectOccurrence) getBO(cpe);

		AttributeAssignment bo = StoditoFactory.eINSTANCE.createAttributeAssignment();

		cbo.getAssignments().add(bo);

		return bo;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.ATTRIBUTE_ASSIGNMENT;
	}
}
