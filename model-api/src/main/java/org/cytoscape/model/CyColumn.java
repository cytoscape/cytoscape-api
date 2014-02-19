package org.cytoscape.model;

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


import java.util.List;


/** This class describes a column in a CyTable. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyColumn {
	/** 
	 * Returns the name of the column.
	 * @return the name of the column. 
	 */
	String getName();

	/** Change the name of this column. If another column with a matching name already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 *  @param newName  the new column name
	 *  @throws IllegalArgumentException if the column is immutable
	 */
	void setName(String newName);

	/** 
	 * Returns the data type of the column.
	 * @return the data type of the column. 
	 */
	Class<?> getType();

	/** 
	 * Returns the data type of the list elements if the column type is List.class otherwise null.
	 * @return the data type of the list elements if the column type is List.class otherwise null 
	 */
	Class<?> getListElementType(); 

	/** 
	 * Returns true if the column is the primary key, otherwise false.
	 * @return true if the column is the primary key, otherwise false. 
	 */
	boolean isPrimaryKey();

	/** 
	 * Returns true if the column is immutable i.e. cannot be deleted or renamed, otherwise false.
	 * @return true if the column is immutable i.e. cannot be deleted or renamed, otherwise false.
	 *  Please note that this does not affect the ability to add or modify values in this column!
	 */
	boolean isImmutable();

	/** Returns the table for this column.
	 *  @return the table that this column is a part of
	 */
	CyTable getTable();

	/** Returns all the values, some of which may be null, for this given column. When type is List.class, call getListElementType() get the type of the list elements.
	 * @param <T> the generic type of the column.
	 *  @param type  the datatype of this column.  (You can use getType() to obtain it.)
	 *  @return the values in this column in some arbitrary but consistent order. When type is List.class, a List<List> is returned.
	 */
	<T> List<T> getValues(Class<? extends T> type);
	
	/**
	 * Returns information about the virtual column definition of this column.
	 * This method will return an instance even if the column is not virtual.
	 * @return an instance of {@link VirtualColumnInfo}.
	 */
	VirtualColumnInfo getVirtualColumnInfo();

	/**
	 * Returns the default value for the column, possibly null.
	 * @return The default value for the column, possibly null.
	 */
	 Object getDefaultValue();
}
