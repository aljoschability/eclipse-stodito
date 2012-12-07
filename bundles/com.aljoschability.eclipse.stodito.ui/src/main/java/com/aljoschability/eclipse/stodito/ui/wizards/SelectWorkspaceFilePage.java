package com.aljoschability.eclipse.stodito.ui.wizards;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.model.WorkbenchViewerComparator;

public class SelectWorkspaceFilePage extends WizardPage {
	private Collection<String> extensions;
	private String path;
	private String extension;

	public SelectWorkspaceFilePage(Collection<String> extensions) {
		super(SelectWorkspaceFilePage.class.getSimpleName());

		this.extensions = extensions;

		setTitle("Wizard Page title");
		setDescription("Wizard Page description");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(1, false));

		final TreeViewer viewer = new TreeViewer(container, SWT.BORDER);
		Tree tree = viewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree.setBounds(0, 0, 85, 85);
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		viewer.setContentProvider(new WorkbenchContentProvider());

		viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		viewer.setComparator(new WorkbenchViewerComparator());
		viewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {
				if (element instanceof IFile) {
					return extensions.contains(((IFile) element).getFileExtension());
				}

				if (element instanceof IContainer) {
					try {
						for (IResource child : ((IContainer) element).members()) {
							if (select(viewer, element, child)) {
								return true;
							}
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
				return false;
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				extension = null;
				path = null;
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				if (selection.size() == 1) {
					Object selected = selection.getFirstElement();
					if (selected instanceof IFile) {
						IFile file = (IFile) selected;
						extension = file.getFileExtension();
						path = file.getFullPath().toString();
					}
				}
			}
		});
	}

	public String getExtension() {
		return extension;
	}

	public String getPath() {
		return path;
	}
}
