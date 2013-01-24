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

import org.cytoscape.model.CyTable;


/**
 * This event signals that a column has been created.
 *<p>
 * This should probably return the type parameter as well. 
 * @CyAPI.Final.Class
 */
public final class ColumnCreatedEvent extends AbstractColumnEvent {
	/**
	 * Constructs event.
	 * @param source The table in which the column was created.
	 * @param columnName The name of the column created.
	 */
	public ColumnCreatedEvent(final CyTable source, final String columnName) {
		super(source, ColumnCreatedListener.class, columnName);
	}
}
