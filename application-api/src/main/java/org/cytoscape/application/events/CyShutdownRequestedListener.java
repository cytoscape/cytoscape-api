package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

import org.cytoscape.event.CyListener;

/**
 * The listener for the {@link CyShutdownRequestedEvent}.
 * 
 * Apps should not listen for {@link CyShutdownRequestedEvent}. It is used internally by Cytoscape to prompt the user
 * if they want to cancel exiting Cytoscape. Apps should only use {@link CyShutdownListener} to be notified
 * of shutdown.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 * @CyAPI.NoReference.Interface
 */
public interface CyShutdownRequestedListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CyShutdownRequestedEvent e);
}
