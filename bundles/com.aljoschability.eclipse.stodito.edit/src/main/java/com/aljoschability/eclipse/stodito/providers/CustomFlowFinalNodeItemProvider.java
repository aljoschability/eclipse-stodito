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

import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CustomFlowFinalNodeItemProvider extends FlowFinalNodeItemProvider {
	public CustomFlowFinalNodeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		return "Flow Final Node";
	}

	@Override
	public Object getImage(Object object) {
		return StoditoImages.get(StoditoPackage.Literals.FLOW_FINAL_NODE.getName());
	}
}
