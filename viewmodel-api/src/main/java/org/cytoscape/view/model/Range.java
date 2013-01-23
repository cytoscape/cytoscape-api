package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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
 * Defines a range of values for {@link VisualProperty}s.
 *
 * @param <T> The generic type of this Range.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface Range<T> {
	

	/**
	 * Type of object used in this range.
	 * 
	 * @return object type.
	 */
	Class<T> getType();
	
	
	/**
	 * If this range is a set of discrete values, return true.
	 * 
	 * @return If discrete, return true.
	 * 
	 */
	boolean isDiscrete();
	
	
	/**
	 * Return true if the given value is in the range defined in this class.
	 * 
	 * @param value any value to be tested.
	 * 
	 * @return true if the given value is in the range.
	 */
	boolean inRange(final T value);
}
