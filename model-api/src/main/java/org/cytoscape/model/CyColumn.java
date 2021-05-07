package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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


/** 
 * This class describes a column in a CyTable. 
 * <br><br>
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
public interface CyColumn extends CyIdentifiable {
	
	public static final String NAMESPACE_SEPARATOR = "::";
	
	/**
	 * Splits a column name into a namespace part and a name part at the first occurrence of a "::".
	 * 
	 * If the column name does not contain a "::" then the namespace part will be null.
	 * 
	 * Whitespace is significant. The string "::hello" has "" as the namespace.
	 */
	public static String[] splitColumnName(String fullyQualifiedName) {
		int index = fullyQualifiedName.indexOf(NAMESPACE_SEPARATOR);
		if(index == -1) {
			return new String[] { null, fullyQualifiedName };
		}
		String namespace = fullyQualifiedName.substring(0, index);
		String name = fullyQualifiedName.substring(index + NAMESPACE_SEPARATOR.length(), fullyQualifiedName.length());
		return new String[] { namespace, name };
	}
	
	
	/**
	 * Joins a namespace and a name string into a fully-qualified column name.
	 * For example: <code>joinColumnName("MyNamespace", "MyName")</code> results in <code>"MyNamespace::MyName"</code>.
	 */
	public static String joinColumnName(String namespace, String name) {
		if(namespace == null)
			return name;
		return namespace + NAMESPACE_SEPARATOR + name;
	}
	
	/** 
	 * Returns the fully-qualified name of the column.
	 * @return the fully-qualified name of the column. 
	 */
	String getName();
	
	/**
	 * Returns the namespace of the column, or null if the column does not have a namespace.
	 * Default columns created by Cytoscape do not have a namespace.
	 */
	default String getNamespace() {
		return splitColumnName(getName())[0];
	}
	
	/**
	 * Returns the name portion without the namespace.
	 */
	default String getNameOnly() {
		return splitColumnName(getName())[1];
	}
	
	/** 
	 * Change the name of this column. If another column with a matching name already exists 
	 * in the same namespace, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param newName  the new fully qualified column name
	 * @throws IllegalArgumentException if the column is immutable
	 */
	void setName(String fullyQualifiedName);
	
	/** 
	 * Change the name of this column. If another column with a matching name already exists 
	 * in the same namespace, IllegalArgumentException will be thrown.
	 * The check for matching column names is case insensitive. 
	 * @param namespace  the new namespace, use null to indicate no namespace
	 * @param name the new name
	 * @throws IllegalArgumentException if the column is immutable
	 */
	default void setName(String namespace, String name) {
		setName(joinColumnName(namespace, name));
	};

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
