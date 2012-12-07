package com.aljoschability.eclipse.stodito.ui.patterns;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.IColorConstant;

import com.aljoschability.eclipse.core.graphiti.pattern.CorePattern;
import com.aljoschability.eclipse.core.graphiti.util.Area;
import com.aljoschability.eclipse.core.graphiti.util.FontDescription;
import com.aljoschability.eclipse.core.graphiti.util.GraphitiUtil;
import com.aljoschability.eclipse.core.graphiti.util.Size;
import com.aljoschability.eclipse.stodito.CallNode;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CallNodePattern extends CorePattern {
	private static final FontDescription NAME_FONT = FontDescription.make(FONT_DEFAULT, 10, true);
	private static final FontDescription EXPRESSION_FONT = FontDescription.make(FONT_MONO, 9);

	private static final String IMAGE_KEY = StoditoPackage.Literals.CALL_NODE.getName();

	@Override
	public PictogramElement add(IAddContext context) {
		// create and link PE
		CallNode bo = (CallNode) context.getNewObject();
		ContainerShape pe = PE.createContainerShape(context.getTargetContainer(), true);
		link(pe, bo);

		// get text data
		Font nameFont = NAME_FONT.get(getDiagram());
		String nameValue = getNameText(bo);
		Size nameSize = getSize(nameFont, nameValue).add(54, 9);

		Font expressionFont = EXPRESSION_FONT.get(getDiagram());
		String expressionValue = getExpressionText(bo);
		Size expressionSize = getSize(expressionFont, expressionValue).add(10, 9);

		// get sizes
		Size minSize = Size.asRows(nameSize, expressionSize).addHeight(1);
		Area gaArea = getArea(context, minSize);

		// create GA
		RoundedRectangle ga = GA.createPlainRoundedRectangle(pe, 16, 16);
		ga.setBackground(manageColor(IColorConstant.WHITE));
		ga.setForeground(manageColor(IColorConstant.BLACK));

		ga.setX(gaArea.getX());
		ga.setY(gaArea.getY());
		ga.setWidth(gaArea.getWidth());
		ga.setHeight(gaArea.getHeight());

		{
			Image image = GA.createImage(ga, IMAGE_KEY);

			image.setX(5 + 2);
			image.setY(5 + 2);
			image.setWidth(16);
			image.setHeight(16);
		}

		{
			// name
			Text text = GA.createPlainText(ga, nameValue);
			text.setFont(nameFont);

			text.setFilled(false);
			text.setForeground(manageColor(IColorConstant.BLACK));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);

			text.setX(27);
			text.setY(5);
			text.setWidth(ga.getWidth() - 54);
			text.setHeight(20);
		}

		{
			// separator
			int y = nameSize.getHeight();
			int[] coords = new int[] { 0, y, ga.getWidth(), y };
			Polyline separator = GA.createPlainPolyline(ga, coords);
			separator.setForeground(manageColor(IColorConstant.BLACK));
		}

		{
			// expression
			Text text = GA.createPlainText(ga, expressionValue);

			text.setFont(expressionFont);
			text.setFilled(false);
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setForeground(manageColor(IColorConstant.BLACK));

			text.setX(5);
			text.setY(30);
			text.setWidth(ga.getWidth() - 10);
			text.setHeight(15);
		}

		return pe;
	}

	private Size getSize(Font font, String value) {
		return GraphitiUtil.getSize(font, value);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		CallNode bo = (CallNode) getBO(pe);
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();

		// check name text
		Text nameText = getNameText(pe);
		String nameValue = getNameText(bo);
		if (differs(nameText, nameValue)) {
			return Reason.createTrueReason("The name is out of date.");
		}

		// check expression text
		Text expressionText = getExpressionText(pe);
		String expressionValue = getExpressionText(bo);
		if (differs(expressionText, expressionValue)) {
			return Reason.createTrueReason("The expression is out of date.");
		}

		// check minimum width
		Size gaSize = getSize(ga);
		Size nameSize = getSize(nameText).addWidth(50);
		Size expressionSize = getSize(expressionText).addWidth(10);
		if (nameSize.wider(gaSize) || expressionSize.wider(gaSize)) {
			// return Reason.createTrueReason("The node is too small.");
		}

		// check minimum height
		if (Size.asRows(nameSize, expressionSize).addHeight(10).getHeight() < 70) {
			// return Reason.createTrueReason("The node is too small.");
		}

		// check separator size
		Polyline separator = getSeparator(pe);
		Size separatorSize = getSize(separator);
		if (!separatorSize.sameWidth(gaSize)) {
			return Reason.createTrueReason("The separator has the wrong length.");
		}

		// check name size
		// check expression size

		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		CallNode bo = (CallNode) getBO(pe);
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();

		// check name text
		Text nameText = getNameText(pe);
		String nameValue = getNameText(bo);
		if (differs(nameText, nameValue)) {
			nameText.setValue(nameValue);
		}

		// check expression text
		Text expressionText = getExpressionText(pe);
		String expressionValue = getExpressionText(bo);
		if (differs(expressionText, expressionValue)) {
			expressionText.setValue(expressionValue);
		}

		// check GA size
		Size gaSize = getSize(ga);
		Size nameSize = getSize(nameText).addWidth(50);
		Size expressionSize = getSize(expressionText).addWidth(10);
		if (nameSize.getWidth() > gaSize.getWidth()) {
			ga.setWidth(nameSize.getWidth());
			gaSize = getSize(ga);
		}
		if (expressionSize.getWidth() > gaSize.getWidth()) {
			ga.setWidth(expressionSize.getWidth());
			gaSize = getSize(ga);
		}

		// check separator size
		Polyline separator = getSeparator(pe);
		Size separatorSize = getSize(separator);
		if (separatorSize.getWidth() != gaSize.getWidth()) {
			separator.getPoints().get(1).setX(ga.getWidth());
		}

		// check name size
		// check expression size

		return true;
	}

	@Override
	protected EClass getEClass() {
		return StoditoPackage.Literals.CALL_NODE;
	}

	@Override
	protected boolean isBO(Object bo) {
		return bo instanceof CallNode;
	}

	private Text getNameText(PictogramElement pe) {
		return (Text) pe.getGraphicsAlgorithm().getGraphicsAlgorithmChildren().get(1);
	}

	private String getNameText(CallNode bo) {
		String text = bo.getName();
		return text == null ? TEXT_EMPTY : text;
	}

	private Polyline getSeparator(PictogramElement pe) {
		return (Polyline) pe.getGraphicsAlgorithm().getGraphicsAlgorithmChildren().get(2);
	}

	private Text getExpressionText(PictogramElement pe) {
		return (Text) pe.getGraphicsAlgorithm().getGraphicsAlgorithmChildren().get(3);
	}

	private String getExpressionText(CallNode bo) {
		// TODO: get expression text
		String text = "<java.util.Math.abs(x,y)>";
		text = "expr";
		return text == null ? TEXT_EMPTY : text;
	}
}
