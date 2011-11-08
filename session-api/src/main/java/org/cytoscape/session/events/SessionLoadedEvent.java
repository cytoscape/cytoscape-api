
package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.session.CySession;
import org.cytoscape.session.CySessionManager;

/**
 * This event is fired after a new session has been set in the 
 * {@link CySessionManager#setCurrentSession(CySession, String)} 
 * method and is used to notify interested parties in the change 
 * of state. 
 * @CyAPI.Final.Class
 */
public final class SessionLoadedEvent extends AbstractCyEvent<CySessionManager> {

	private final CySession session;
	private final String fileName;

	/**
	 * Constructor.
	 * @param source The {@link CySessionManager} that is the source of this event.
	 * @param session The {@link CySession} object that was just loaded.
	 * @param fileName the name of the file representing the session.
	 */
	public SessionLoadedEvent(final CySessionManager source, final CySession session, final String fileName) {
		super(source,SessionLoadedListener.class);
		this.session = session;
		this.fileName = fileName;
	}

	/**
	 * Returns the session that was just loaded.
	 * @return The session that was just loaded.
	 */
	public CySession getLoadedSession() {
		return session;
	}

	/**
	 * Returns the file name of the session just loaded.
	 * @return The file name of the session just loaded.
	 */
	public String getLoadedFileName() {
		return fileName;
	}
}
