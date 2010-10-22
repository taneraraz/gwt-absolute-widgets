package com.factoria2.absolute.widgets.aspect;

public abstract class Reflection {

	public enum Position {
		ABOVE("above"), 
		BELOW("below"),
		LEFT("left"), 
		RIGHT("right");
		
		private String cssValue;

		private Position( String cssValue ){
			this.cssValue = cssValue;
		}

		public String getCssValue() {
			return cssValue;
		}
	}

	private Position position;
	private int separation;
	
	protected Reflection(Position position, int separation) {
		this.position = position;
		this.separation = separation;
	}

	public Position getPosition() {
		return position;
	}

	public int getSeparation() {
		return separation;
	}

	public abstract String getCssValue();
}
