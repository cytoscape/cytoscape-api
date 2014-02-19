package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * This event is fired when a session load task is cancelled, which can also happen because of exceptions.
 * @CyAPI.Final.Class
 * @CyAPI.InModule session-api
 */
public final class SessionLoadCancelledEvent extends AbstractCyEvent<Object> {

	private final Exception exception;
	
	/**
	 * Constructor.
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
