package com.aljoschability.eclipse.stodito.interpreter.strategies;

import java.util.Set;

/**
 * The default matching strategy of the story pattern matcher. It searches matches in the order of their cost estimates,
 * starting with the cheapest ones.
 */
public class DefaultMatchingStrategy extends AbstractMatchingStrategy {
	@Override
	public PatternPart getNextPatternPartForMatching(Set<PatternPart> uncheckedPatternParts) {
		int lowestCost = PatternPart.MATCHING_NOT_POSSIBLE;
		int lowestCostOptional = PatternPart.MATCHING_NOT_POSSIBLE;
		int cost = 0;

		PatternPart cheapestPatternPart = null;
		PatternPart cheapestPatternPartOptional = null;

		/*
		 * Look at all unchecked pattern parts.
		 */
		for (PatternPart patternPart : uncheckedPatternParts) {
			/*
			 * Calculate matching cost.
			 */
			cost = patternPart.calculateMatchingCost();

			if (cost == PatternPart.MATCHING_NOT_POSSIBLE) {
				// Skip
			} else {
				switch (patternPart.getMatchType()) {
				case MANDATORY:
				case NEGATIVE:
					if (cost == 1) {
						/*
						 * The cost can hardly be lower, return immediately.
						 */
						return patternPart;
					} else if (cost < lowestCost || cheapestPatternPart == null) {
						/*
						 * keep this pattern part if it is cheaper
						 */
						lowestCost = cost;
						cheapestPatternPart = patternPart;
					}
					break;
				case OPTIONAL:
					if (cost < lowestCostOptional || cheapestPatternPartOptional == null) {
						lowestCostOptional = cost;
						cheapestPatternPartOptional = patternPart;
					}
					break;
				}
			}
		}

		if (cheapestPatternPart != null) {
			return cheapestPatternPart;
		} else {
			return cheapestPatternPartOptional;
		}
	}
}
