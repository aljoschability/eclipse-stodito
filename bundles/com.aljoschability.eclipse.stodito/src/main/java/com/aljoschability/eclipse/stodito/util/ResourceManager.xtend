package com.aljoschability.eclipse.stodito.util

import java.util.Collection
import java.util.Collections
import java.util.Comparator
import java.util.Deque
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.FeatureMap
import org.eclipse.emf.ecore.util.FeatureMapUtil

public class ResourceManager {
	static val ENAMEDELEMENT_COMPARATOR = new Comparator<ENamedElement>() {
		override compare(ENamedElement one, ENamedElement two) {
			if (one == null) {
				if (two == null) {
					return 0
				} else {
					return -1
				}
			}
			val nameOne = one.getName();
			val nameTwo = two.getName();

			if (nameOne == null) {
				if (nameTwo == null) {
					return 0
				} else {
					return -1
				}
			}

			if (nameTwo == null) {
				return 1
			}

			return nameOne.compareTo(nameTwo)
		}

		override equals(Object obj) {
			throw new UnsupportedOperationException("TODO: auto-generated method stub")
		}
	}

	private static def void collectReachableObjectsOfType(Collection<EObject> visited, Deque<EObject> queue,
		Collection<EObject> result, EObject element, EClassifier type) {
		if (visited.add(element)) {
			if (type.isInstance(element)) {
				result.add(element);
			}

			// avoid pulling all EcorePackage's meta data
			if (!EcorePackage.Literals.EOBJECT.equals(element)) {
				val eClass = element.eClass();
				for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
					if (!feature.isDerived()) {
						if (feature instanceof EReference) {
							if (feature.isMany()) {
								for (Object object : element.eGet(feature)as Collection<?>) {
									queue.add(object as EObject);
								}
							} else {
								val object = element.eGet(feature) as EObject

								// avoid pulling all EcorePackage's meta data
								if (object != null && (!EcorePackage.eINSTANCE.equals(object) ||
									!EcorePackage.Literals.ECLASSIFIER__EPACKAGE.equals(feature) ||
									element instanceof EClass)) {
									queue.addLast(object);
								}
							}
						} else if (FeatureMapUtil.isFeatureMap(feature)) {
							for (FeatureMap.Entry entry : element.eGet(feature) as FeatureMap) {
								if (entry.getEStructuralFeature() instanceof EReference && entry.getValue() != null) {
									queue.addLast(entry.getValue() as EObject)
								}
							}
						}
					}
				}
			}
		}
	}

	protected static def Collection<EObject> getReachableObjectsOfType(ResourceSet resourceSet, EClassifier type) {
		val queue = newLinkedList()
		val visited = newLinkedHashSet()
		val result = newArrayList()

		// iterate over resource set
		val i = EcoreUtil.getAllContents(resourceSet, true)
		while (i.hasNext()) {
			val object = i.next()
			if (object instanceof EObject) {
				collectReachableObjectsOfType(visited, queue, result, object as EObject, type)
				i.prune()
			}
		}

		while (!queue.isEmpty()) {
			val element = queue.removeFirst()
			collectReachableObjectsOfType(visited, queue, result, element, type)
		}

		return result
	}

	ResourceSet resourceSet
	List<EPackage> ePackages

	List<EClass> eClasses

	List<EDataType> eDataTypes

	new(ResourceSet resourceSet) {
		this.resourceSet = resourceSet
		refresh()
	}

	def List<EClass> getAllEClasses() {
		return eClasses
	}

	def List<EDataType> getAllEDataType() {
		return eDataTypes
	}

	def List<EPackage> getAllEPackages() {
		return ePackages
	}

	def void refresh() {
		ePackages = newArrayList()
		eClasses = newArrayList()
		eDataTypes = newArrayList()

		for (EObject element : getReachableObjectsOfType(resourceSet, EcorePackage.Literals.EPACKAGE)) {
			if (element instanceof EPackage) {
				ePackages.add(element);
			}
		}

		for (EObject element : getReachableObjectsOfType(resourceSet, EcorePackage.Literals.ECLASS)) {
			if (element instanceof EClass) {
				eClasses.add(element)
			}
		}

		for (EObject element : getReachableObjectsOfType(resourceSet, EcorePackage.Literals.EDATA_TYPE)) {
			if (element instanceof EDataType) {
				eDataTypes.add(element)
			}
		}

		Collections.sort(ePackages, ENAMEDELEMENT_COMPARATOR)
		Collections.sort(eClasses, ENAMEDELEMENT_COMPARATOR)
		Collections.sort(eDataTypes, ENAMEDELEMENT_COMPARATOR)
	}
}
