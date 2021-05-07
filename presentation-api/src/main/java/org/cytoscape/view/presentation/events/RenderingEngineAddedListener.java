package org.cytoscape.view.presentation.events;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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
 * Listener for {@linkplain RenderingEngineAddedEvent}.
 * {@linkplain org.cytoscape.view.presentation.RenderingEngineManager} implementation should implement this
 * interface, too.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface RenderingEngineAddedListener extends CyListener {

	/**
	 * Listener can extract source RenderingEngine object in this method. This
	 * is mainly for {@linkplain org.cytoscape.view.presentation.RenderingEngineManager}.
	 * 
	 * @param e an event object which contains source RenderingEngine.
	 */
	void handleEvent(final RenderingEngineAddedEvent e);
}
