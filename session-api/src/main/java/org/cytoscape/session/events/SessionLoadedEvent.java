package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.session.CySession;
import org.cytoscape.session.CySessionManager;

/*
 * #%L
 * Cytoscape Session API (session-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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
 * This event is fired after a new session has been set in the 
 * {@link CySessionManager#setCurrentSession(CySession, String)} 
 * method and is used to notify interested parties in the change of state. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule session-api
 */
public final class SessionLoadedEvent extends AbstractCyEvent<CySessionManager> {

	private final CySession session;
	private final String fileName;

	/**
	 * @param source The {@link CySessionManager} that is the source of this event.
	 * @param session The {@link CySession} object that was just loaded.
	 * @param fileName the name of the file representing the session.
	 */
	public SessionLoadedEvent(CySessionManager source, CySession session, String fileName) {
		super(source, SessionLoadedListener.class);
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
