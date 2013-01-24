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

import org.cytoscape.model.CyRow;

/**
 * Holds a record of a {@link CyRow} that was set and what it was set to.
 * @CyAPI.Final.Class
 */
public final class RowSetRecord {
	private final CyRow row;
	private final String column;
	private final Object value;
	private final Object rawValue;

	/**
	 * Constructs a RowSetRecord.
	 * @param row The {@link CyRow} of the data that was set.
	 * @param column The name of the column of the data that was set.
	 * @param value The value the data was set as.
	 * @param rawValue The raw value the data was set as.
	 */
	public RowSetRecord(final CyRow row, final String column,
			final Object value, final Object rawValue) {
		this.row = row;
		this.column = column;
		this.value = value;
		this.rawValue = rawValue;
	}

	/**
	 * Returns the {@link CyRow} of the data that was set.
	 * @return the {@link CyRow} of the data that was set.
	 */
	public CyRow getRow() {
		return row;
	}

	/**
	 * Returns the name of the column of the data that was set.
	 * @return the name of the column of the data that was set.
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * Returns the value that the data was set as.
	 * @return the value that the data was set as.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Returns the raw value that the data was set as.
	 * @return the raw value that the data was set as.
	 */
	public Object getRawValue() {
		return rawValue;
	}
}
