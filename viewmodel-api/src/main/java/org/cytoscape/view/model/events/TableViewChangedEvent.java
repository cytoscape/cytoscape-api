package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.table.CyTableView;

/**
 * This event is fired when a {@link org.cytoscape.view.model.VisualProperty} value
 * is set or removed from a {@link org.cytoscape.view.model.View}. 
 * <p>
 * This event should be created and fired by the view being updated and not anyone else.
 * @param <T>
 * 
 * @CyAPI.Final.Class 
 * @CyAPI.InModule viewmodel-api
 */
public final class TableViewChangedEvent<T> extends AbstractCyPayloadEvent<CyTableView, ViewChangeRecord<T>> {

	
	public TableViewChangedEvent(final CyTableView source, final Collection<ViewChangeRecord<T>> payload) {
		super(source, TableViewChangedListener.class, payload);
	}
}
