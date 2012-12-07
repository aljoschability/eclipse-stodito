package com.aljoschability.eclipse.stodito.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.util.Importer;
import com.aljoschability.eclipse.stodito.util.ImporterUtil;

public class ImportWizard extends Wizard implements IImportWizard {
	private ResourceSet resourceSet;
	private IStructuredSelection selection;

	private SelectWorkspaceFilePage page;
	private Map<String, Importer> importers;

	public ImportWizard() {
		importers = ImporterUtil.getImporters();
	}

	@Override
	public void addPages() {
		addPage(page = new SelectWorkspaceFilePage(importers.keySet()));
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public boolean performFinish() {
		final String extension = page.getExtension();
		final String path = page.getPath();

		// create operation
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask(String.format("Importing '%1s'...", path), IProgressMonitor.UNKNOWN);

				Importer importer = importers.get(extension);
				URI uri = URI.createPlatformResourceURI(path, true);
				Resource resource = getResourceSet().getResource(uri, true);

				Activity activity = importer.getActivity(resource.getContents());
				System.out.println(activity);

				monitor.done();
			}
		};

		try {
			getContainer().run(true, false, runnable);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}
}
