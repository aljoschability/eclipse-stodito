package com.aljoschability.eclipse.stodito.edit;

import org.eclipse.emf.common.notify.Adapter;

public class CustomStoditoItemProviderAdapterFactory extends StoditoItemProviderAdapterFactory {
	@Override
	public Adapter createActivityAdapter() {
		if (activityItemProvider == null) {
			activityItemProvider = new CustomActivityItemProvider(this);
		}
		return activityItemProvider;
	}

	@Override
	public Adapter createActivityFinalNodeAdapter() {
		if (activityFinalNodeItemProvider == null) {
			activityFinalNodeItemProvider = new CustomActivityFinalNodeItemProvider(this);
		}
		return activityFinalNodeItemProvider;
	}

	@Override
	public Adapter createArithmeticExpressionAdapter() {
		if (arithmeticExpressionItemProvider == null) {
			arithmeticExpressionItemProvider = new CustomArithmeticExpressionItemProvider(this);
		}
		return arithmeticExpressionItemProvider;
	}

	@Override
	public Adapter createAttributeAssignmentAdapter() {
		if (attributeAssignmentItemProvider == null) {
			attributeAssignmentItemProvider = new CustomAttributeAssignmentItemProvider(this);
		}
		return attributeAssignmentItemProvider;
	}

	@Override
	public Adapter createCallExpressionAdapter() {
		if (callExpressionItemProvider == null) {
			callExpressionItemProvider = new CustomCallExpressionItemProvider(this);
		}
		return callExpressionItemProvider;
	}

	@Override
	public Adapter createCallNodeAdapter() {
		if (callNodeItemProvider == null) {
			callNodeItemProvider = new CustomCallNodeItemProvider(this);
		}
		return callNodeItemProvider;
	}

	@Override
	public Adapter createCollectionOccurrenceAdapter() {
		if (collectionOccurrenceItemProvider == null) {
			collectionOccurrenceItemProvider = new CustomCollectionOccurrenceItemProvider(this);
		}
		return collectionOccurrenceItemProvider;
	}

	@Override
	public Adapter createCollectionSizeExpressionAdapter() {
		if (collectionSizeExpressionItemProvider == null) {
			collectionSizeExpressionItemProvider = new CustomCollectionSizeExpressionItemProvider(this);
		}
		return collectionSizeExpressionItemProvider;
	}

	@Override
	public Adapter createComparisonExpressionAdapter() {
		if (comparisonExpressionItemProvider == null) {
			comparisonExpressionItemProvider = new CustomComparisonExpressionItemProvider(this);
		}
		return comparisonExpressionItemProvider;
	}

	@Override
	public Adapter createConstraintAdapter() {
		if (constraintItemProvider == null) {
			constraintItemProvider = new CustomConstraintItemProvider(this);
		}
		return constraintItemProvider;
	}

	@Override
	public Adapter createControlFlowAdapter() {
		if (controlFlowItemProvider == null) {
			controlFlowItemProvider = new CustomControlFlowItemProvider(this);
		}
		return controlFlowItemProvider;
	}

	@Override
	public Adapter createFlowFinalNodeAdapter() {
		if (flowFinalNodeItemProvider == null) {
			flowFinalNodeItemProvider = new CustomFlowFinalNodeItemProvider(this);
		}
		return flowFinalNodeItemProvider;
	}

	@Override
	public Adapter createInclusionLinkAdapter() {
		if (inclusionLinkItemProvider == null) {
			inclusionLinkItemProvider = new CustomInclusionLinkItemProvider(this);
		}
		return inclusionLinkItemProvider;
	}

	@Override
	public Adapter createInitialNodeAdapter() {
		if (initialNodeItemProvider == null) {
			initialNodeItemProvider = new CustomInitialNodeItemProvider(this);
		}
		return initialNodeItemProvider;
	}

	@Override
	public Adapter createJunctionNodeAdapter() {
		if (junctionNodeItemProvider == null) {
			junctionNodeItemProvider = new CustomJunctionNodeItemProvider(this);
		}
		return junctionNodeItemProvider;
	}

	@Override
	public Adapter createLinkAdapter() {
		if (linkItemProvider == null) {
			linkItemProvider = new CustomLinkItemProvider(this);
		}
		return linkItemProvider;
	}

	@Override
	public Adapter createLiteralExpressionAdapter() {
		if (literalExpressionItemProvider == null) {
			literalExpressionItemProvider = new CustomLiteralExpressionItemProvider(this);
		}
		return literalExpressionItemProvider;
	}

	@Override
	public Adapter createLogicExpressionAdapter() {
		if (logicExpressionItemProvider == null) {
			logicExpressionItemProvider = new CustomLogicExpressionItemProvider(this);
		}
		return logicExpressionItemProvider;
	}

	@Override
	public Adapter createObjectAttributeExpressionAdapter() {
		if (objectAttributeExpressionItemProvider == null) {
			objectAttributeExpressionItemProvider = new CustomObjectAttributeExpressionItemProvider(this);
		}
		return objectAttributeExpressionItemProvider;
	}

	@Override
	public Adapter createObjectOccurrenceAdapter() {
		if (objectOccurrenceItemProvider == null) {
			objectOccurrenceItemProvider = new CustomObjectOccurrenceItemProvider(this);
		}
		return objectOccurrenceItemProvider;
	}

	@Override
	public Adapter createObjectOccurrenceExpressionAdapter() {
		if (objectOccurrenceExpressionItemProvider == null) {
			objectOccurrenceExpressionItemProvider = new CustomObjectOccurrenceExpressionItemProvider(this);
		}
		return objectOccurrenceExpressionItemProvider;
	}

	@Override
	public Adapter createOclExpressionAdapter() {
		if (oclExpressionItemProvider == null) {
			oclExpressionItemProvider = new CustomOclExpressionItemProvider(this);
		}
		return oclExpressionItemProvider;
	}

	@Override
	public Adapter createOpaqueCallableAdapter() {
		if (opaqueCallableItemProvider == null) {
			opaqueCallableItemProvider = new CustomOpaqueCallableItemProvider(this);
		}
		return opaqueCallableItemProvider;
	}

	@Override
	public Adapter createParameterAdapter() {
		if (parameterItemProvider == null) {
			parameterItemProvider = new CustomParameterItemProvider(this);
		}
		return parameterItemProvider;
	}

	@Override
	public Adapter createParameterBindingAdapter() {
		if (parameterBindingItemProvider == null) {
			parameterBindingItemProvider = new CustomParameterBindingItemProvider(this);
		}
		return parameterBindingItemProvider;
	}

	@Override
	public Adapter createPathAdapter() {
		if (pathItemProvider == null) {
			pathItemProvider = new CustomPathItemProvider(this);
		}
		return pathItemProvider;
	}

	@Override
	public Adapter createPatternPartAdapter() {
		if (patternPartItemProvider == null) {
			patternPartItemProvider = new CustomPatternPartItemProvider(this);
		}
		return patternPartItemProvider;
	}

	@Override
	public Adapter createPrimitiveOccurrenceAdapter() {
		if (primitiveOccurrenceItemProvider == null) {
			primitiveOccurrenceItemProvider = new CustomPrimitiveOccurrenceItemProvider(this);
		}
		return primitiveOccurrenceItemProvider;
	}

	@Override
	public Adapter createPrimitiveOccurrenceExpressionAdapter() {
		if (primitiveOccurrenceExpressionItemProvider == null) {
			primitiveOccurrenceExpressionItemProvider = new CustomPrimitiveOccurrenceExpressionItemProvider(this);
		}
		return primitiveOccurrenceExpressionItemProvider;
	}

	@Override
	public Adapter createSameLinkAdapter() {
		if (sameLinkItemProvider == null) {
			sameLinkItemProvider = new CustomSameLinkItemProvider(this);
		}
		return sameLinkItemProvider;
	}

	@Override
	public Adapter createStatementNodeAdapter() {
		if (statementNodeItemProvider == null) {
			statementNodeItemProvider = new CustomStatementNodeItemProvider(this);
		}
		return statementNodeItemProvider;
	}

	@Override
	public Adapter createStoryNodeAdapter() {
		if (storyNodeItemProvider == null) {
			storyNodeItemProvider = new CustomStoryNodeItemProvider(this);
		}
		return storyNodeItemProvider;
	}

	@Override
	public Adapter createStructuredNodeAdapter() {
		if (structuredNodeItemProvider == null) {
			structuredNodeItemProvider = new CustomStructuredNodeItemProvider(this);
		}
		return structuredNodeItemProvider;
	}

	@Override
	public Adapter createUnaryExpressionAdapter() {
		if (unaryExpressionItemProvider == null) {
			unaryExpressionItemProvider = new CustomUnaryExpressionItemProvider(this);
		}
		return unaryExpressionItemProvider;
	}
}