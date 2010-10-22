package com.factoria2.absolute.widgets.aspect.reflection;

import com.factoria2.absolute.widgets.aspect.Reflection;

public class WetFloorReflection extends Reflection {

	private double opacity;
	private double length;

	public WetFloorReflection() {
		this(0.3);
	}
	
	public WetFloorReflection(double length) {
		this(length,0.45);
	}

	public WetFloorReflection(double length, double opacity) {
		this(4,length,opacity);
	}
	
	public WetFloorReflection(int separation, double length, double opacity) {
		this(Position.BELOW,separation,length,opacity);
	}

	public WetFloorReflection(Position position, int separation, double length, double opacity ) {
		super(position, separation);
		this.opacity=opacity;
		this.length=length;
	}

	public double getLength() {
		return length;
	}

	public double getOpacity() {
		return opacity;
	}

	@Override
	public String getCssValue() {
		return getPosition().getCssValue()+" "+getSeparation()+"px"+
		" -webkit-gradient( linear, left top, left bottom, from(transparent), color-stop("+(1-length)+", transparent), "+
		" to( rgba(255,255,255,"+opacity+") ) )";
	}



}
