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
			val contents = addContainerShape[
				name = "contents"
				active = true
				val contentsRectangle = addRectangle[
					position = #[5, 30]
					size = context.size(200, 100)
				]
			]
		]
	}

	override canDirectEdit(IDirectEditingContext context) {
		context.graphicsAlgorithm
		
		return false
	}
	
	override isElement(EObject element) {
		return element instanceof StoryNode
	}
}
