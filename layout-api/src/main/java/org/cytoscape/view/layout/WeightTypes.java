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
 * An enum describing different weighting strategies. 
 * @CyAPI.Enum.Class
 * @CyAPI.InModule layout-api
 */
public enum WeightTypes {
	/** Use a heuristic to guess. */
	GUESS("Heuristic"),
	/** Use the negative log value. */
	LOG("-Log(value)"),
	/** Use 1 minus the normalized value. */
	DISTANCE("1 - normalized value"),
	/** Use the normalized value. */
	WEIGHT("normalized value");

	private String name;
	private WeightTypes(String str) { name=str; }

	/**
	 * Returns the name of the weighting type.
	 * @return the name of the weighting type.
	 */	
	public String toString() { return name; }
}
