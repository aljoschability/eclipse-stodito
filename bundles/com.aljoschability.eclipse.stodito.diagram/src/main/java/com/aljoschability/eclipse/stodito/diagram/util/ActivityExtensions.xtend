package com.aljoschability.eclipse.stodito.diagram.util

import com.aljoschability.eclipse.core.graphiti.util.GraphitiExtensions
import com.aljoschability.eclipse.stodito.Activity
import org.eclipse.graphiti.features.context.ITargetContext

class ActivityExtensions {
	extension GraphitiExtensions = new GraphitiExtensions

	def Activity getActivity(ITargetContext context) {
		val element = context?.targetContainer?.element
		if (element instanceof Activity) {
			return element
		}
	}
}
