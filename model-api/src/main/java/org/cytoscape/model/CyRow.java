/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
import java.util.Map;


/**
 * This interface represents one row in a CyTable.
 * @CyAPI.Api.Interface
 */
public interface CyRow {
	/**
	 * Returns the value found for this row in the specified column
	 * with the specified type. If the column name doesn't exist
	 * @param <T> The generic type of the specified column.
	 * @param columnName The name identifying the attribute.
	 * @param type The type of the column.
	 * @return The value found for this row in the specified column and
	 * null if the column does not exist.
	 * Please note that this method should not be used to retrieve values that are Lists!
	 */
	<T> T get(String columnName, Class<?extends T> type);

	/**
	 * Returns the value found for this row in the specified column
	 * with the specified type.
	 * @param <T> The generic type of the specified column.
	 * @param columnName The name identifying the attribute.
	 * @param type The type of the column.
	 * @param defaultValue The value to return if the column has not previously been set. 
	 * @return The value found for this row in the specified column, the default value
	 * if the row has not yet been set, and null if the column does not exist.
	 * Please note that this method should not be used to retrieve values that are Lists!
	 */
	<T> T get(String columnName, Class<?extends T> type, T defaultValue);

	/**
	 * Returns the value found for this row in the specified column
	 * with the specified type.
	 * @param <T> the generic type of the elements of the list we wish to retrieve.
	 * @param columnName The name identifying the attribute.
	 * @param listElementType  The type of the elements of the list that we wish to retrieve.
	 * @return The value found for this row in the specified column and
	 * null if the column does not exist.
	 * Please note that this method can only be used to retrieve values that are Lists!
	 */
	<T> List<T> getList(String columnName, Class<T> listElementType);

	/**
	 * Returns the value found for this row in the specified column
	 * with the specified type.
	 * @param <T> the generic type of the elements of the list we wish to retrieve.
	 * @param columnName The name identifying the attribute.
	 * @param listElementType  The type of the elements of the list that we wish to retrieve.
	 * @param defaultValue The List to return if the column has not previously been set. 
	 * @return The value found for this row in the specified column, the default value
	 * if the row has not yet been set, and null if the column does not exist.
	 * Please note that this method can only be used to retrieve values that are Lists!
	 */
	<T> List<T> getList(String columnName, Class<T> listElementType, List<T> defaultValue);

	/**
	 * Set the specified column for this row to the specified value.
	 * To unset a column entry use null for value.
	 * @param <T> The generic type of the value to assign the specified column in this row.
	 * @param columnName The name identifying the attribute.
	 * @param value The value to assign the specified column in this row
	 * Please note that if "value" is a List it is your responsibility that all the
	 * elements are of the type specified when the column was created with
	 * {@link CyTable#createListColumn}!
	 * @throws IllegalArgumentException If the column does not yet exist or if the 
	 * the value does not match the column type.
	 */
	<T> void set(String columnName, T value);

	/**
	 * Indicates whether the column of the specified type contains
	 * a non-null value.
	 * @param columnName The name identifying the attribute.
	 * @return true if the value specified in this row at this column
	 * of the specified type is not null.
	 */
	boolean isSet(String columnName);

	/**
	 * Returns a map of column names to Objects that contain the values
	 * contained in this Row.
	 * @return A map of column names to Objects that contain the values
	 * contained in this Row.
	 */
	Map<String, Object> getAllValues();

	/**
	 * Returns the Object that contains the value for the specified column.
	 * The returned object may or may not be of the type that get() for this 
	 * column will return, for example it may return an equation object that
	 * has not yet been evaluated!
	 * @param columnName The name identifying the attribute.
	 * @return The row Object that represents the value in a column.
	 */
	Object getRaw(String columnName);

	/**
	 * Returns the {@link CyTable} that this row belongs to.
	 * @return the {@link CyTable} that this row belongs to.
	 */
	CyTable getTable();
}
