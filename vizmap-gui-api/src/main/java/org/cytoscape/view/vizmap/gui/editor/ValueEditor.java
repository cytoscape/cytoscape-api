package org.cytoscape.view.vizmap.gui.editor;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.awt.Component;

/**
 * Provides a GUI Editor for a data type.
 * 
 * To support new data type, this should be implemented.
 *
 * @param <V> target data type for this editor.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface ValueEditor<V> {
	
	/**
	 * Display the editor and get a new value.
	 * @param parent the parent Component of this editor.
	 * @param initialValue the initial value for the value editor dialogue.
	 * 
	 * @return the value generated from the value editor dialogue or null.
	 */
	<S extends V> V showEditor(Component parent, S initialValue);
	
	/**
	 * Get type of the value to be edited.
	 * 
	 * @return the type of value returned by the value editor.
	 */
	Class<V> getValueType();

}
