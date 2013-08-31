package com.aljoschability.eclipse.stodito.ui.patterns;

import com.aljoschability.eclipse.core.graphiti.GraphitiExtensions
import com.aljoschability.eclipse.core.graphiti.patterns.CorePattern
import com.aljoschability.eclipse.stodito.StoditoPackage
import com.aljoschability.eclipse.stodito.StoryNode
import org.eclipse.emf.ecore.EObject
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.util.IColorConstant
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm
import org.eclipse.graphiti.services.Graphiti
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.features.context.IAreaContext
import org.eclipse.graphiti.features.context.IDirectEditingContext
import org.eclipse.graphiti.func.IDirectEditing

class StoryNodeExtensions {
	def String getSymbol() {
		return StoditoPackage.Literals::STORY_NODE.name
	}

	def ContainerShape getContainer(IAddContext context) {
		return context.targetContainer
	}

	def int[] size(IAreaContext context, int minWidth, int minHeight) {
		var width = minWidth
		if (width < context.width) {
			width = context.width
		}

		var height = minHeight
		if (height < context.height) {
			height = context.height
		}

		return #[width, height]
	}

	def int[] position(IAreaContext context) {
		return #[context.x, context.y]
	}
}

class StoryNodePattern extends CorePattern {
	extension GraphitiExtensions = new GraphitiExtensions
	extension StoryNodeExtensions = new StoryNodeExtensions

	override add(IAddContext context) {
		val nameFont = manageFont("Segoe UI", 10, false, true)

		return addContainerShape [
			container = context.container
			active = true
			link = context.newObject
			val frame = addRoundedRectangle[
				background = IColorConstant::WHITE
				foreground = IColorConstant::BLACK
				radius = 16
				position = context.position
				size = context.size(200, 100)
				val titleSymbol = addImage[
					name = "title.symbol"
					id = symbol
					position = #[7, 7]
					size = #[16, 16]
				]
				val titleText = addText[
					name = "title.text"
					position = #[27, 5]
					width = parentGraphicsAlgorithm.width - 54
					foreground = IColorConstant::BLACK
					height = 20
					font = nameFont
					value = "Test Calibri Font"
				]
				val titleSeparator = addPolyline[
					name = "title.separator"
					addPoint(0, 29)
					addPoint(parentGraphicsAlgorithm.width, 29)
				]
			]
			val contents = it.addContainerShape [
				name = "contents"
				active = false
				val contentsRectangle = addRectangle[
					position = #[5, 34]
					width = context.size(200, 100).get(0) - 10
					height = context.size(200, 100).get(1) - 39
				]
			]
		]
	}

	override getInitialValue(IDirectEditingContext context) {
		if (isPatternControlled(context.pictogramElement)) {
			val name = Graphiti::getPeService.getPropertyValue(context.graphicsAlgorithm, "name")
			if (name == "title.text") {
				val bo = getBusinessObject(context.pictogramElement) as StoryNode
				return bo.name
			}
		}

		super.getInitialValue(context)
	}

	override getEditingType() {
		IDirectEditing::TYPE_TEXT
	}

	override canDirectEdit(IDirectEditingContext context) {
		if (isPatternControlled(context.pictogramElement)) {
			val name = Graphiti::getPeService.getPropertyValue(context.graphicsAlgorithm, "name")
			if (name == "title.text") {
				return true
			}
		}

		return false
	}

	override isElement(EObject element) {
		return element instanceof StoryNode
	}
}