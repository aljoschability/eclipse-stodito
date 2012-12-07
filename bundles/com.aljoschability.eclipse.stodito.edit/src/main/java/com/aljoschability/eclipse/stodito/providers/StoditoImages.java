package com.aljoschability.eclipse.stodito.providers;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.eclipse.stodito.StoditoPackage;

public class StoditoImages extends AbstractImageProvider {
	public static final Image get(String key) {
		return GraphitiUi.getImageService().getImageForId(StoditoPackage.eNS_URI, key);
	}

	@Override
	protected void addAvailableImages() {
		add(StoditoPackage.Literals.ACTIVITY_FINAL_NODE);
		add(StoditoPackage.Literals.ACTIVITY);
		add(StoditoPackage.Literals.ARITHMETIC_EXPRESSION);
		add(StoditoPackage.Literals.ATTRIBUTE_ASSIGNMENT);
		add(StoditoPackage.Literals.CALL_EXPRESSION);
		add(StoditoPackage.Literals.CALL_NODE);
		add(StoditoPackage.Literals.COLLECTION_OCCURRENCE);
		add(StoditoPackage.Literals.COLLECTION_SIZE_EXPRESSION);
		add(StoditoPackage.Literals.COMPARISON_EXPRESSION);
		add(StoditoPackage.Literals.CONSTRAINT);
		add(StoditoPackage.Literals.CONTROL_FLOW);
		add(StoditoPackage.Literals.FLOW_FINAL_NODE);
		add(StoditoPackage.Literals.INCLUSION_LINK);
		add(StoditoPackage.Literals.INITIAL_NODE);
		add(StoditoPackage.Literals.JUNCTION_NODE);
		add(StoditoPackage.Literals.LINK);
		add(StoditoPackage.Literals.LITERAL_EXPRESSION);
		add(StoditoPackage.Literals.LOGIC_EXPRESSION);
		add(StoditoPackage.Literals.OBJECT_ATTRIBUTE_EXPRESSION);
		add(StoditoPackage.Literals.OBJECT_OCCURRENCE_EXPRESSION);
		add(StoditoPackage.Literals.OBJECT_OCCURRENCE);
		add(StoditoPackage.Literals.OCL_EXPRESSION);
		add(StoditoPackage.Literals.OPAQUE_CALLABLE);
		add(StoditoPackage.Literals.PARAMETER_BINDING);
		add(StoditoPackage.Literals.PARAMETER);
		add(StoditoPackage.Literals.PATH);
		add(StoditoPackage.Literals.PATTERN_PART);
		add(StoditoPackage.Literals.PRIMITIVE_OCCURRENCE_EXPRESSION);
		add(StoditoPackage.Literals.PRIMITIVE_OCCURRENCE);
		add(StoditoPackage.Literals.SAME_LINK);
		add(StoditoPackage.Literals.STATEMENT_NODE);
		add(StoditoPackage.Literals.STORY_NODE);
		add(StoditoPackage.Literals.STRUCTURED_NODE);
		add(StoditoPackage.Literals.UNARY_EXPRESSION);
	}

	private void add(EClass eClass) {
		addImageFilePath(eClass.getName(), "icons/" + eClass.getName() + ".png");
	}
}
