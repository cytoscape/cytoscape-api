package org.cytoscape.view.vizmap.gui.event;

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

/**
 * Listener for {@link LexiconStateChangedEvent}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface LexiconStateChangedListener {

	/**
	 * Handles the specified event.
	 * @param e the {@link LexiconStateChangedEvent} to handle.
	 */
	public void handleEvent(LexiconStateChangedEvent e);
}
