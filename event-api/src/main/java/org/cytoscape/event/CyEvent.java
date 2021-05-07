package org.cytoscape.event;

/*
 * #%L
 * Cytoscape Event API (event-api)
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
 * All Cytoscape events should extend this interface.  An implementing
 * event can add additional methods to provide access to information
 * relevant to that specific event.
 * @param <T> the generic type of the CyEvent.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule event-api
 */
public interface CyEvent<T> {
	
	/**
	 * The object that fired the event.
	 * 
	 * @return The object that fired the event.
	 */
	T getSource();

	
	/**
	 * The Class of the listener that is expected to handle this event.
	 * 
	 * @return The Class of the listener that is expected to handle this event. 
	 */
	Class<?> getListenerClass();

}
