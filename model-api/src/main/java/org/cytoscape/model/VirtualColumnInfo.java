package org.cytoscape.model;

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

/**
 * Information object for columns that stores extra Virtual Column information
 * if applicable. See {@link CyTable#addVirtualColumn} for details on
 * what virtual columns are.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface VirtualColumnInfo {
	/**
	 * Returns true if the column is virtual, otherwise false.
	 * @return true if the column is virtual, otherwise false.
	 */
	boolean isVirtual();
	
	/**
	 * Returns the name of the column from the source table which contains the
	 * values this column provides. 
	 * @return the name of the column from the source table which contains the
	 * values this column provides.
	 */
	String getSourceColumn();
	
	/**
	 * Returns the name of the column from the source table used for the join.
	 * @return the name of the column from the source table used for the join.
	 */
	String getSourceJoinKey();
	
	/**
	 * Returns the name of the column from the target table used for the join.
	 * @return the name of the column from the target table used for the join.
	 */
	String getTargetJoinKey();
	
	/**
	 * Returns the originating table for this column if this column is virtual.
	 * @return the originating table for this column if this column is virtual.
	 */
	CyTable getSourceTable();

	/**
	 * Returns true if this virtual column was originally created
	 * immutable.
	 * @return true if this virtual column was originally created immutable.
	 */
	boolean isImmutable();
}
