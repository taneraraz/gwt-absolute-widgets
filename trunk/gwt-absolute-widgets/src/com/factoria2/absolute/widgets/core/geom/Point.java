package com.factoria2.absolute.widgets.core.geom;

/**
 * Points are read-only
 * 
 * @author Iv�n
 */
public class Point {

	public static final Point ORIGIN = new Point(0, 0);

	private int x;
	private int y;

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public Size asSize() {
		return new Size(x, y);
	}

	public Point clone() {
		return new Point(x, y);
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
		Point other = (Point) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	public Point getOpposite() {
		return new Point(-getX(), -getY());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public Point moveBy(final Size p) {
		Point point = this.clone();
		point.x += p.getWidth();
		point.y += p.getHeight();
		return point;
	}

	public Point offsetTo(final Point p) {
		return new Point(p.getX() - x, p.getY() - y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
