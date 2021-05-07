package org.cytoscape.util.color;

import java.awt.Color;

/*
 * #%L
 * Cytoscape Color API (utils-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

public enum BrewerType implements PaletteType {
	/**
	 * Diverging palettes -- typically used to represent centered data with extremes
	 * in either direction.  In Cytoscape, these palettes might be used
	 * for a continuous mapping that has both positive and nevative values.
	 */
	DIVERGING("Diverging"),

	/**
	 * Qualitative palettes -- typically used to represent categorical data.  Choose this
	 * kind of palette for discrate mappings.
	 */
	QUALITATIVE("Qualitative"),
	/**
	 * Sequential palettes -- typically used to represent ordered data that progresses from
	 * low to high.  In Cytoscape, these palettes might be used
	 * for a continuous mapping that has either positive or negative values, but not both.
	 */
	SEQUENTIAL("Sequential"),

	/**
	 * Any (or all) palettes
	 */
	ANY("Any");

	String name;
	BrewerType(String name) {
		this.name = name;
	}
	public String toString() { return name; }
}
