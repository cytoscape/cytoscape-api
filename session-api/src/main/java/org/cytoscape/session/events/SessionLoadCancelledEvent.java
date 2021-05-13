package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;

/*
 * #%L
 * Cytoscape Session API (session-api)
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

/**
 * This event used to be fired (until version 3.5.1) when a session load task was cancelled,
 * which can also happen because of exceptions, but is no longer fired.
 * @deprecated Just listen to {@link SessionLoadedEvent} instead now.
 * @CyAPI.Final.Class
 * @CyAPI.InModule session-api
 */
@Deprecated
public final class SessionLoadCancelledEvent extends AbstractCyEvent<Object> {

	private final Exception exception;
	
	/**
	 * @param source The object that fired this event.
	 */
	public SessionLoadCancelledEvent(final Object source) {
		this(source, null);
	}
	
	/**
	 * Use this constructor if the session load was cancelled because of an exception.
	 * @param source The object that fired this event.
	 * @param exception The {@link Exception} that interrupted the session load task.
	 */
	public SessionLoadCancelledEvent(final Object source, final Exception exception) {
		super(source, SessionLoadCancelledListener.class);
		this.exception = exception;
	}

	/**
	 * @return The exception that cancelled the session load task or null,
	 *         if it was not interrupted by an exception.
	 */
	public Exception getException() {
		return exception;
	}
}
