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

import java.beans.PropertyChangeEvent;

/**
 * Handler for Vizmap-GUI-local {@link PropertyChangeEvent}.
 * 
 * Once GUI-local {@link PropertyChangeEvent} is fired,
 * {@link java.beans.PropertyChangeListener} catches the event and one of these
 * handlers will be called and processes the event.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface VizMapEventHandler {
	
	/**
	 * Process event.
	 * 
	 * @param e GUI-local event to be processed.
	 */
	void processEvent(final PropertyChangeEvent e);
}