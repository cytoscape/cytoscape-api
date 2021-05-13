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

import java.util.Collection;

/**
 * An extension of CyEvent specifically for payload events.
 * A payload event is a single event that contains a
 * collection of payload objects, which represent the state
 * change of the event. Payload events are used in cases
 * where many small changes can be combined into a single
 * event object (such as node creation or table row 
 * modification).  The goal is to prevent too many events
 * from being fired.
 * @param <S> The event source type.
 * @param <P> The payload type.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule event-api
 */
public interface CyPayloadEvent<S,P> extends CyEvent<S> {
	
	/**
	 * Returns a collection of payload objects.
	 * @return a collection of payload objects.
	 */
	Collection<P> getPayloadCollection();
}
