package com.factoria2.absolute.widgets.aspect.reflection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Reflection;
import com.factoria2.absolute.widgets.aspect.value.ReflectionPosition;

public class WetFloorReflection extends Reflection {

	private double opacity;
	private double length;
	private Map<String, String> cssProps = new HashMap<String, String>();

	public WetFloorReflection() {
		this(0.3);
	}

	public WetFloorReflection(final double length) {
		this(length, 0.45);
	}

	public WetFloorReflection(final double length, final double opacity) {
		this(4, length, opacity);
	}

	public WetFloorReflection(final int separation, final double length, final double opacity) {
		this(ReflectionPosition.BELOW, separation, length, opacity);
	}

	public WetFloorReflection(final ReflectionPosition position, final int separation, final double length, final double opacity) {
		super(position, separation);
		this.opacity = opacity;
		this.length = length;

		cssProps.put("webkitBoxReflect", getPosition().getCssValue() + " " + getSeparation() + "px" + " -webkit-gradient( linear, left top, left bottom, from(transparent), color-stop(" + (1 - length) + ", transparent), " + " to( rgba(255,255,255," + opacity + ") ) )");
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

	public double getLength() {
		return length;
	}

	public double getOpacity() {
		return opacity;
	}

}
