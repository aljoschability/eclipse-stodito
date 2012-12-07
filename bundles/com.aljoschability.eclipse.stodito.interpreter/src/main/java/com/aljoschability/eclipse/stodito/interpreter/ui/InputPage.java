package com.aljoschability.eclipse.stodito.interpreter.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.aljoschability.core.emf.providers.ComposedAdapterFactoryLabelProvider;
import com.aljoschability.core.ui.CoreImages;
import com.aljoschability.eclipse.stodito.Parameter;
import com.aljoschability.eclipse.stodito.ui.dialogs.SelectWorkspaceResourceDialog;
import com.aljoschability.eclipse.stodito.ui.providers.ParameterBinder;
import com.aljoschability.eclipse.stodito.ui.providers.ParameterBindingEditingSupport;

public class InputPage extends WizardPage implements ParameterBinder {
	private ComposedAdapterFactoryLabelProvider aflp;
	private SelectWorkspaceResourceDialog resourceDialog;

	private Text resourceText;
	private Table parametersTable;
	private ParameterBindingEditingSupport editingSupport;

	private Button resourceButton;

	private Resource resource;

	protected InputPage() {
		super(InputPage.class.getSimpleName());

		setTitle("Configure Input");
		setDescription("Select context and arguments for the activity.");

		aflp = new ComposedAdapterFactoryLabelProvider();
		resourceDialog = new SelectWorkspaceResourceDialog();
	}

	@Override
	public void createControl(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(page);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(page);

		// resource
		Composite resourceComposite = new Composite(page, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(resourceComposite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(resourceComposite);

		Label resourceLabel = new Label(resourceComposite, SWT.TRAIL);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(resourceLabel);
		resourceLabel.setText("Resource:");

		resourceText = new Text(resourceComposite, SWT.BORDER | SWT.SINGLE | SWT.LEAD);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(resourceText);
		resourceText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				final String path = resourceText.getText();
				try {
					getContainer().run(true, false, new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask(String.format("Loading resource '%1s'...", path),
									IProgressMonitor.UNKNOWN);
							URI uri = URI.createPlatformResourceURI(path, true);
							resource = getWizard().getResourceSet().getResource(uri, true);
						}
					});
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});

		resourceButton = new Button(resourceComposite, SWT.PUSH);
		GridDataFactory.fillDefaults().applyTo(resourceButton);
		resourceButton.setText("Find");
		resourceButton.setImage(CoreImages.get(CoreImages.FIND));
		resourceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (resourceDialog.open() == Window.OK) {
					resourceText.setText(resourceDialog.getElement().getFullPath().toString());
				}
			}
		});

		// parameters
		Group parameterGroup = new Group(page, SWT.NONE);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(parameterGroup);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parameterGroup);
		parameterGroup.setText("Parameters");

		TableViewer tableViewer = new TableViewer(parameterGroup, SWT.BORDER | SWT.FULL_SELECTION);
		parametersTable = tableViewer.getTable();
		GridDataFactory.fillDefaults().grab(true, true).applyTo(parametersTable);
		parametersTable.setLinesVisible(true);
		parametersTable.setHeaderVisible(true);

		tableViewer.setContentProvider(new ArrayContentProvider());
		TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.LEAD);
		nameColumn.getColumn().setWidth(200);
		nameColumn.getColumn().setText("Parameter");
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public Image getImage(Object element) {
				return aflp.getImage(element);
			}

			@Override
			public String getText(Object element) {
				return "Parameter " + element;
			}
		});

		TableViewerColumn valueColumn = new TableViewerColumn(tableViewer, SWT.LEAD);
		valueColumn.getColumn().setWidth(200);
		valueColumn.getColumn().setText("Value");
		valueColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public Image getImage(Object element) {
				return aflp.getImage(getBinding(element));
			}

			@Override
			public String getText(Object element) {
				return aflp.getText(getBinding(element));
			}
		});

		editingSupport = new ParameterBindingEditingSupport(tableViewer, this);
		valueColumn.setEditingSupport(editingSupport);

		tableViewer.setInput(getWizard().getBindings().keySet());

		createWarnings();

		setControl(page);
	}

	@Override
	public void setValue(Parameter element, Object value) {
		getWizard().getBindings().put(element, value);
		createWarnings();
	}

	private void createWarnings() {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (Parameter parameter : getWizard().getBindings().keySet()) {
			if (getValue(parameter) == null) {
				builder.append('\'');
				builder.append(parameter.getName());
				builder.append('\'');
				builder.append(',');
				builder.append(' ');
				count++;
			}
		}

		if (count > 0) {
			int size = builder.length();
			String format = "The parameters %1s are not set.";
			if (count == 1) {
				format = "The parameter %1s is not set.";
			}
			String list = builder.substring(0, size - 2);
			setMessage(String.format(format, list), IMessageProvider.WARNING);
		} else {
			setMessage(null);
		}
	}

	@Override
	public Object getValue(Parameter parameter) {
		return getWizard().getBindings().get(parameter);
	}

	private Object getBinding(Object element) {
		return getWizard().getBindings().get(element);
	}

	@Override
	public InterpreteActivityWizard getWizard() {
		return (InterpreteActivityWizard) super.getWizard();
	}

	@Override
	public void dispose() {
		aflp.dispose();
		aflp = null;
		super.dispose();
	}

	@Override
	public Resource getResource() {
		return resource;
	}

	@Override
	public void setError(String text) {
		if (text == null) {
			setErrorMessage(null);
		} else {
			setErrorMessage(text);
		}
	}
}
