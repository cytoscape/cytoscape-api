package org.cytoscape.group.data;

/*
 * #%L
 * Cytoscape Groups API (group-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;

/**
 * The Aggregator interface acts as a service interface for
 * different methods of aggregating attribute data for the
 * nodes in a {@link CyGroup}. Aggregators are used to combine
 * the attribute values of the nodes contained within a Group
 * into a representative attribute value for the Group itself.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule group-api
 */
public interface Aggregator<T> {
	/**
 	 * Return the Class this aggregator supports
 	 *
 	 * @return the {@link Class} supported
 	 */
	public Class getSupportedType();

	/**
	 * Return the base Class of the List, if this
	 * is a list type.
 	 *
 	 * @return the {@link Class} supported by this List
 	 */
	default Class getSupportedListType() {
		return String.class;
	}

	/**
 	 * This gets the name of the handling type supported
 	 * by this aggregator, suitable for use in a menu.  
 	 *
 	 * @return a name for the handling type
 	 */
	public String toString();

	/**
	 * Aggregate the column for the members of the specified group into
	 * the group.
	 *
	 * @param table the CyTable for this group
	 * @param group the group
	 * @param column the column we're aggregating
	 * @return the aggregated value
	 */
  public <T> T aggregate(CyTable table, CyGroup group, CyColumn column);
    
} 

