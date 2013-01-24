package org.cytoscape.view.presentation;

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

import java.util.Collection;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

/**
 * Manager for {@linkplain RenderingEngine} objects. All RenderingEngine objects
 * created by {@linkplain RenderingEngineFactory} should be registered to this
 * manager.
 * <P>
 * Register/unregister engines are handled through
 * {@linkplain org.cytoscape.view.presentation.events.RenderingEngineAddedEvent}s.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface RenderingEngineManager {

	/**
	 * Provide default {@link VisualLexicon} from a default engine factory. In
	 * current implementation, this is always DING's lexicon.
	 * 
	 * @return default VisualLexicon.
	 */
	VisualLexicon getDefaultVisualLexicon();

	/**
	 * Get rendering engines for the given view model.
	 * 
	 * @param viewModel
	 *            View model for the presentation.
	 * 
	 * @return Rendering engines (presentations) associated with the view model.
	 */
	Collection<RenderingEngine<?>> getRenderingEngines(final View<?> viewModel);

	/**
	 * Get all {@link RenderingEngine}s registered in this manager.
	 * 
	 * @return all rendering engines.
	 */
	Collection<RenderingEngine<?>> getAllRenderingEngines();

	/**
	 * Add new {@link RenderingEngine} to this manager.
	 * <p>
	 * This method fires
	 * {@link org.cytoscape.view.presentation.events.RenderingEngineAddedEvent}.
	 * 
	 * @param engine
	 *            New engine to be added.
	 */
	void addRenderingEngine(final RenderingEngine<?> engine);

	/**
	 * Remove a rendering engine.
	 * 
	 * <p>
	 * This method fires
	 * {@link org.cytoscape.view.presentation.events.RenderingEngineAboutToBeRemovedEvent}.
	 * 
	 * @param engine
	 *            engine to be removed.
	 */
	void removeRenderingEngine(final RenderingEngine<?> engine);
}