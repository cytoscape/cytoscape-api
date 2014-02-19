package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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
 * Listener for {@linkplain FitContentEvent}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface FitContentListener extends CyListener {
	
	/**
	 * Process the event.  Usually, this handler will fire event to the presentation layer and invoke the redraw method in the rendering engine.
	 * 
	 * @param e
	 */
	public void handleEvent(FitContentEvent e);
}
