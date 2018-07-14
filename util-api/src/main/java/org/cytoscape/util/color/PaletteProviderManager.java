package org.cytoscape.util.color;

import java.awt.Color;
import java.util.List;

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
 * The PaletteProviderManager provides a way for palette providers
 * to register themselves.
 **/
public interface PaletteProviderManager {
	/**
	 * Return the list of currently registered palette providers
	 *
	 * @return the list of palette providers
	 **/
	public List<PaletteProvider> getPaletteProviders();

	/**
	 * Return the list of currently registered palette providers
	 * that provide palettes of the requested type.
	 *
	 * @param type the type of the palette
	 * @param colorSafe true if the palette should provide color safe palettes
	 * @return the list of palette providers
	 **/
	public List<PaletteProvider> getPaletteProviders(PaletteType type, boolean colorSafe);

	/**
	 * Return the palette provider that matches the name in the argument.
	 *
	 * @param provider the name of the provider.
	 * @return the named palette provider
	 */
	public PaletteProvider getPaletteProvider(String provider);

	/**
	 * Add a palette provider to the manager.
	 *
	 * @param provider the provider to add.
	 */
	public void addPaletteProvider(PaletteProvider provider);

	/**
	 * Remove a palette provider from the manager.
	 *
	 * @param provider the provider to remove.
	 */
	public void removePaletteProvider(PaletteProvider provider);

	/**
	 * Save a palette.  This is a convenience method that provides a way for apps
	 * (and the core) to remember palettes that were previously used.  The main
	 * idea is that palettes can be saved with an arbitrary key (use the full name
	 * of the class to avoid conflicts) and recalled later.  This mechanism is
	 * used internally by the Styles (vizmap) interface to remember palette
	 * selections used for color visual properties.
	 *
	 * @param key the key to save this palette under
	 * @param palette the {@link Palette} to save
	 */
	public void savePalette(Object key, Palette palette);

	/**
	 * Retrieve a palette previously saved
	 *
	 * @param key the key that was used to save the {@link Palette} 
	 * @return the saved {@link Palette} or null
	 */
	public Palette retrievePalette(Object key);
}
