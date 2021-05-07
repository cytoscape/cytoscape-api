package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.util.List;


/**
 * Definition of Edge Bends.
 * Bend is an ordered {@link List} of {@link Handle}s.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface Bend {
	
	/**
	 * Get List of all handles on this edge
	 * 
	 * @return All {@link Handle}s belong to this Bend.
	 */
	List<Handle> getAllHandles();
	
	/**
	 * Insert a Handle to the specified position in the Bend
	 * 
	 * @param index Position of the new Handle
	 * @param handle Handle to be added
	 */
	void insertHandleAt(final int index, final Handle handle);

	/**
	 * Remove a Handle at the given index.
	 * 
	 * @param handleIndex Index of the Handle to be removed
	 */
	void removeHandleAt(final int handleIndex);
	
	/**
	 * Remove all Handles on this Bend
	 */
	void removeAllHandles();

	
	/**
	 * Get index of a Handle
	 * 
	 * @param handle 
	 * 
	 * @return Index of the given Handle
	 */
	int getIndex(final Handle handle);
	
	/**
	 * Create string representation of this object for parsing.
	 * 
	 * @return serializable string.
	 */
	String getSerializableString();
}
