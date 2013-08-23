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
package com.aljoschability.eclipse.stodito.edit;

import java.text.MessageFormat;

import org.eclipse.emf.common.notify.AdapterFactory;

import com.aljoschability.eclipse.stodito.StatementNode;
import com.aljoschability.eclipse.stodito.StoditoPackage;

public class CustomStatementNodeItemProvider extends StatementNodeItemProvider {
	public CustomStatementNodeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		return MessageFormat.format("Statement Node {0}", ((StatementNode) object).getName());
	}

	@Override
	public Object getImage(Object object) {
		return StoditoImages.get(StoditoPackage.Literals.STATEMENT_NODE.getName());
	}
}
