package com.factoria2.absolute.widgets.geom;

/**
 * Insets are read-only.
 * 
 * @author Iván
 * 
 */
public class Insets {

	public static final Insets NONE = new Insets(0, 0, 0, 0);

	private int left;
	private int top;
	private int right;
	private int bottom;

	public Insets(int all) {
		this(all, all, all, all);
	}

	public Insets(int topBottom, int leftRight) {
		this(leftRight, topBottom, leftRight, topBottom);
	}

	public Insets(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}

	public Point getOffset() {
		return new Point(left, top);
	}

	public Size getAggregatedSize() {
		return new Size(left + right, top + bottom);
	}

	public Insets clone() {
		return new Insets(left, top, right, bottom);
	}

	@Override
	public String toString() {
		return "[" + left + "," + top + "," + right + "," + bottom + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottom;
		result = prime * result + left;
		result = prime * result + right;
		result = prime * result + top;
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
		Insets other = (Insets) obj;
		if (bottom != other.bottom)
			return false;
		if (left != other.left)
			return false;
		if (right != other.right)
			return false;
		if (top != other.top)
			return false;
		return true;
	}

}
