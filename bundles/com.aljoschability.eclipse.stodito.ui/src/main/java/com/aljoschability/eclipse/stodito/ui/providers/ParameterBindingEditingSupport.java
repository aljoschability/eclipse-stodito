package com.aljoschability.eclipse.stodito.ui.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor.EDataTypeCellEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.ui.dialogs.SelectParameterBindingDialog;

public class ParameterBindingEditingSupport extends EditingSupport {
	private final ParameterBinder binder;

	private final ICellEditorListener validationListener;

	private EDataTypeCellEditor eDataTypeCellEditor;
	private DialogCellEditor dialogCellEditor;

	private final SelectParameterBindingDialog dialog;

	public ParameterBindingEditingSupport(TableViewer viewer, ParameterBinder thebinder) {
		super(viewer);

		binder = thebinder;

		validationListener = new ICellEditorListener() {
			@Override
			public void applyEditorValue() {
				// nothing
			}

			@Override
			public void cancelEditor() {
				// nothing
			}

			@Override
			public void editorValueChanged(boolean oldValidState, boolean newValidState) {
				String text = null;
				if (!newValidState) {
					text = eDataTypeCellEditor.getErrorMessage();
				}
				binder.setError(text);
			}
		};

		dialog = new SelectParameterBindingDialog();
		dialogCellEditor = new DialogCellEditor((Composite) getViewer().getControl()) {
			@Override
			protected Object openDialogBox(Control control) {
				if (dialog.open() == Window.OK) {
					return dialog.getElement();
				}

				return null;
			}
		};
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		Composite parent = (Composite) getViewer().getControl();
		Parameter parameter = (Parameter) element;
		EClassifier type = parameter.getType();
		if (type instanceof EDataType) {
			List<Object> values = getComboBoxObjects((EDataType) type);
			if (!values.isEmpty()) {
				return new ExtendedComboBoxCellEditor(parent, values, (ILabelProvider) getViewer().getLabelProvider());
			}

			if (eDataTypeCellEditor != null) {
				eDataTypeCellEditor.removeListener(validationListener);
			}

			eDataTypeCellEditor = new EDataTypeCellEditor((EDataType) type, parent);
			eDataTypeCellEditor.addListener(validationListener);

			return eDataTypeCellEditor;
		}

		if (type instanceof EClass) {
			dialog.setType(((Parameter) element).getType());
			dialog.setSelectedElement((EObject) binder.getValue(parameter));
			dialog.setResource(binder.getResource());
			return dialogCellEditor;
		}
		return null;
	}

	private static List<Object> getComboBoxObjects(EDataType type) {
		// boolean
		if (type.getInstanceClass() == Boolean.class || type.getInstanceClass() == Boolean.TYPE) {
			return Arrays.asList(new Object[] { Boolean.FALSE, Boolean.TRUE });
		}

		// literals
		if (type instanceof EEnum) {
			List<Object> result = new ArrayList<Object>();
			for (EEnumLiteral literal : ((EEnum) type).getELiterals()) {
				result.add(literal.getInstance());
			}
			return result;
		}

		// facets
		Collection<String> literals = ExtendedMetaData.INSTANCE.getEnumerationFacet(type);
		if (!literals.isEmpty()) {
			List<Object> result = new ArrayList<Object>();
			for (String literal : literals) {
				result.add(EcoreUtil.createFromString(type, literal));
			}
			return result;
		}

		// base types
		EDataType base = ExtendedMetaData.INSTANCE.getBaseType(type);
		while (base != null) {
			if (base instanceof EEnum) {
				List<Object> result = new ArrayList<Object>();
				result.add(null);
				for (EEnumLiteral literal : ((EEnum) base).getELiterals()) {
					result.add(literal.getInstance());
				}
				return result;
			}

			base = ExtendedMetaData.INSTANCE.getBaseType(base);
		}

		return Collections.emptyList();
	}

	@Override
	protected boolean canEdit(Object element) {
		return element instanceof Parameter;
	}

	@Override
	protected Object getValue(Object element) {
		return binder.getValue((Parameter) element);
	}

	@Override
	protected void setValue(Object element, Object value) {
		binder.setValue((Parameter) element, value);
		getViewer().refresh(element);
	}
}
