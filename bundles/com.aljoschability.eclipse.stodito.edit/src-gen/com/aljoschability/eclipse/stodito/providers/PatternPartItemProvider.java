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

import com.aljoschability.eclipse.stodito.PatternPart;
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
 * This is the item provider adapter for a {@link com.aljoschability.eclipse.stodito.PatternPart} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PatternPartItemProvider extends CommentableItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PatternPartItemProvider(AdapterFactory adapterFactory) {
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

			addSemanticsPropertyDescriptor(object);
			addNodePropertyDescriptor(object);
			addParentPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Semantics feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSemanticsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_PatternPart_semantics_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_PatternPart_semantics_feature", "_UI_PatternPart_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.PATTERN_PART__SEMANTICS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Node feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNodePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_PatternPart_node_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_PatternPart_node_feature", "_UI_PatternPart_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.PATTERN_PART__NODE, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Parent feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParentPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_PatternPart_parent_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_PatternPart_parent_feature", "_UI_PatternPart_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						StoditoPackage.Literals.PATTERN_PART__PARENT, true, false, true, null, null, null));
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
			childrenFeatures.add(StoditoPackage.Literals.PATTERN_PART__PARTS);
			childrenFeatures.add(StoditoPackage.Literals.PATTERN_PART__PRIMITIVES);
			childrenFeatures.add(StoditoPackage.Literals.PATTERN_PART__OBJECTS);
			childrenFeatures.add(StoditoPackage.Literals.PATTERN_PART__CONNECTIONS);
			childrenFeatures.add(StoditoPackage.Literals.PATTERN_PART__CONSTRAINTS);
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
		String label = ((PatternPart) object).getComment();
		return label == null || label.length() == 0 ? getString("_UI_PatternPart_type") : //$NON-NLS-1$
				getString("_UI_PatternPart_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(PatternPart.class)) {
		case StoditoPackage.PATTERN_PART__SEMANTICS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case StoditoPackage.PATTERN_PART__PARTS:
		case StoditoPackage.PATTERN_PART__PRIMITIVES:
		case StoditoPackage.PATTERN_PART__OBJECTS:
		case StoditoPackage.PATTERN_PART__CONNECTIONS:
		case StoditoPackage.PATTERN_PART__CONSTRAINTS:
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

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__PARTS,
				StoditoFactory.eINSTANCE.createPatternPart()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__PRIMITIVES,
				StoditoFactory.eINSTANCE.createPrimitiveOccurrence()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__OBJECTS,
				StoditoFactory.eINSTANCE.createObjectOccurrence()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__OBJECTS,
				StoditoFactory.eINSTANCE.createCollectionOccurrence()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__CONNECTIONS,
				StoditoFactory.eINSTANCE.createLink()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__CONNECTIONS,
				StoditoFactory.eINSTANCE.createInclusionLink()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__CONNECTIONS,
				StoditoFactory.eINSTANCE.createSameLink()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__CONNECTIONS,
				StoditoFactory.eINSTANCE.createPath()));

		newChildDescriptors.add(createChildParameter(StoditoPackage.Literals.PATTERN_PART__CONSTRAINTS,
				StoditoFactory.eINSTANCE.createConstraint()));
	}

}
