package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.RenderingEngine;

/**
 * This event signals that a {@link RenderingEngine} has been set as current.
 * @CyAPI.Final.Class
 */
public final class SetCurrentRenderingEngineEvent extends
		AbstractCyEvent<CyApplicationManager>{
	
	private final RenderingEngine<CyNetwork> engine;

	/**
	 * Constructor.
	 * 
	 * @param source The application manager firing this event.
	 * @param engine The rendering engine (presentation) that has been set as current.
	 */
	public SetCurrentRenderingEngineEvent(final CyApplicationManager source, final RenderingEngine<CyNetwork> engine) {
		super(source, SetCurrentRenderingEngineListener.class);
		this.engine = engine;
	}
	
	/**
	 * Return the rendering engine (presentation) that has been set as current.
	 * @return the rendering engine (presentation) that has been set as current.
	 */
	public RenderingEngine<CyNetwork> getRenderingEngine() {
		return this.engine;
	}
}