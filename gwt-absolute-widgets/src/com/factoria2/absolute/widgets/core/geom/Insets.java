package com.factoria2.absolute.widgets.core.geom;

/**
 * Insets are read-only.
 * 
 * @author Iv�n
 */
public class Insets {

	public static final Insets NONE = new Insets(0, 0, 0, 0);

	private int left;
	private int top;
	private int right;
	private int bottom;

	public Insets(final int all) {
		this(all, all, all, all);
	}

	public Insets(final int topBottom, final int leftRight) {
		this(leftRight, topBottom, leftRight, topBottom);
	}

	public Insets(final int left, final int top, final int right, final int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public Insets clone() {
		return new Insets(left, top, right, bottom);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Insets other = (Insets) obj;
		if (bottom != other.bottom) {
			return false;
		}
		if (left != other.left) {
			return false;
		}
		if (right != other.right) {
			return false;
		}
		if (top != other.top) {
			return false;
		}
		return true;
	}

	public Size getAggregatedSize() {
		return new Size(left + right, top + bottom);
	}

	public int getBottom() {
		return bottom;
	}

	public int getLeft() {
		return left;
	}

	public Size getOffset() {
		return new Size(left, top);
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
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
	public String toString() {
		return "[" + left + "," + top + "," + right + "," + bottom + "]";
	}

}
