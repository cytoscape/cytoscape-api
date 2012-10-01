package org.cytoscape.view.layout;


/**
 * Simple immutable object which represents a point (x, y).
 */
public final class LayoutPoint {
	
	private final double x;
	private final double y;

	/**
	 * Constructor.
	 * @param x The X location of the point.
	 * @param y The Y location of the point.
	 */
	public LayoutPoint(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the X location of the point.
	 * @return the X location of the point.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the Y location of the point.
	 * @return the Y location of the point.
	 */
	public double getY() {
		return y;
	}
}
