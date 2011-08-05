/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.model;


import java.util.List;


/** This class describes a column in a CyTable. */
public interface CyColumn {
	/** @return the name of the column. */
	String getName();

	/** Change the name of this column.
	 *  @param newName  the new column name
	 *  @throws IllegalArgumentException if the column is immutable
	 */
	void setName(String newName);

	/** @return the data type of the column. */
	Class<?> getType();

	/** @return the data type of the list elements if the column type is List.class otherwise null */
	Class<?> getListElementType(); 

	/** @return true if the column is the primary key, otherwise false. */
	boolean isPrimaryKey();

	/** @return true if the column is immutable i.e. cannot be deleted or renamed, otherwise false.
	 *  Please note that this does not affect the ability to add or modify values in this column!
	 */
	boolean isImmutable();

	/** Returns the table for this column.
	 *  @return the table that this column is a part of
	 */
	CyTable getTable();

	/** Returns all the values, some of which may be null, for this given column.
	 *  @param type  the datatype of this column.  (You can use getType() to obtain it.)
	 *  @return the values in this column in some arbitrary but consistent order
	 */
	<T> List<T> getValues(Class<? extends T> type);
	
	/**
	 * Returns information about the virtual column definition of this column.
	 * This method will return an instance even if the column is not virtual.
	 */
	VirtualColumnInfo getVirtualColumnInfo();
}