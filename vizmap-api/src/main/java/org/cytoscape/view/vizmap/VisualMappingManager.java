package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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

import java.util.Set;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.vizmap.events.VisualStyleAboutToBeRemovedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleAddedEvent;

/**
 * Manager for {@linkplain VisualStyle}s. This object manages mapping from view
 * model to VisualStyle. User objects can access all VisualStyles and
 * VisualMappingFunctions through this class.
 * 
 * <p>
 * Add/Remove operations will be done through events. For more information, read
 * JavaDoc for {@linkplain VisualStyleAddedEvent} and
 * {@linkplain VisualStyleAboutToBeRemovedEvent}.
 * </p>
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualMappingManager {

	/**
	 * Set a {@linkplain VisualStyle} to the target network view model.
	 * 
	 * @param visualStyle Visual Style to be set.
	 * @param networkViewModel The target network view model.
	 */
	void setVisualStyle(final VisualStyle visualStyle, final CyNetworkView networkViewModel);

	/**
	 * Returns the {@linkplain VisualStyle} associated with the target network
	 * view model.
	 * 
	 * @param networkViewModel
	 *            Target network view
	 * 
	 * @return VisualStyle associated with the network view model. This is
	 *         always non-null value. If there is no mapping from given view
	 *         model to a style, then default style will be used.
	 */
	VisualStyle getVisualStyle(final CyNetworkView networkViewModel);

	/**
	 * Returns all available {@linkplain VisualStyle}s managed by this object.
	 * 
	 * @return Set of all registered VisualStyles.
	 * 
	 */
	Set<VisualStyle> getAllVisualStyles();

	/**
	 * Add a new {@link VisualStyle} to this manager.
	 * 
	 * @param visualStyle
	 *            new visual style to be registered.
	 */
	void addVisualStyle(final VisualStyle visualStyle);

	/**
	 * Remove a VisualStyle from this manager.
	 * 
	 * @param visualStyle
	 *            VisualStyle to be removed.
	 */
	void removeVisualStyle(VisualStyle visualStyle);

	/**
	 * Return default {@link VisualStyle}.  This is just an empty visual style.
	 * 
	 * @return default Visual Style.
	 */
	VisualStyle getDefaultVisualStyle();
	
	/**
	 * Set the specified {@link VisualStyle} as the current one. 
	 * 
	 * @param visualStyle the {@link VisualStyle} that will become the current style.
	 */
	void setCurrentVisualStyle(VisualStyle visualStyle);
	
	/**
	 * Returns currently selected Visual Style.
	 * 
	 * @return Selected Visual Style.
	 */
	VisualStyle getCurrentVisualStyle();
	
	
	/**
	 * Returns a Set of all {@link VisualLexicon}s.
	 * 
	 * @return a Set of all {@link VisualLexicon}s.
	 */
	Set<VisualLexicon> getAllVisualLexicon();

}
