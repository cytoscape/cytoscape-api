package org.cytoscape.event;

/*
 * #%L
 * Cytoscape Event API (event-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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
 * A base implementation of {@link CyEvent} that can be used by events.
 * @param <T> the generic type of the AbstractCyEvent.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule event-api
 */
public abstract class AbstractCyEvent<T> implements CyEvent<T> {

	private final T source;
	private final Class<?> listenerClass;

	/** 
	 * Constructor.
	 * @param source The source object that fires the event. May NOT be null.
	 * @param listenerClass The Class that defines the listener interface. May NOT be null.
	 */
	public AbstractCyEvent(final T source, Class<?> listenerClass) {
		if ( source == null )
			throw new NullPointerException("event source is null");

		if ( listenerClass == null )
			throw new NullPointerException("listener class is null");

		this.source = source;
		this.listenerClass = listenerClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getSource() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getListenerClass() {
		return listenerClass;
	}
}
