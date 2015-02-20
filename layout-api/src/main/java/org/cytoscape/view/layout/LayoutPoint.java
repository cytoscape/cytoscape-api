package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


/**
 * Simple immutable object which represents a point (x, y, z).
 * @CyAPI.Final.Class
 * @CyAPI.InModule layout-api
 */
public final class LayoutPoint {
	
	private final double x;
	private final double y;
	private final double z;

	/**
	 * 2D Constructor, the Z coordinate is set to 0.
	 * @param x The X location of the point.
	 * @param y The Y location of the point.
	 */
	public LayoutPoint(final double x, final double y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	/**
	 * 3D Constructor.
	 * @param x The X location of the point.
	 * @param y The Y location of the point.
	 * @param z The Z location of the point.
	 */
	public LayoutPoint(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	/**
	 * Returns the Z location of the point.
	 * @return the Z location of the point.
	 */
	public double getZ() {
		return z;
	}
	
	
	public String toString() {
		return String.format("(%f,%f,%f)", x,y,z);
	}
}
