package org.cytoscape.session;


/**
 * This class primarily acts as a listener and tracks the state of 
 * the Cytoscape application. This state can be interrogated at any
 * time and the result is an immutable CySession object suitable
 * for serialization. Likewise, setting a new session will replace
 * the current session with a new one.
 */
public interface CySessionManager {

	/**
	 * This method returns a CySession object describing the current
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
}

