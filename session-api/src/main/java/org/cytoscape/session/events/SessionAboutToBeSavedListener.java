package org.cytoscape.session.events;

/*
 * #%L
 * Cytoscape Session API (session-api)
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

import org.cytoscape.event.CyListener;

/**
 * Any object that needs to know that a {@link org.cytoscape.session.CySession} is about to be
 * created should listen to this event.  Additionally, apps can set
 * a list of files to be saved in the CySession using the
 * appropriate method in the {@link SessionAboutToBeSavedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule session-api
 */
public interface SessionAboutToBeSavedListener extends CyListener {
	
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(SessionAboutToBeSavedEvent e);
}
