package org.cytoscape.view.vizmap.events.table;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.vizmap.TableVisualMappingManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;

/**
 * When new {@linkplain VisualStyle} is added to the {@link VisualMappingManager},
 * it fires this event.
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class ColumnVisualStyleAddedEvent extends AbstractCyEvent<TableVisualMappingManager> {

	private final VisualStyle style;

	/**
	 * Creates an event for the newly created style.
	 * 
	 * @param source Source of this event.  This is always {@link TableVisualMappingManager}.
	 * @param created New VisualStyle
	 */
	public ColumnVisualStyleAddedEvent(TableVisualMappingManager source, VisualStyle created) {
		super(source, ColumnVisualStyleAddedListener.class);
		this.style = created;
	}

	/**
	 * Get the newly created VisualStyle.
	 * 
	 * @return new VisualStyle
	 */
	public VisualStyle getVisualStyleAdded() {
		return style;
	}
}
