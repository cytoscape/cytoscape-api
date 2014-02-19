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

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

/**
 * This event signals that rows have been Deleted.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class RowsDeletedEvent extends AbstractCyEvent<CyTable>  {
	
	private final Collection<Object> keys;

	/**
	 * Constructs event.
	 * @param source the table in which the rows have been Deleted.
	 * @param primaryKeys the primary keys of the rows that have been Deleted.
	 */
	public RowsDeletedEvent(CyTable source, Collection<Object> primaryKeys) {
		super(source, RowsDeletedListener.class);
		if ( primaryKeys == null )
			throw new NullPointerException("Keys is null");
		keys = primaryKeys;
	}
	
	/**
	 * Returns the primary keys of the rows for this event.
	 * @return The the primary keys for this event.
	 */
	public Collection<Object> getKeys() {
		return keys;
	}
}
