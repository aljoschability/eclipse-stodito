/**
 * <copyright>
 * 	Copyright 2013 by Aljoschability and others. All rights reserved. This program and its materials are made
 * 	available under the terms of the Eclipse Public License v1.0 which should be contained in this distribution.
 * 
 * 	Contributors:
 * 		Aljoscha Hark <aljoscha@aljoschability.com> - Initial code
 * 
 * </copyright>
 */
package com.aljoschability.eclipse.stodito.providers;

import com.aljoschability.eclipse.stodito.ArithmeticExpression;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.aljoschability.eclipse.stodito.ArithmeticExpression} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArithmeticExpressionItemProvider extends ExpressionItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArithmeticExpressionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOperatorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Operator feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOperatorPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_ArithmeticExpression_operator_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_ArithmeticExpression_operator_feature", "_UI_ArithmeticExpression_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.ARITHMETIC_EXPRESSION__OPERATOR, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT);
			childrenFeatures.add(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ArithmeticExpression) object).getComment();
		return label == null || label.length() == 0 ? getString("_UI_ArithmeticExpression_type") : //$NON-NLS-1$
				getString("_UI_ArithmeticExpression_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ArithmeticExpression.class)) {
		case StoditoPackage.ARITHMETIC_EXPRESSION__OPERATOR:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case StoditoPackage.ARITHMETIC_EXPRESSION__LEFT:
		case StoditoPackage.ARITHMETIC_EXPRESSION__RIGHT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createOclExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createLiteralExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createArithmeticExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createComparisonExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createLogicExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createPrimitiveOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createObjectOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createObjectAttributeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createCollectionSizeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT,
				StoditoFactory.eINSTANCE.createCallExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createOclExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createLiteralExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createArithmeticExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createComparisonExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createLogicExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createPrimitiveOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createObjectOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createObjectAttributeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createCollectionSizeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT,
				StoditoFactory.eINSTANCE.createCallExpression()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == StoditoPackage.Literals.ARITHMETIC_EXPRESSION__LEFT
				|| childFeature == StoditoPackage.Literals.ARITHMETIC_EXPRESSION__RIGHT;

		if (qualify) {
			return getString("_UI_CreateChild_text2", //$NON-NLS-1$
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
