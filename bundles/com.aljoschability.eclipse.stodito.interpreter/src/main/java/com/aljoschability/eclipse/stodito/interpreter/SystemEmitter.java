package com.aljoschability.eclipse.stodito.interpreter;

import org.eclipse.emf.ecore.EObject;

import com.aljoschability.eclipse.stodito.ControlFlow;

public class SystemEmitter implements Emitter {
	@Override
	public void traversing(ControlFlow edge) {
		System.out.println("traversing " + edge);
	}

	@Override
	public void start(EObject node) {
		System.out.println("starting " + node);
	}

	@Override
	public void end(EObject node) {
		System.out.println("end " + node);
	}

	@Override
	public void create(Variable variable) {
		System.out.println("created variable: " + variable);
	}

	@Override
	public void remove(Variable variable) {
		System.out.println("removed variable: " + variable);

	}

	@Override
	public void change(Variable variable, Object oldValue, Object value) {
		System.out.println("changed variable: " + variable + " := " + value + " (was " + oldValue + ")");
	}
}
