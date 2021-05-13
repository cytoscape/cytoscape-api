package org.cytoscape.view.vizmap.events;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * Event fired when a {@link VisualStyle} is set to a network view through 
 * {@linkplain VisualMappingManager#setVisualStyle(VisualStyle, org.cytoscape.view.model.CyNetworkView)}.
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class VisualStyleSetEvent extends AbstractCyEvent<VisualMappingManager> {

	private final VisualStyle style;
	private final CyNetworkView view;

	/**
	 * Creates the event.
	 * 
	 * @param source Source of this event.  This is always {@link VisualMappingManager}.
	 * @param style The VisualStyle that was set to the network view.
	 * @param view The target CyNetworkView.
	 */
	public VisualStyleSetEvent(final VisualMappingManager source, final VisualStyle style, CyNetworkView view) {
		super(source, VisualStyleSetListener.class);
		this.style = style;
		this.view = view;
	}

	/**
	 * Get the VisualStyle that was set to the network view.
	 * @return The VisualStyle that was set to the network view.
	 */
	public VisualStyle getVisualStyle() {
		return style;
	}
	
	/**
	 * Get the The target network view model.
	 * @return The target CyNetworkView.
	 */
	public CyNetworkView getNetworkView() {
		return view;
	}
}
