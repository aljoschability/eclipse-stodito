package com.aljoschability.eclipse.stodito.interpreter;

public abstract class AbstractNotifier {
	private final EventEmitter emitter;

	public AbstractNotifier(EventEmitter emitter) {
		this.emitter = emitter;
	}

	public void addListener(EvaluationListener receiver) {
		emitter.add(receiver);
	}

	public void removeListener(EvaluationListener receiver) {
		emitter.remove(receiver);
	}

	protected EventEmitter getEmitter() {
		return emitter;
	}
}
