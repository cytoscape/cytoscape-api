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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.presentation.RenderingEngine;
import org.cytoscape.view.presentation.RenderingEngineManager;

/**
 * When new presentation is created, its factory always creates a {@linkplain RenderingEngine}).
 * The engine should be added to {@link RenderingEngineManager} and it fires this event.
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class RenderingEngineAddedEvent extends
		AbstractCyEvent<RenderingEngineManager> {

	private final RenderingEngine<?> engine;

	/**
	 * Create an event for newly created presentation.
	 * 
	 * @param source manager holding reference to this new rendering engine
	 * @param engine New {@linkplain RenderingEngine} just created.
	 */
	public RenderingEngineAddedEvent(final RenderingEngineManager source,
			final RenderingEngine<?> engine) {
		super(source, RenderingEngineAddedListener.class);
		this.engine = engine;

	}

	/**
	 * Get new rendering engine.
	 * 
	 * @return new rendering engine.
	 */
	public RenderingEngine<?> getRenderingEngine() {
		return this.engine;
	}

}
