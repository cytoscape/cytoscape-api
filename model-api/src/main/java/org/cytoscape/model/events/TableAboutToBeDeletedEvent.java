package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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
 * This event signals that a table is about to be deleted. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class TableAboutToBeDeletedEvent extends AbstractCyEvent<CyTableManager> {
	private final CyTable table;
	
	/**
	 * Constructs the event.
	 * @param source  the table manager
	 * @param table   the table in which is about to be deleted from the table manager
	 */
	public TableAboutToBeDeletedEvent(final CyTableManager source, final CyTable table) {
		super(source, TableAboutToBeDeletedListener.class);
		this.table = table;
	}


	/**
	 * Returns the table that is about to be deleted from the table manager.
	 * @return the table that is about to be deleted from the table manager.
	 */
	public final CyTable getTable() {
		return table;
	}
}
