package com.aljoschability.eclipse.stodito.interpreter.strategies;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Subclass of the default matching strategy. This strategy maintains a log of the matching order. This is required for
 * for-each nodes. Here, the matching order must be the same in each iteration to ensure that all matches are returned
 * and each match is returned only once.
 */
public class DefaultMatchingStrategyWithLog extends DefaultMatchingStrategy {

	private final LinkedHashSet<PatternPart> log;

	public DefaultMatchingStrategyWithLog() {
		log = new LinkedHashSet<PatternPart>();
	}

	public LinkedHashSet<PatternPart> getLog() {
		return log;
	}

	@Override
	public PatternPart getNextPatternPartForMatching(Set<PatternPart> uncheckedPatternParts) {
		PatternPart patternPart = super.getNextPatternPartForMatching(uncheckedPatternParts);

		if (!log.contains(patternPart)) {
			log.add(patternPart);
		}

		return patternPart;
	}
}
