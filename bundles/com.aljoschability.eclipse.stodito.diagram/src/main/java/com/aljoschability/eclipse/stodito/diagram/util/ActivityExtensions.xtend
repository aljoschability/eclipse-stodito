package com.aljoschability.eclipse.stodito.diagram.util

import com.aljoschability.eclipse.core.graphiti.util.GraphitiExtensions
import com.aljoschability.eclipse.stodito.Activity
import org.eclipse.graphiti.features.context.ITargetContext

class ActivityExtensions {
	extension GraphitiExtensions = GraphitiExtensions::INSTANCE

	def Activity getActivity(ITargetContext context) {
		val element = context?.targetContainer?.bo
		if (element instanceof Activity) {
			return element
		}
	}
}
