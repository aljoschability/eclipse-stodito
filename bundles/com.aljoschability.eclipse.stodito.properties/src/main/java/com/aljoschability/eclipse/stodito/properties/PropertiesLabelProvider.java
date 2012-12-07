package com.aljoschability.eclipse.stodito.properties;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.aljoschability.core.emf.providers.ComposedAdapterFactoryLabelProvider;
import com.aljoschability.eclipse.core.properties.graphiti.GraphitiElementAdapter;

public class PropertiesLabelProvider extends LabelProvider {
	private final ComposedAdapterFactoryLabelProvider labelProvider;

	public PropertiesLabelProvider() {
		labelProvider = new ComposedAdapterFactoryLabelProvider();
	}

	@Override
	public void dispose() {
		labelProvider.dispose();

		super.dispose();
	}

	@Override
	public String getText(Object element) {
		return labelProvider.getText(GraphitiElementAdapter.get().getElement(element));
	}

	@Override
	public Image getImage(Object element) {
		return labelProvider.getImage(GraphitiElementAdapter.get().getElement(element));
	}
}
