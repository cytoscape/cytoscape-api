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


import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

/**
 * A factory class to create visualization for a given view model. One
 * visualization (presentation) have one rendering engine
 * 
 * @param <T>
 *            Compatible data model for this factory. For example, if this
 *            parameter is set to {@linkplain org.cytoscape.model.CyNetwork}, the factory creates rendering
 *            engine for {@link org.cytoscape.model.CyNetwork} objects.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface RenderingEngineFactory<T> {


	/**
	 * A view model can have multiple presentations. This enable developers
	 * to render multiple View Models in the same display. For example, if
	 * View<CyNetwork> and View<Decoration> are passed to this, both of them
	 * will be rendered in a window, using same rendering engine.
	 * 
	 * @param visualizationContainer
	 *            Window component which contains the rendered view. In most
	 *            cases, {@linkplain java.awt.Window} components in Swing will be used.
	 * @param viewModel
	 *            view-model to be rendered by the RenderingEngine.
	 * 
	 * @return Rendering Engine for visualization on the visualizationContainer.
	 */
	RenderingEngine<T> createRenderingEngine(final Object visualizationContainer, final View<T> viewModel);
	
	
	/**
	 * Returns supported VisualLexicon supported by this rendering engine implementation.
	 * 
	 * @return lexicon for this implementation.
	 */
	VisualLexicon getVisualLexicon();

}
