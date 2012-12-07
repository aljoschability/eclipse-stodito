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

import org.eclipse.emf.common.notify.AdapterFactory;

public class CustomObjectOccurrenceItemProvider extends ObjectOccurrenceItemProvider {
	public CustomObjectOccurrenceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	@Override
	public Object getImage(Object object) {
		return getImage("Activity.png");
	}
}
