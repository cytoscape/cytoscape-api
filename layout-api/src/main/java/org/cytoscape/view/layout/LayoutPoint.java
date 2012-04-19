package org.cytoscape.view.layout;


/**
 * Simple immutable object which represents a point (x, y).
 *
 */
public final class LayoutPoint {
	
	private final double x;
	private final double y;

	public LayoutPoint(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
