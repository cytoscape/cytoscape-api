package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
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
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableManager;

/**
 * This event will be fired when new table is added to {@link CyTableManager}.
 * @CyAPI.Final.Class
 */
public final class TableAddedEvent extends AbstractCyEvent<CyTableManager> {
	
	private final CyTable table;
	
	/**
	 * Constructs the table added event.
	 * @param source  the table manager
	 * @param table   the table added to the table manager
	 */
	public TableAddedEvent(final CyTableManager source, final CyTable table) {
		super(source, TableAddedListener.class);
		this.table = table;
	}


	/**
	 * Returns the table added to the table manager.
	 * @return the table added to the table manager.
	 */
	public final CyTable getTable() {
		return table;
	}
}
