package com.factoria2.absolute.widgets.aspect;

import com.factoria2.absolute.widgets.geom.Insets;

/**
 * Borders are read-only
 * @author Iván
 *
 */
public class Border {

	public static final Border SOLID_BLACK = new Border(Type.SOLID,1,0,Color.BLACK); 
	public static final Border SOLID_WHITE = new Border(Type.SOLID,1,0,Color.WHITE);
	public static final Border SOLID_RED = new Border(Type.SOLID,1,0,Color.RED);
	
	public enum Type {
		DOTTED("dotted"), 
		DASHED("dashed"),
		SOLID("solid"),
		DOUBLE("double"),
		GROOVE("groove"),
		RIDGE("ridge"),
		INSET("inset"),
		OUTSET("outset");
		
		private String cssValue;

		private Type( String cssValue ) {
			this.cssValue = cssValue;
		}

		public String getCssValue() {
			return cssValue;
		}

	}
	
	private Type type = Type.SOLID;
	private Insets width = Insets.NONE;
	private Insets radius = Insets.NONE;
	private Color color = Color.BLACK;
	
	public Border(Type type, Insets width, Insets radius, Color color) {
		this.type = type;
		this.width = width;
		this.radius = radius;
		this.color = color;
	}

	public Border(Type type, int width, int radius, Color color) {
		this.type = type;
		this.width = new Insets(width);
		this.radius = new Insets(radius);
		this.color = color;
	}

	public Border(Type type, int width, Color color) {
		this.type = type;
		this.width = new Insets(width);
		this.color = color;
	}

	public Type getType() {
		return type;
	}

	public Insets getWidth() {
		return width;
	}

	public Insets getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public Border clone() {
		return new Border(type,width,radius,color);
	}

	@Override
	public String toString() {
		return "{"+type+" "+width+" "+radius+" "+color+"}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((radius == null) ? 0 : radius.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (radius == null) {
			if (other.radius != null)
				return false;
		} else if (!radius.equals(other.radius))
			return false;
		if (type != other.type)
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

}
