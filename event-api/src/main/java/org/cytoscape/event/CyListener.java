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
 * The basic interface that any class interested in a particular
 * type of event should implement.
 * <p>Any class implementing this interface must implement the method:</p>
 * <p> <code>public void handleEvent(ZZZ e);</code> </p>
 * <p>where ZZZ extends CyEvent. </p> 
 * <p>
 * Unfortunately, we can't parameterize this
 * because Java doesn't verify generic types, meaning a class 
 * could only implement ONE instance of this interface, 
 * something that doesn't work for us.  And so we leave it
 * to convention.
 * </p>
 * <p>
 * Instead of the customary strategy of registering events with 
 * the objects that fire the events, listeners should register
 * themselves as services with the OSGi ServiceRegistry.  
 * The event producers will simply query the ServiceRegistry 
 * to search for Listeners for the type of events they fire.  
 * </p>
 * <p>
 * It would be fantastic if we could specify one listener
 * interface that differentiated itself by a parameterized
 * type, but that doesn't appear possible with Java. That
 * means users are either must register as different listeners
 * or handle different event types in the handleEvent method.
 * </p>
 * 
 * @CyAPI.Spi.Interface 
 * @CyAPI.InModule event-api
 */
public interface CyListener {
	// implement 
	// public void handleEvent(CyEvent e);
}
