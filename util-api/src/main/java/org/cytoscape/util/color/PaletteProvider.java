package org.cytoscape.util.color;

import java.awt.Color;
import java.util.List;

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

/**
 * This interface provides a consistent way for various providers (ColorBrewer,
 * ColourLovers, etc.) to provide color palettes to Cytoscape routines.
 **/
public interface PaletteProvider {
	/**
	 * Return the human-readable string identifier for this PaletteProvider. 
	 * 
	 * @return the name
	 */
	public String getProviderName();

	/**
	 * Return the list of {@link org.cytoscape.util.color.Palette.PaletteType}s
	 * supported by this provider
	 * 
	 * @return the list of palette types supported
	 */
	public List<PaletteType> getPaletteTypes();

	/**
	 * List all of the palettes available from this provider, optionally restricted by
	 * the palette type and whether or not the palette is color blind safe.  To get all
	 * palettes, use a PaletteType of 'ANY'.
	 *
	 * @param type the PaletteType to restrct the list to
	 * @param colorBlindSafe if this provider distinguishes between color blind safe palettes or not
	 * @return a list of palette names
	 **/
	public List<String> listPaletteNames(PaletteType type, boolean colorBlindSafe);

	/**
	 * List all of the palettes available from this provider, optionally restricted by
	 * the palette type and whether or not the palette is color blind safe.  To get all
	 * palettes, use a PaletteType of 'ANY'.
	 *
	 * @param type the PaletteType to restrct the list to
	 * @param colorBlindSafe if this provider distinguishes between color blind safe palettes or not
	 * @return a list of palette identifiers
	 **/
	public List<Object> listPaletteIdentifiers(PaletteType type, boolean colorBlindSafe);

	/**
	 * Get a palette by its name
	 * 
	 * @param paletteName the name of the palette to return
	 * @return the palette
	 **/
	public Palette getPalette(String paletteName);

	/**
	 * Get a palette of the specified size by its name
	 * 
	 * @param paletteName the name of the palette to return
	 * @param size the number of colors in the palette
	 * @return the palette or null if no palette of that size exists
	 **/
	public Palette getPalette(String paletteName, int size);

	/**
	 * Get a palette by its internal identifier 
	 * 
	 * @param paletteIdentifier the identifier of the palette to return
	 * @return the palette
	 **/
	public Palette getPalette(Object paletteIdentifier);

	/**
	 * Get a palette by its internal identifier 
	 * 
	 * @param paletteIdentifier the identifier of the palette to return
	 * @return the palette or null if no palette of that size exists
	 * @return the palette
	 **/
	public Palette getPalette(Object paletteIdentifier, int size);
}
