package org.cytoscape.work.properties;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2015 The Cytoscape Consortium
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

import org.cytoscape.work.TunableHandler;


/**
 * An extension of <code>TunableHandler</code> with added functionality to support
 * converting to/from a String.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TunablePropertyHandler extends TunableHandler {

	/**
	 * Converts the value of the tunable to a String.
	 * @return String representation of the tunable value.
	 * @throws IllegalArgumentException If there is a problem accessing the tunable value.
	 */
	String toPropertyValue();
	
	/**
	 * Converts the String representation to an object or primitive value and sets the tunable.
	 * @throws IllegalArgumentException If there is a problem setting the tunable value.
	 */
	void parseAndSetValue(String propertyValue);
	
	
	/**
	 * Typically don't need to implement this method, default implementation is empty.
	 */
	default void handle() {}
}
