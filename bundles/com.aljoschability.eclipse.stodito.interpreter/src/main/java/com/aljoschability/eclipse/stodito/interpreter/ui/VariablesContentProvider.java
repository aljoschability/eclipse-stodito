package com.aljoschability.eclipse.stodito.interpreter.ui;

import com.aljoschability.core.emf.providers.ContainmentContentProvider;
import com.aljoschability.eclipse.stodito.evaluator.Variable;

public class VariablesContentProvider extends ContainmentContentProvider {
	@Override
	public Object[] getChildren(Object element) {
		if (element instanceof Variable) {
			Object value = ((Variable) element).getValue();
			if (value != null) {
				return new Object[] { value };
			}
		}
		return super.getChildren(element);
	}
}
