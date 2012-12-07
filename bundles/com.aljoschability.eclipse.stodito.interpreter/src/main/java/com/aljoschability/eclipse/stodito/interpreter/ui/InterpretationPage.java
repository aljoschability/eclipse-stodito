package com.aljoschability.eclipse.stodito.interpreter.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.console.TextConsoleViewer;

import com.aljoschability.core.emf.providers.ComposedAdapterFactoryLabelProvider;
import com.aljoschability.eclipse.stodito.evaluator.Evaluator;
import com.aljoschability.eclipse.stodito.evaluator.Variable;
import com.aljoschability.eclipse.stodito.interpreter.EvaluationType;
import com.aljoschability.eclipse.stodito.interpreter.ExecutionUtil;
import com.aljoschability.eclipse.stodito.interpreter.InterpreterException;
import com.aljoschability.eclipse.stodito.interpreter.util.ConsoleEventReceiver;
import com.aljoschability.eclipse.stodito.interpreter.util.ExecutionConsole;
import com.aljoschability.eclipse.stodito.interpreter.util.ExecutionConsole.StreamType;
import com.aljoschability.eclipse.stodito.interpreter.util.VariablesEventReceiver;

public class InterpretationPage extends WizardPage {
	private Evaluator executor;
	private Variable variables;
	private ExecutionConsole console;

	private ToolItem playItem;
	private ToolItem stepItem;
	private ToolItem stopItem;

	private TreeViewer variablesViewer;

	private TextConsoleViewer loggingViewer;
	private ConsoleEventReceiver consoleReceiver;
	private VariablesEventReceiver variablesReceiver;

	protected InterpretationPage() {
		super(InterpretationPage.class.getName());

		console = new ExecutionConsole();

		setTitle("Execute Activity");
		setDescription("Execute the activity.");
	}

	@Override
	public void createControl(Composite parent) {
		// page
		SashForm page = new SashForm(parent, SWT.VERTICAL);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(page);

		// main
		Composite topComposite = new Composite(page, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).margins(6, 6).applyTo(topComposite);

		// controls
		ToolBar controlBar = new ToolBar(topComposite, SWT.FLAT | SWT.RIGHT | SWT.VERTICAL);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).applyTo(controlBar);

		playItem = new ToolItem(controlBar, SWT.NONE);
		// TODO: image
		// playItem.setImage(CoreImages.get(CoreImages.CONTROL_RUN));
		playItem.setToolTipText("Start Execution");

		stepItem = new ToolItem(controlBar, SWT.NONE);
		// TODO: image
		// stepItem.setImage(CoreImages.get(CoreImages.CONTROL_RESUME));
		stepItem.setToolTipText("Execute Next Step");
		stepItem.setEnabled(false);

		stopItem = new ToolItem(controlBar, SWT.NONE);
		// TODO: image
		// stopItem.setImage(CoreImages.get(CoreImages.CONTROL_TERMINATE));
		stopItem.setToolTipText("Terminate Execution");
		stopItem.setEnabled(false);

		// results
		Group variablesGroup = new Group(topComposite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(variablesGroup);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(variablesGroup);
		variablesGroup.setText("Variables");

		Tree variablesTree = new Tree(variablesGroup, SWT.BORDER | SWT.SINGLE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(variablesTree);

		variablesViewer = new TreeViewer(variablesTree);
		variablesViewer.setContentProvider(new VariablesContentProvider());
		variablesViewer.setLabelProvider(new ComposedAdapterFactoryLabelProvider());

		// logging
		Group loggingGroup = new Group(page, SWT.NONE);
		GridLayoutFactory.fillDefaults().margins(6, 6).applyTo(loggingGroup);
		loggingGroup.setText("Event Log");

		Composite loggingComposite = new Composite(loggingGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(loggingComposite);
		GridLayoutFactory.fillDefaults().applyTo(loggingComposite);

		loggingViewer = new TextConsoleViewer(loggingComposite, console);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(loggingViewer.getControl());
		loggingViewer.setEditable(false);

		hookListeners();

		page.setWeights(new int[] { 7, 4 });
		setControl(page);
	}

	private void hookListeners() {
		// controls
		playItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				run();
			}
		});

		stepItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				step();
			}
		});

		stopItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stop();
			}
		});

		// logging
		loggingViewer.addTextListener(new ITextListener() {
			@Override
			public void textChanged(TextEvent event) {
				int index = loggingViewer.getDocument().getLength() - 1;
				loggingViewer.setTopIndex(index);
			}
		});
	}

	@Override
	public InterpreteActivityWizard getWizard() {
		return (InterpreteActivityWizard) super.getWizard();
	}

	private void step() {
		try {
			getExecutor().evaluate(EvaluationType.NEXT);
		} catch (InterpreterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void stop() {
		try {
			getExecutor().evaluate(EvaluationType.TERMINATE);
		} catch (InterpreterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Evaluator getExecutor() {
		if (executor == null) {
			executor = ExecutionUtil.createExecutor(getClass().getClassLoader());
			executor.addListener(consoleReceiver = new ConsoleEventReceiver(console));
			executor.addListener(variablesReceiver = new VariablesEventReceiver());
		}
		return executor;
	}

	private void run() {
		Exception exception = null;
		variables = null;
		variablesViewer.setInput(null);
		console.clearConsole();

		try {
			IRunnableWithProgress runnable = new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Executing Activity...", IProgressMonitor.UNKNOWN);

					getExecutor().initialize(getWizard().getActivity(), getWizard().getBindings());

					if (getWizard().getResource() != null) {
						getWizard().getResource().setTrackingModification(true);
					}

					try {
						getExecutor().evaluate(EvaluationType.RUN);
						variables = getExecutor().getResult();
					} catch (InterpreterException e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			};

			getContainer().run(true, true, runnable);

			variablesViewer.setInput(variables);
		} catch (InvocationTargetException e) {
			exception = e;
		} catch (InterruptedException e) {
			exception = e;
		}

		if (exception != null) {
			exception.printStackTrace();

			console.wrap();
			console.append(StreamType.FAILURE, "An exception occurred during the interpretation!");
		}
	}
}
