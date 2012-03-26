/*
 Copyright (c) 2008, 2010-2011, The Cytoscape Consortium (www.cytoscape.org)

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


import java.util.Collection;
import java.util.List;


/** 
 * A simple representation of a table object consisting of rows
 * and columns. Columns have names and specific types and rows
 * contain the data for a specific primary key. Tables are limited in the
 * types of data that may be stored.  The allowable types are:
 * String, Integer, Long, Double, Boolean, and Lists of those five
 * types.
 * @CyAPI.Api.Interface
 */
public interface CyTable extends CyIdentifiable {
	/**
	 * Mutability of the table specifies whether or not it is able to be deleted..
	 *
	 */
	public static enum Mutability {
		/**
		 * The table can be deleted.
		 */
		MUTABLE("mutable"),
		/**
		 * The table can not be deleted.
		 */
		PERMANENTLY_IMMUTABLE("permanently immutable"),
		/**
		 * The table can not be deleted because it currently has virtual {@link CyColumn}s referencing it.
		 */
		IMMUTABLE_DUE_TO_VIRT_COLUMN_REFERENCES("immutable due to virtual column references");

		private String humanReadableRepresentation;

		Mutability(final String humanReadableRepresentation) {
			this.humanReadableRepresentation = humanReadableRepresentation;
		}

		@Override
		final public String toString() { return humanReadableRepresentation; }
	}
	
	/**
	 * The SavePolicy of a table specifies whether or not it will be saved.
	 */
	public static enum SavePolicy {
		/** This table should not be saved i.e. this table should not be serialized. */
		DO_NOT_SAVE,  
		/** This table should be saved. */
		SESSION_FILE,
	}

	/**
	 * A public CyTable is a table that is accessible to the user through the user
	 * interface.  Private or non-public CyTables will not be visible to the user from the
	 * normal user interface, although they will be accessible to app writers through the API.
	 *
	 * @return Whether or not this CyTable should be publicly accessible.
	 */
	boolean isPublic();

	/** The table can be deleted if this returns Mutability.MUTABLE, otherwise it cannot be
	 *  deleted!
	 *  @return the current mutability state
	 */
	Mutability getMutability();

	/**
	 * Returns a human readable name for the CyTable.
	 * @return A human readable name for the CyTable.
	 */
	String getTitle();

	/**
	 * Allows the title of the table to be set. The title is meant to be
	 * human readable and suitable for use in a user interface.
	 * @param title The human readable title for the CyTable suitable for use in a user
	 *        interface.
	 */
	void setTitle(String title);

	/**
	 * Returns the column type of the primary key for this table.
	 * @return The column type of the primary key for this table.
	 */
	CyColumn getPrimaryKey();

	/**
	 * Returns the type of a column for this table.
	 * @param columnName  The name of the column whose type we desire.
	 * @return The column type of the column whose column name was provided, or null if there is
	 *         no column named "columnName".
	 */
	CyColumn getColumn(String columnName);

	/**
	 * Returns the column types for all columns in this table.
	 * @return A set of {@link CyColumn} objects that describe all columns in this table.
	 */
	Collection<CyColumn> getColumns();

	/**
	 * Will delete the column of the specified name. columnName must be not null. If the column does not exist,
	 * there is no effect. If the column is immutable, IllegalArgumentException will be thrown. If the deletion is 
	 * successful, {@link org.cytoscape.model.events.ColumnDeletedEvent} will be fired.  
	 * @param columnName The name identifying the attribute.
	 */
	void deleteColumn(String columnName);

	/**
	 * Create a column of the specified name and the specified type. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null. If the column already exists, IllegalArgumentException will be thrown.
	 * @param <T> The generic type of the column.
	 * @param columnName The name identifying the attribute.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 */
	<T> void createColumn(String columnName, Class<?extends T> type, boolean isImmutable);

	/**
	 * Create a column of the specified name and the specified type. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the column.
	 * @param columnName The name identifying the attribute.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue The default value for the column. Must be of 
	 * the specified type or null.
	 */
	<T> void createColumn(String columnName, Class<?extends T> type, boolean isImmutable, T defaultValue);

	/**
	 * Create a column of Lists with the specified name and the specified element type.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param columnName The name identifying the attribute.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 */
	<T> void createListColumn(String columnName, Class<T> listElementType, boolean isImmutable);

	/**
	 * Create a column of Lists with the specified name and the specified element type.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param columnName The name identifying the attribute.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue A default list for the column. Must be a List of 
	 * the specified element type or null.
	 */
	<T> void createListColumn(String columnName, Class<T> listElementType, boolean isImmutable, List<T> defaultValue );

	/**
	 * Returns the row specified by the primary key object and if a row
	 * for the specified key does not yet exist in the table, a new row
	 * will be created and the new row will be returned.
	 * The check for matching column names is case insensitive. 
	 * @param primaryKey The primary key index of the row to return.
	 * @return The {@link CyRow} identified by the specified key or a new
	 * row identified by the key if one did not already exist.
	 */
	CyRow getRow(Object primaryKey);

	/**
	 * Returns true if a row exists for the specified primary key and false otherwise. 
	 * @param primaryKey The primary key index of the row.
	 * @return True if a row exists for the specified primary key and false otherwise. 
	 */
	boolean rowExists(Object primaryKey);

	/**
	 * Return a list of all the rows stored in this data table.
	 * @return a list of all the rows stored in this data table.
	 */
	List<CyRow> getAllRows();

	/**
	 * Returns a descriptive message for certain internal errors.  Please
	 * note that only the very last message will be retrieved.
	 * @return if available, a message describing an internal error, otherwise null
	 */
	String getLastInternalError();

	/**
	 * Returns all the rows of a specified column that contain a certain value for that column.
	 * @param columnName  the column for which we want the rows
	 * @param value       the value for which we want the rows that contain it
	 * @return the rows, if any that contain the value "value" for the column "columnName"
	 */
	Collection<CyRow> getMatchingRows(String columnName, Object value);

	/** 
	 * Returns the number of rows with the in the specified column with the specified value. 
	 * @param columnName  the column to check 
	 * @param value       the value we want to check for 
	 * @return the number of rows with the in the specified column with the specified value. 
	 */
	int countMatchingRows(String columnName, Object value);

	/** Returns the number of rows in this table.
	 *  @return The number if rows in the table.
	 */
	int getRowCount();

	/** 
	 * Adds a "virtual" column to the the current table.
	 * @param virtualColumn  The name of the new virtual column, if this name already exists,
	 *                       new column names with -1, -2 and so appended to this name on will
	 *                       be tried until a non-existing name will be found.
	 * @param sourceColumn   The name of the column in "sourceTable" that will be mapped to
	 *                       "virtualColumn".
	 * @param sourceTable    The table that really contains the column that we're adding (all
	 *                       updates and lookups of this new column will be redirected to here).
	 *                       The table will be joined on the primary key column of this table.
	 * @param targetJoinKey  The column in current table that will be used for the join. This
	 *                       column will be joined with the primary key column of the source
	 *                       table. These columns must be of the same type!
	 * @param isImmutable    If true, this column cannot be deleted.
	 * @return The actual name of the new virtual column.
	 */
	String addVirtualColumn(String virtualColumn, String sourceColumn, CyTable sourceTable,
	                        String targetJoinKey, boolean isImmutable);

	/** Adds all columns in another table as "virtual" columns to the the current table.
	 *  @param sourceTable    The table that really contains the column that we're adding (all
	 *                        updates and lookups of this new column will be redirected to here).
	 *                        The table will be joined on the primary key column of this table.
	 *                        None of the column names in "sourceTable" must exist in the current table!
	 *  @param targetJoinKey  The column in current table that will be used for the join. This
	 *                        column will be joined with the primary key column of the source
	 *                        table. These columns must be of the same type!
	 *  @param isImmutable    If true, these columns cannot be deleted.
	 */
	void addVirtualColumns(CyTable sourceTable, String targetJoinKey, boolean isImmutable);
	
	/**
	 * Returns how (or if) this CyTable should be saved.
	 * @return how (or if) this CyTable should be saved.
	 */
	SavePolicy getSavePolicy();
	
	/**
	 * Sets how (or if) this CyTable should be saved.
	 * @param policy the policy to follow during the life-cycle of the CyTable.
	 */
	void setSavePolicy(SavePolicy policy);

	/** Swaps the contents and properties, like mutability etc. of "otherTable" with this table.
	 *  @param otherTable  the table that we're being swapped with.
	 *  Note: the one "property" that is not being swapped is the SUID!  Also, no events are being
	 *        fired to give any listeners a chance to react to the exchange!
	 */
	void swap(CyTable otherTable);
}
