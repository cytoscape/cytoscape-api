package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
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
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;


class AbstractColumnEvent extends AbstractCyEvent<CyTable> {

	private final String columnName;

	AbstractColumnEvent(final CyTable source, final Class<?> listenerClass, final String columnName) {
		super(source, listenerClass);
		if ( columnName == null )
			throw new NullPointerException("columnName can't be null");
		this.columnName = columnName;
	}

	/**
	 * Returns the fully-qualified name of the column for this event.
	 * @return The name of the column for this event.
	 */
	public String getColumnName() {
		return columnName;
	}
	
	/**
	 * Returns the namespace of the column, or null if the column does not have a namespace.
	 * Default columns created by Cytoscape do not have a namespace.
	 */
	public String getNamespace() {
		return CyColumn.splitColumnName(columnName)[0];
	}
	
	/**
	 * Returns the name portion without the namespace.
	 */
	public String getNameOnly() {
		return CyColumn.splitColumnName(columnName)[1];
	}
}
