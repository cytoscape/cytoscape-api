package org.cytoscape.model;

import static org.cytoscape.model.CyColumn.joinColumnName;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
 * A simple representation of a table object consisting of rows
 * and columns. Columns have names and specific types and rows
 * contain the data for a specific primary key. Tables are limited in the
 * types of data that may be stored.  The allowable types are:
 * String, Integer, Long, Double, Boolean, and Lists of those five
 * types. For column naming conventions: 
 * {@link org.cytoscape.model org.cytoscape.model} document.
 * 
 * <h1>Column Namespaces</h1>
 * <p>
 * A column name can be broken down into two parts, a "namespace" and a "name". The namespace and name
 * can be combined into a String by separating them with a "::", for example: "MyNamespace::MyName".
 * The namespace part is optional, for example the column name "MyName" does not have a namespace.
 * All methods that take a namespace argument will accept null to indicate no namespace.
 * All of the default network columns created by Cytoscape do not have a namespace.
 * </p>
 * <p>
 * Apps are encouraged to put any columns they create into a namespace. The advantages of doing this are:
 * <ol>
 * <li>Avoid name collision with other apps.</li>
 * <li>Enable UI features based on namespaces.</li>
 * <li>Use namespace aware APIs in CyColumn, CyRow and CyTable</li>
 * </ol>
 * </p>
 *
 * <h1>Column Naming Rules</h1>
 * <p>
 * Case is ignored when column names are compared, or example "MyColumnName" and "mycolumnname" are equivalent.
 * This also applies to namespaces, for example "MyNamespace::MyName" and "mynamespace::myname" are equivalent.
 * </p>
 * <p>
 * Whitespace is significant in both the namespace and the name (for historical reasons), for example " mynamespace ::myname" is in the
 * namespace " mynamespace ". The empty string is a valid name for a namespace, for example "::myname" is in the "" namespace.
 * It is highly recommended not to use whitespace in namespace identifiers, stick with alphanumeric characters and underscores.
 * </p>
 * <p>
 * Pick a namespace identifier that is between 6-15 characters in length that does not contain whitespace or special characters. 
 * Good examples are "EnrichmentMap", "clusterMaker" or "WordCloud".
 * </p>
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
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
		public final String toString() { return humanReadableRepresentation; }
	}
	
	/**
	 * A public CyTable is a table that is accessible to the user through the user
	 * interface.  Private or non-public CyTables will not be visible to the user from the
	 * normal user interface, although they will be accessible to app writers through the API.
	 *
	 * @return Whether or not this CyTable should be publicly accessible.
	 */
	boolean isPublic();
	
	/**
	 * Sets the privacy flag for the CyTable. A public CyTable is a table that is accessible to the user through the user
	 * interface.  Private or non-public CyTables will not be visible to the user from the
	 * normal user interface, although they will be accessible to app writers through the API.
	 * This method may fire CyTablePrivacyChangedEvent.
	 * @param isPublic if true, the table will be public and if false, the table will be private.
	 */
	void setPublic(boolean isPublic);

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
	 * Returns the column with the specified fully-qualified name. 
	 * @param fullyQualifiedName The fully-qualified name of the column, not null.
	 * @return The column for the name provided, or null if there is no column with the given name.
	 * @see CyTable#getColumn(String, String)
	 */
	CyColumn getColumn(String fullyQualifiedName);
	
	/**
	 * Returns the column for the specified name in the specified namespace. 
	 * Default columns created by Cytoscape do not have a namespace.
	 * @param name The name of the column, not null.
	 * @param namespace The namespace of the column, or null to indicate no namespace.
	 * @return The column for the name provided, or null if there is no column with the given namespace and name.
	 */
	default CyColumn getColumn(String namespace, String name) {
		return getColumn(joinColumnName(namespace, name));
	}

	/**
	 * Returns the column types for all columns in this table.
	 * @return A set of {@link CyColumn} objects that describe all columns in this table.
	 */
	Collection<CyColumn> getColumns();
	
	/**
	 * Returns the column types for all columns in this table in the given namespace.
	 * @param namespace The namespace, or null to indicate no namespace.
	 * @return A set of {@link CyColumn} objects that describe all columns in the given namespace.
	 */
	default Collection<CyColumn> getColumns(String namespace) {
		return getColumns().stream()
				.filter(col -> Objects.equals(col.getNamespace(), namespace))
				.collect(Collectors.toList());
	}

	/**
	 * Returns all the namespaces used in the table.
	 * If there is a column in the default namespace then the returned collection will contain null.
	 */
	default Collection<String> getNamespaces() {
		return getColumns().stream()
				.map(CyColumn::getNamespace)
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	/**
	 * Will delete the column with the specified fully-qualified name. If the column does not exist,
	 * there is no effect. If the column is immutable, IllegalArgumentException will be thrown. If the deletion is 
	 * successful, {@link org.cytoscape.model.events.ColumnDeletedEvent} will be fired.  
	 * Default columns created by Cytoscape do not have a namespace.
	 * @param fullyQualifiedName The fully-qualified name of the column, not null.
	 * @see CyTable#deleteColumn(String, String)
	 */
	void deleteColumn(String fullyQualifiedName);
	
	/**
	 * Will delete the column of the specified name in the specified namespace. If the column does not exist,
	 * there is no effect. If the column is immutable, IllegalArgumentException will be thrown. If the deletion is 
	 * successful, {@link org.cytoscape.model.events.ColumnDeletedEvent} will be fired.  
	 * @param columnName The name identifying the attribute, must not be null.
	 * @param namespace The namespace of the column, or null to indicate no namespace.
	 */
	default void deleteColumn(String namespace, String columnName) {
		deleteColumn(joinColumnName(namespace, columnName));
	}

	/**
	 * Create a column of the specified name and the specified type. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null.
	 * If the column name has the suffix ".SUID" and the column type is Long, Cytoscape will handle it as a column
	 * of SUIDs, and automatically update its values when the session file is loaded. However only SUIDs of CyNodes,
	 * CyEdges and CyNetworks can be updated.
	 * If the column already exists, IllegalArgumentException will be thrown.
	 * @param <T> The generic type of the column.
	 * @param fullyQualifiedName The fully-qualified name identifying the attribute.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 * @see CyTable#createColumn(String, String, Class, boolean)
	 */
	<T> void createColumn(String fullyQualifiedName, Class<?extends T> type, boolean isImmutable);
	
	/**
	 * Create a column of the specified name and the specified type in the specified namespace. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null.
	 * If the column name has the suffix ".SUID" and the column type is Long, Cytoscape will handle it as a column
	 * of SUIDs, and automatically update its values when the session file is loaded. However only SUIDs of CyNodes,
	 * CyEdges and CyNetworks can be updated.
	 * If the column already exists, IllegalArgumentException will be thrown.
	 * @param <T> The generic type of the column.
	 * @param columnName The name identifying the attribute.
	 * @param namespace The namespace for the column, or null to indicate no namespace.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 */
	default <T> void createColumn(String namespace, String columnName, Class<?extends T> type, boolean isImmutable) {
		createColumn(joinColumnName(namespace, columnName), type, isImmutable);
	}

	/**
	 * Create a column of the specified name and the specified type. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the column.
	 * @param fullyQualifiedName The fully-qualified name identifying the attribute.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue The default value for the column. Must be of the specified type or null.
	 * @see CyTable#createColumn(String, String, Class, boolean, Object)
	 */
	<T> void createColumn(String fullyQualifiedName, Class<?extends T> type, boolean isImmutable, T defaultValue);
	
	/**
	 * Create a column of the specified name and the specified type in the specified namespace. The column
	 * type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the column.
	 * @param columnName The name identifying the attribute.
	 * @param namespace The namespace for the column, or null to indicate no namespace.
	 * @param type The type of the column.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue The default value for the column. Must be of the specified type or null.
	 */
	default <T> void createColumn(String namespace, String columnName, Class<?extends T> type, boolean isImmutable, T defaultValue) {
		createColumn(joinColumnName(namespace, columnName), type, isImmutable, defaultValue);
	}

	/**
	 * Create a column of Lists with the specified name and the specified element type.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param fullyQualifiedName The fully-qualified name identifying the attribute.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 * @see CyTable#createListColumn(String, String, Class, boolean)
	 */
	<T> void createListColumn(String fullyQualifiedName, Class<T> listElementType, boolean isImmutable);

	/**
	 * Create a column of Lists with the specified name and the specified element type in the specified namespace.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. The
	 * default value for the column will be null. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param columnName The name identifying the attribute.
	 * @param namespace The namespace for the column, or null to indicate no namespace.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 */
	default <T> void createListColumn(String namespace, String columnName, Class<T> listElementType, boolean isImmutable) {
		createListColumn(joinColumnName(namespace, columnName), listElementType, isImmutable);
	}
	
	/**
	 * Create a column of Lists with the specified name and the specified element type.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param fullyQualifiedName The fully-qualified name identifying the attribute.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue A default list for the column. Must be a List of the specified element type or null.
	 * @see CyTable#createListColumn(String, String, Class, boolean, List)
	 */
	<T> void createListColumn(String fullyQualifiedName, Class<T> listElementType, boolean isImmutable, List<T> defaultValue);
	
	/**
	 * Create a column of Lists with the specified name and the specified element type in the specified namespace.
	 * The column type is limited to Integer, Long, Double, String, and Boolean. If the column already exists, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param <T> The generic type of the elements of the list.
	 * @param columnName The name identifying the attribute.
	 * @param namespace The namespace for the column, or null to indicate no namespace.
	 * @param listElementType The type of the elements of the list.
	 * @param isImmutable  if true, this column can never be deleted
	 * @param defaultValue A default list for the column. Must be a List of the specified element type or null.
	 */
	default <T> void createListColumn(String namespace, String columnName, Class<T> listElementType, boolean isImmutable, List<T> defaultValue) {
		createListColumn(joinColumnName(namespace, columnName), listElementType, isImmutable);
	}

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
	 * Deletes the rows corresponding to the given primary keys and returns true if
	 * at least one row was deleted.
	 * @param primaryKeys The primary keys of the rows to delete.
	 * @return true if at least one row was deleted.
	 */
	boolean deleteRows(Collection<?> primaryKeys);
	
	/**
	 * Return a list of all the rows stored in this data table. The order of the rows
	 * in the list corresponds to the order in which the rows have been inserted in the table.
     * 
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
	 * @param fullyQualifiedName  the fully-qualified column name for which we want the rows
	 * @param value       the value for which we want the rows that contain it
	 * @return the rows, if any that contain the value "value" for the column "columnName"
	 * @see CyTable#getMatchingRows(String, String, Object)
	 */
	Collection<CyRow> getMatchingRows(String fullyQualifiedName, Object value);

	/**
	 * Returns all the rows of a specified column that contain a certain value for that column.
	 * Default columns created by Cytoscape are in the {@link CyTable#USER_NAMESPACE} namespace.
	 * @param columnName  the column for which we want the rows
	 * @param namespace the namespace that contains the column.
	 * @param value       the value for which we want the rows that contain it
	 * @return the rows, if any that contain the value "value" for the column "columnName"
	 */
	default Collection<CyRow> getMatchingRows(String namespace, String columnName, Object value) {
		return getMatchingRows(joinColumnName(namespace, columnName), value);
	}
	
	/**
	 * Returns all the keys of a specified column that match the given value.
	 * @param fullyQualifiedName the fully-qualified column name for which we want the row keys
	 * @param value       the value for which we want the rows that contain it
	 * @param type  the type of the key column
	 * @return the keys, if any that contain the value "value" for the column "columnName"
	 * @throws ClassCastException if the keys are not of the given type
	 * @see CyTable#getMatchingKeys(String, String, Object, Class)
	 */
	<T> Collection<T> getMatchingKeys(String fullyQualifiedName, Object value, Class<T> type);
	
	/**
	 * Returns all the keys of a specified column that match the given value.
	 * Default columns created by Cytoscape are in the {@link CyTable#USER_NAMESPACE} namespace.
	 * @param columnName the column for which we want the row keys
	 * @param namespace the namespace that contains the column
	 * @param value       the value for which we want the rows that contain it
	 * @param type  the type of the key column
	 * @return the keys, if any that contain the value "value" for the column "columnName"
	 * @throws ClassCastException if the keys are not of the given type
	 */
	default <T> Collection<T> getMatchingKeys(String namespace, String columnName, Object value, Class<T> type) {
		return getMatchingKeys(joinColumnName(namespace, columnName), value, type);
	}
	
	/** 
	 * Returns the number of rows with the in the specified column with the specified value in the user namespace. 
	 * @param fullyQualifiedName  the fully-qualified column name to check 
	 * @param value       the value we want to check for 
	 * @return the number of rows with the in the specified column with the specified value. 
	 * @see CyTable#countMatchingRows(String, String, Object)
	 */
	int countMatchingRows(String fullyQualifiedName, Object value);
	
	/** 
	 * Returns the number of rows with the in the specified column with the specified value. 
	 * Default columns created by Cytoscape are in the {@link CyTable#USER_NAMESPACE} namespace.
	 * @param columnName  the column to check 
	 * @param namespace the namespace that contains the column
	 * @param value       the value we want to check for 
	 * @return the number of rows with the in the specified column with the specified value. 
	 */
	default int countMatchingRows(String namespace, String columnName, Object value) {
		return countMatchingRows(joinColumnName(namespace, columnName), value);
	}
	

	/** Returns the number of rows in this table.
	 *  @return The number if rows in the table.
	 */
	int getRowCount();

	/** 
	 * Adds a "virtual" column to the the current table. 
	 * A virtual column is a column in one table that points to a column in a different table. 
	 * Instead of duplicating the column data found in the other table, a virtual column allows 
	 * us to share that data by reference. A virtual column requires that columns be matched
	 * according to the primary key of the other table with a column in this table.
	 * @param virtualColumn  The name of the new virtual column, if this name already exists,
	 *                       new column names with -1, -2 and so appended to this name on will
	 *                       be tried until a non-existing name will be found.
	 * @param sourceColumn   The name of the column in "sourceTable" that will be mapped to "virtualColumn".
	 * @param sourceTable    The table that really contains the column that we're adding (all
	 *                       updates and lookups of this new column will be redirected to here).
	 *                       The table will be joined on the primary key column of this table.
	 * @param targetJoinKey  The column in current table that will be used for the join. This
	 *                       column will be joined with the primary key column of the source
	 *                       table. These columns must be of the same type!
	 * @param isImmutable    If true, this column cannot be deleted.
	 * @return The actual name of the new virtual column.
	 */
	String addVirtualColumn(String virtualColumn, String sourceColumn, CyTable sourceTable, String targetJoinKey, boolean isImmutable);
	
	
	/** Adds all columns in another table as "virtual" columns to the the current table.
	 * A virtual column is a column in one table that points to a column in a different table. 
	 * Instead of duplicating the column data found in the other table, a virtual column allows 
	 * us to share that data by reference. A virtual column requires that columns be matched
	 * according to the primary key of the other table with a column in this table.
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

	/** 
	 * Swaps the contents and properties (such as mutability) of "otherTable" with this table.
	 * This method is used to copy tables for backup, undo, and deletion and generally shouldn't
	 * be needed for most normal work.
	 * @param otherTable  the table that we're being swapped with.
	 * Note: the one "property" that is not being swapped is the SUID.  Also, no events are being
	 *       fired to give any listeners a chance to react to the exchange!
	 */
	void swap(CyTable otherTable);
	
}
