package com.aljoschability.eclipse.stodito.evaluator;

public interface Sequence {
	boolean hasNext();

	Step next();
}
