package com.aljoschability.eclipse.stodito.interpreter;

import com.aljoschability.eclipse.stodito.PatternPart;
import com.aljoschability.eclipse.stodito.interpreter.strategies.AbstractMatchingStrategy;

public class Matcher extends AbstractNotifier {

	private PatternPart pattern;
	private Scope scope;

	public Matcher(EventEmitter emitter, PatternPart pattern, Scope scope) {
		super(emitter);

		this.pattern = pattern;
		this.scope = scope;
	}

	public void applyMatch() {
		// TODO Auto-generated method stub

	}

	public boolean findNextMatch() throws InterpreterException {
		getEmitter().start(pattern);
		// TODO Auto-generated method stub
		return false;
	}

	public AbstractMatchingStrategy getMatchingStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMatchingStrategy(AbstractMatchingStrategy logReproducingMatchingStrategy) {
		// TODO Auto-generated method stub

	}

}
