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


/**
 * This event signals that a columns name has been changed.
 * @CyAPI.Final.Class 
 */
public final class ColumnNameChangedEvent extends AbstractCyEvent<CyTable> {
	private final String oldColumnName;
	private final String newColumnName;

	/**
	 * Constructs event.
	 * @param source the source table of the column.
	 * @param oldColumnName the columns old name before the change.
	 * @param newColumnName the name the column name was changed to.
	 */
	public ColumnNameChangedEvent(final CyTable source, final String oldColumnName,
				      final String newColumnName)
	{
		super(source, ColumnNameChangedListener.class);

		if (oldColumnName == null)
			throw new NullPointerException("\"oldColumnName\" must not be null.");
		this.oldColumnName = oldColumnName;

		if (newColumnName == null)
			throw new NullPointerException("\"newColumnName\" must not be null.");
		this.newColumnName = newColumnName;
	}

	/**
	 * Returns the old name of the column.
	 * @return the old name of the column
	 */
	public String getOldColumnName() {
		return oldColumnName;
	}

	/**
	 * Returns the new name of the column.
	 * @return the new name of the column
	 */
	public String getNewColumnName() {
		return newColumnName;
	}
}
