package com.aljoschability.eclipse.stodito.properties

import com.aljoschability.eclipse.core.graphiti.properties.AbstractPropertySection
import org.eclipse.swt.widgets.Composite
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory

class CommentableCommentSection extends AbstractPropertySection {
	override protected createWidgets(Composite parent, TabbedPropertySheetWidgetFactory factory) {
		println("createWidgets")
	}
}
