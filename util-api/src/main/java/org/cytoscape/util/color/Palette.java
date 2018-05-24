package org.cytoscape.util.color;

import java.awt.Color;

/*
 * #%L
 * Cytoscape Color API (utils-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2018 The Cytoscape Consortium
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
 * This interface provides a consistent way for various providers (ColorBrewer,
 * ColourLovers, etc.) to provide color palettes to Cytoscape routines.
 **/
public interface Palette {
	/**
	 * Return the name of this palette.  This is intended to be the user-visible
	 * name, not the internal identifier.
	 *
	 * @return user-visible name of this palette
	 */
	public String getName();

	/**
	 * Return the internal identifier of this palette.  This is the internal identifier
	 * of the palette which might be different for different providers.  For example,
	 * BrewerPalettes use short names (e.g. RdBu), while ColourLovers uses an integer.
	 *
	 * @return the internal identifier for this palette
	 **/
	public Object getIdentifier();

	/**
	 * This is the type of the palette, using the Cynthia Brewer's classification.
	 * 
	 * @return the palette type
	 **/
	public PaletteType getType();

	/**
	 * Return the number of colors natively in the palette.
	 *
	 * @return the number of colors in the palette
	 */
	public int size();

	/**
	 * Return the palette as an array of colors
	 *
	 * @return the color array
	 */
	public Color[] getColors();

	/**
	 * Return the palette as an array of colors of the
	 * requested length.  If the number of colors in the
	 * palette is less than the requested number, the colors
	 * will be interpolated.
	 *
	 * @return the color array
	 */
	public Color[] getColors(int nColors);

	/**
	 * Return true if the palette is color blind safe.
	 *
	 * @return true if the palette is color blind safe
	 */
	public boolean isColorBlindSafe();

	/**
	 * Return the string representation of this palette (usually the name)
	 *
	 * @return the palette name
	 */
	public String toString();
}
