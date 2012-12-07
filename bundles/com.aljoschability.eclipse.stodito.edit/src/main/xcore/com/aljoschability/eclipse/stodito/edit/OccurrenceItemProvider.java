/**
 * <copyright>
 * 
 * Copyright 2013 by Aljoschability and others. All rights reserved.
 * 
 * This program and its materials are made available under the terms of the
 * Eclipse Public License v1.0 which should be contained in this distribution.
 * 
 * Contributors:
 *    Aljoscha Hark <aljoschability@gmail.com> - Initial code
 * 
 * </copyright>
 */
package com.aljoschability.eclipse.stodito.edit;

import com.aljoschability.eclipse.stodito.Occurrence;
import com.aljoschability.eclipse.stodito.StoditoFactory;
import com.aljoschability.eclipse.stodito.StoditoPackage;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.aljoschability.eclipse.stodito.Occurrence} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OccurrenceItemProvider extends NamedItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurrenceItemProvider(AdapterFactory adapterFactory) {
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

			addCommentPropertyDescriptor(object);
			addBindingStatePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Comment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCommentPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_Commentable_comment_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_Commentable_comment_feature", "_UI_Commentable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.COMMENTABLE__COMMENT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Binding State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBindingStatePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_Occurrence_bindingState_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_Occurrence_bindingState_feature", "_UI_Occurrence_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.OCCURRENCE__BINDING_STATE, true, false, false,
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
			childrenFeatures.add(StoditoPackage.Literals.OCCURRENCE__CONSTRAINTS);
			childrenFeatures.add(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION);
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
		String label = ((Occurrence) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Occurrence_type") : //$NON-NLS-1$
				getString("_UI_Occurrence_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(Occurrence.class)) {
		case StoditoPackage.OCCURRENCE__COMMENT:
		case StoditoPackage.OCCURRENCE__BINDING_STATE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case StoditoPackage.OCCURRENCE__CONSTRAINTS:
		case StoditoPackage.OCCURRENCE__BINDING_EXPRESSION:
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

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__CONSTRAINTS,
				StoditoFactory.eINSTANCE.createConstraint()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createOclExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createLiteralExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createUnaryExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createArithmeticExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createComparisonExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createLogicExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createPrimitiveOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createObjectOccurrenceExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createObjectAttributeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createCollectionSizeExpression()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.OCCURRENCE__BINDING_EXPRESSION,
				StoditoFactory.eINSTANCE.createCallExpression()));
	}

}
