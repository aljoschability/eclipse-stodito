package com.aljoschability.eclipse.stodito.properties

import com.aljoschability.eclipse.core.properties.graphiti.GraphitiElementAdapter
import com.aljoschability.eclipse.core.properties.sections.AbstractPropertySection
import org.eclipse.swt.widgets.Composite
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory

class CommentableCommentSection extends AbstractPropertySection {
	new() {
		super(GraphitiElementAdapter::get)
	}

	override protected createWidgets(Composite parent, TabbedPropertySheetWidgetFactory factory) {
		println("createWidgets")
	}
}
