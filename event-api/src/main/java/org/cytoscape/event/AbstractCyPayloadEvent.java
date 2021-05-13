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
import java.util.Collections;

/**
 * A base implementation of {@link CyPayloadEvent} that can be used by events.
 * @param <T> the generic type of the source object.
 * @param <P> the generic type of the payloads.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule event-api
 */
public abstract class AbstractCyPayloadEvent<T,P> extends AbstractCyEvent<T> implements CyPayloadEvent<T,P> {

	private final Collection<P> payload;

	/**
	 * Constructor.
	 * @param source The event source object.
	 * @param listenerClass The listener class for this event.
	 * @param payload A collection of payload objects. May be empty, but not null!
	 */
	public AbstractCyPayloadEvent(final T source, Class<?> listenerClass, Collection<P> payload) {
		super(source, listenerClass);

		if ( payload == null )
			throw new NullPointerException("Payload is null");

		this.payload = Collections.unmodifiableCollection(payload);
	}

	/**
	 * Returns an unmodifiable collection of payload objects.
	 * @return an unmodifiable collection of payload objects.
	 */
	@Override
	public Collection<P> getPayloadCollection() {
		return payload;
	}
}
