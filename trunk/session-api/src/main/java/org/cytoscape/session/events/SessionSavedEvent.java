package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.session.CySession;

public final class SessionSavedEvent extends AbstractCyEvent<Object> {
	
	private final CySession session;
	private final String fileName;

	public SessionSavedEvent(final Object source, final CySession session, final String fileName) {
		super(source, SessionSavedListener.class);
		this.session = session;
		this.fileName = fileName;
	}
	
	/**
	 * Returns the session that was just loaded.
	 * @return The session that was just loaded.
	 */
	public CySession getSavedSession() {
		return session;
	}

	/**
	 * Returns the file name of the session just loaded.
	 * @return The file name of the session just loaded.
	 */
	public String getSavedFileName() {
		return fileName;
	}

}
