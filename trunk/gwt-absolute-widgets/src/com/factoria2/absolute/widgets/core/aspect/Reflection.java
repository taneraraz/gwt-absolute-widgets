package com.factoria2.absolute.widgets.core.aspect;

import com.factoria2.absolute.widgets.core.aspect.value.ReflectionPosition;

public abstract class Reflection implements HasCssProperties {

	private ReflectionPosition position;
	private int separation;

	protected Reflection(final ReflectionPosition position, final int separation) {
		this.position = position;
		this.separation = separation;
	}

	public ReflectionPosition getPosition() {
		return position;
	}

	public int getSeparation() {
		return separation;
	}
}
