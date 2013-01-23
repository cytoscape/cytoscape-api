package org.cytoscape.view.presentation.events;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.presentation.RenderingEngine;
import org.cytoscape.view.presentation.RenderingEngineManager;

/**
 * When presentation (rendered graphics) is destroyed, this event should be
 * fired.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class RenderingEngineAboutToBeRemovedEvent extends AbstractCyEvent<RenderingEngineManager> {

	private final RenderingEngine<?> engine;

	/**
	 * Construct an event for destroyed {@linkplain RenderingEngine}.
	 * 
	 * @param source
	 *            source of event. In theory, this can be anything, but in most
	 *            cases, it will be an GUI components.
	 * @param engine
	 *            {@linkplain RenderingEngine} associated with the deleted
	 *            presentation.
	 */
	public RenderingEngineAboutToBeRemovedEvent(final RenderingEngineManager source, final RenderingEngine<?> engine) {
		super(source, RenderingEngineAboutToBeRemovedListener.class);
		this.engine = engine;
	}

	/**
	 * Returns the {@link RenderingEngine} associated with the deleted presentation.
	 * @return the {@link RenderingEngine} associated with the deleted presentation.
	 */
	public RenderingEngine<?> getRenderingEngine() {
		return this.engine;
	}

}
