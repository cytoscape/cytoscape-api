package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * This event is fired when a session save task is cancelled, which can also happen because of exceptions.
 * @CyAPI.Final.Class
 * @CyAPI.InModule session-api
 */
public final class SessionSaveCancelledEvent extends AbstractCyEvent<Object> {

	private final Exception exception;
	
	/**
	 * Constructor.
	 * @param source The object that fired this event.
	 */
	public SessionSaveCancelledEvent(final Object source) {
		this(source, null);
	}
	
	/**
	 * Use this constructor if the session save was cancelled because of an exception.
	 * @param source The object that fired this event.
	 * @param exception The {@link Exception} that interrupted the session save task.
	 */
	public SessionSaveCancelledEvent(final Object source, final Exception exception) {
		super(source, SessionSaveCancelledListener.class);
		this.exception = exception;
	}

	/**
	 * @return The exception that cancelled the session save task or null,
	 *         if it was not interrupted by an exception.
	 */
	public Exception getException() {
		return exception;
	}
}
