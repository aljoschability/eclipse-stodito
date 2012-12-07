package com.aljoschability.eclipse.stodito.ui.commands;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.services.GraphitiUi;

import com.aljoschability.eclipse.stodito.Activity;
import com.aljoschability.eclipse.stodito.ActivityNode;
import com.aljoschability.eclipse.stodito.ControlFlow;
import com.aljoschability.eclipse.stodito.ui.Activator;

public class AddElementsCommand extends RecordingCommand {
	private Activity bo;
	private Resource peResource;
	private URI uri;

	public AddElementsCommand(Activity bo, URI uri) {
		super(TransactionUtil.getEditingDomain(bo));
		this.bo = bo;
		this.uri = uri;
	}

	public Resource getDiagramResource() {
		return peResource;
	}

	@Override
	protected void doExecute() {
		// create the diagram and its file
		Diagram pe = Graphiti.getPeCreateService().createDiagram(getDiagramId(), "diagramName", true); //$NON-NLS-1$

		// create link
		PictogramLink link = PictogramsFactory.eINSTANCE.createPictogramLink();
		link.getBusinessObjects().add(bo);
		link.setPictogramElement(pe);

		peResource = getEditingDomain().getResourceSet().createResource(uri);
		peResource.getContents().add(pe);

		// start dark feature processing
		IDiagramTypeProvider dtp = GraphitiUi.getExtensionManager().createDiagramTypeProvider(pe,
				getDiagramProviderId());
		IFeatureProvider fp = dtp.getFeatureProvider();

		// add all nodes to diagram
		int x = 0;
		int y = 0;
		for (ActivityNode node : bo.getNodes()) {
			// Create the context information
			AddContext context = new AddContext();
			context.setNewObject(node);
			context.setTargetContainer(pe);
			context.setX(50 + x * 200);
			context.setY(50 + y * 200);

			IAddFeature feature = fp.getAddFeature(context);
			if (feature.canAdd(context)) {
				feature.add(context);
				x++;
				if (x > 5) {
					y++;
					x = 0;
				}
			}
		}

		// add all edges to diagram
		for (ControlFlow edge : bo.getEdges()) {
			ContainerShape source = (ContainerShape) fp.getPictogramElementForBusinessObject(edge.getSource());
			ContainerShape target = (ContainerShape) fp.getPictogramElementForBusinessObject(edge.getTarget());
			if (source != null && target != null) {
				Anchor sourceAnchor = Graphiti.getPeService().getChopboxAnchor(source);
				Anchor targetAnchor = Graphiti.getPeService().getChopboxAnchor(target);

				if (sourceAnchor != null && targetAnchor != null) {
					AddConnectionContext context = new AddConnectionContext(sourceAnchor, targetAnchor);
					context.setNewObject(edge);

					IAddFeature feature = fp.getAddFeature(context);
					if (feature.canAdd(context)) {
						feature.add(context);
					}
				} else {
					Activator.get().error("No anchor found for source or target pictogram element.");
				}
			} else {
				Activator.get().error("No pictogram element found for source or target.");
			}
		}

		// layout diagram
		// LayoutDiagramFeature layoutFeature = new LayoutDiagramFeature(fp);
		// layoutFeature.execute(null);
	}

	private String getDiagramProviderId() {
		// TODO Auto-generated method stub
		return "Constants.ID_PROVIDER";
	}

	private String getDiagramId() {
		// TODO Auto-generated method stub
		return "Constants.ID_DIAGRAM";
	}

	private TransactionalEditingDomain getEditingDomain() {
		return TransactionUtil.getEditingDomain(bo);
	}
}
