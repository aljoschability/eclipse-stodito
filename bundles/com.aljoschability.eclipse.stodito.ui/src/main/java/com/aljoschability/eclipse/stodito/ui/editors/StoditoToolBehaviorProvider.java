package com.aljoschability.eclipse.stodito.ui.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.PaletteSeparatorEntry;

import com.aljoschability.eclipse.core.graphiti.editors.CoreToolBehaviorProvider;
import com.aljoschability.eclipse.stodito.StoditoPackage;
import com.aljoschability.eclipse.stodito.ui.features.CreateActivityFinalNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateAttributeAssignmentFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateCallNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateCollectionOccurrenceFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateConstraintFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateControlFlowFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateFlowFinalNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateInclusionLinkOccurenceFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateInitialNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateJunctionNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateLinkOccurenceFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateObjectOccurrenceFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreatePathFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateStatementNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateStoryNodeFeature;
import com.aljoschability.eclipse.stodito.ui.features.CreateStructuredNodeFeature;

public class StoditoToolBehaviorProvider extends CoreToolBehaviorProvider {
	public StoditoToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	protected List<IPaletteCompartmentEntry> createPaletteEntries() {
		List<IPaletteCompartmentEntry> entries = new ArrayList<IPaletteCompartmentEntry>();

		entries.add(createActivityEntry());

		entries.add(createPatternEntry());

		return entries;
	}

	private PaletteCompartmentEntry createActivityEntry() {
		PaletteCompartmentEntry entry = new PaletteCompartmentEntry("Activity",
				StoditoPackage.Literals.ACTIVITY.getName());

		// functional nodes
		entry.addToolEntry(createNodeEntry(new CreateStoryNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateCallNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateStatementNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateStructuredNodeFeature(getFeatureProvider())));

		// separator
		entry.addToolEntry(new PaletteSeparatorEntry());

		// edges
		entry.addToolEntry(createEdgeEntry(new CreateControlFlowFeature(getFeatureProvider())));

		// separator
		entry.addToolEntry(new PaletteSeparatorEntry());

		// control nodes
		entry.addToolEntry(createNodeEntry(new CreateInitialNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateJunctionNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateFlowFinalNodeFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateActivityFinalNodeFeature(getFeatureProvider())));

		return entry;
	}

	private PaletteCompartmentEntry createPatternEntry() {
		PaletteCompartmentEntry entry = new PaletteCompartmentEntry("Pattern",
				StoditoPackage.Literals.STORY_NODE.getName());

		// occurrences
		entry.addToolEntry(createNodeEntry(new CreateObjectOccurrenceFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateCollectionOccurrenceFeature(getFeatureProvider())));

		// separator
		entry.addToolEntry(new PaletteSeparatorEntry());

		// edges
		entry.addToolEntry(createEdgeEntry(new CreateLinkOccurenceFeature(getFeatureProvider())));
		entry.addToolEntry(createEdgeEntry(new CreateInclusionLinkOccurenceFeature(getFeatureProvider())));
		entry.addToolEntry(createEdgeEntry(new CreatePathFeature(getFeatureProvider())));

		// separator
		entry.addToolEntry(new PaletteSeparatorEntry());

		// elements
		entry.addToolEntry(createNodeEntry(new CreateConstraintFeature(getFeatureProvider())));
		entry.addToolEntry(createNodeEntry(new CreateAttributeAssignmentFeature(getFeatureProvider())));

		return entry;
	}
}
