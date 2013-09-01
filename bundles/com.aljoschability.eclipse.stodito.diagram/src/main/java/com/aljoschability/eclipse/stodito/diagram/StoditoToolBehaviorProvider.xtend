package com.aljoschability.eclipse.stodito.diagram

import com.aljoschability.eclipse.core.graphiti.editors.CoreToolBehaviorProvider
import com.aljoschability.eclipse.stodito.StoditoPackage
import com.aljoschability.eclipse.stodito.diagram.features.CallNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.ControlFlowCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateAttributeAssignmentFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateCollectionOccurrenceFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateConstraintFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateInclusionLinkOccurenceFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateLinkOccurenceFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreateObjectOccurrenceFeature
import com.aljoschability.eclipse.stodito.diagram.features.CreatePathFeature
import com.aljoschability.eclipse.stodito.diagram.features.FinalNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.InitialNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.JunctionNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.StatementNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.StoryNodeCreateFeature
import com.aljoschability.eclipse.stodito.diagram.features.StructuredNodeCreateFeature
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry
import org.eclipse.graphiti.palette.impl.PaletteSeparatorEntry

class StoditoToolBehaviorProvider extends CoreToolBehaviorProvider {
	new(IDiagramTypeProvider dtp) {
		super(dtp)
	}

	override getPalette() {
		val entries = newArrayList

		entries += createActivityEntry

		entries += createPatternEntry

		return entries
	}

	def private IPaletteCompartmentEntry createActivityEntry() {
		val entry = new PaletteCompartmentEntry("Activity", StoditoPackage.Literals.ACTIVITY.getName())

		// functional nodes
		entry.toolEntries += new StoryNodeCreateFeature(featureProvider).creationTool
		entry.toolEntries += new CallNodeCreateFeature(featureProvider).creationTool
		entry.toolEntries += new StatementNodeCreateFeature(featureProvider).creationTool
		entry.toolEntries += new StructuredNodeCreateFeature(featureProvider).creationTool

		// separator
		entry.toolEntries += new PaletteSeparatorEntry()

		// edges
		entry.toolEntries += new ControlFlowCreateFeature(featureProvider).creationTool

		// separator
		entry.toolEntries += new PaletteSeparatorEntry()

		// control nodes
		entry.toolEntries += new InitialNodeCreateFeature(featureProvider).creationTool
		entry.toolEntries += new JunctionNodeCreateFeature(featureProvider).creationTool
		entry.toolEntries += new FinalNodeCreateFeature(featureProvider).creationTool

		return entry
	}

	def private IPaletteCompartmentEntry createPatternEntry() {
		val entry = new PaletteCompartmentEntry("Pattern", StoditoPackage.Literals.STORY_NODE.getName())

		// occurrences
		entry.toolEntries += new CreateObjectOccurrenceFeature(featureProvider).creationTool
		entry.toolEntries += new CreateCollectionOccurrenceFeature(featureProvider).creationTool

		// separator
		entry.toolEntries += new PaletteSeparatorEntry()

		// edges
		entry.toolEntries += new CreateLinkOccurenceFeature(featureProvider).creationTool
		entry.toolEntries += new CreateInclusionLinkOccurenceFeature(featureProvider).creationTool
		entry.toolEntries += new CreatePathFeature(featureProvider).creationTool

		// separator
		entry.toolEntries += new PaletteSeparatorEntry()

		// elements
		entry.toolEntries += new CreateConstraintFeature(featureProvider).creationTool
		entry.toolEntries += new CreateAttributeAssignmentFeature(featureProvider).creationTool

		return entry
	}
}
