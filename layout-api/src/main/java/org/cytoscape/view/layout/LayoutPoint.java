package org.cytoscape.view.layout;


/**
 * Simple immutable object which represents a point (x, y).
 *
 */
public final class LayoutPoint {
	
	private final Double x;
	private final Double y;

	public LayoutPoint(final Double x, final Double y) {
		this.x = x;
		this.y = y;
	}
	
	public Double getX() {
		return x;
	}
	
	public Double getY() {
		return y;
	}
	
}
