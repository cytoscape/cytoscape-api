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

import javax.swing.ImageIcon;

/**
 * A basic interface used to display continuous mapping editors.
 * @param <K> The numeric type of the attribute.
 * @param <V> The generic type of the visual property the attribute is being mapped to. 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface ContinuousMappingEditor<K extends Number, V> {

	/**
	 * Returns an icon representing this editor for use in a user interface.
	 * @param width The width of the icon. 
	 * @param height The height of the icon. 
	 * @param detail Whether to include high detail or not.
	 * @return an icon representing this editor for use in a user interface.
	 */
	public abstract ImageIcon drawIcon(int width, int height, boolean detail);

}
