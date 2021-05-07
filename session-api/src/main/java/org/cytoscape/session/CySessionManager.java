package org.cytoscape.session;

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
 * This class primarily acts as a listener and tracks the state of 
 * the Cytoscape application. This state can be interrogated at any
 * time and the result is an immutable {@link CySession} object suitable
 * for serialization. Likewise, setting a new session will replace
 * the current session with a new one.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule session-api
 */
public interface CySessionManager {

	/**
	 * This method returns a {@link CySession} object describing the current
	 * state of Cytoscape. The object returned is meant to
	 * be used for serialization and is not meant to be used interactively
	 * to track the state of Cytsocape.
	 * @return An immutable CySession object.  
	 */
    CySession getCurrentSession();
   
    /**
	 * This method allows a new session to be set and in doing
	 * so <b>erases and overrides the current session!</b>
	 * @param session The new session to be applied to Cytoscape.
	 * @param fileName The name of the file representing the new session.
	 */
    void setCurrentSession(CySession session, String fileName);

	/**
	 * Returns the name of the current session file.
	 * @return The name of the current session file.
	 */
	String getCurrentSessionFileName();
	
	/**
	 * Disposes the current session.
	 * This method is meant to be called by Cytoscape right before loading a new session.
	 */
	void disposeCurrentSession();
}

