package com.aljoschability.eclipse.stodito.interpreter.strategies;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * This matching strategy returns pattern parts in the order defined in the log, which has to be provided as a
 * parameter. This is required for for-each nodes. Changes in the instance model can make the default strategy use a
 * different matching order in later iterations than in the first one. Therefore, the DefaultMatchingStrategyWithLog can
 * be used to produce a log in the first run. The LogReproducingMatchingStrategy uses this log to maintain the same
 * matching order.
 */
public class LogReproducingMatchingStrategy extends AbstractMatchingStrategy {

	private final LinkedHashSet<PatternPart> log;

	public LogReproducingMatchingStrategy(LinkedHashSet<PatternPart> log) {
		assert log != null;

		this.log = log;
	}

	@Override
	public PatternPart getNextPatternPartForMatching(Set<PatternPart> uncheckedPatternParts) {
		for (PatternPart patternPart : log) {
			if (uncheckedPatternParts.contains(patternPart)) {
				return patternPart;
			}
		}

		return null;
	}

}
