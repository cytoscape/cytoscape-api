
package org.cytoscape.session.events;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.session.CySessionManager;

/**
 * This event is fired synchronously by the {@link CySessionManager} at beginning of the
 * {@link CySessionManager#getCurrentSession()} method.  The intent is to allow
 * listeners to provide information to this event object or to update their 
 * state before that state is interrogated by the CySessionManager. 
 * @CyAPI.Final.Class
 */
public final class SessionAboutToBeSavedEvent extends AbstractCyEvent<CySessionManager> {
	
	private final Map<String,List<File>> appFileListMap;
	
	/**
	 * Constructor.
	 * @param source The {@link CySessionManager} that will be saving the session.
	 */
	public SessionAboutToBeSavedEvent(final CySessionManager source) {
		super(source, SessionAboutToBeSavedListener.class);

		appFileListMap = new HashMap<String,List<File>>();
	}

	/**
	 * A method that allows apps to specify a list of files that should be
	 * stored in the session.
	 * @param appName The name of the app that these files should be stored for.
	 * @param files The list of File objects to be stored in the session file.
	 * @throws Exception 
	 */
	public void addAppFiles(final String appName, final List<File> files) throws Exception {
		// Throw checked Exceptions here to force app authors to deal with
		// problems they might create.
		if ( appName == null )
			throw new NullPointerException("app name is null");
			
		if ( appName == "" )
			throw new IllegalArgumentException("app name is empty");

		if ( appFileListMap.containsKey( appName ) )
			throw new IllegalArgumentException("The app file list already contains a list of files identified by the name: " + appName);

		if ( files == null )
			throw new NullPointerException("file list is null");

		// allow empty lists
		appFileListMap.put(appName, new ArrayList<File>(files));
	}

	/**
	 * This method is not meant to be used by listeners for this event, 
	 * although you can and no harm should come to you.  This method is
	 * used by the {@link CySessionManager} to retrieve the list of files from
	 * apps.
	 * @return A map of app names to lists of files to be stored in the
	 * session for that app.
	 */
	public Map<String,List<File>> getAppFileListMap() {
		// Make the return value immutable so that listeners
		// can't mess with us.
		return Collections.unmodifiableMap( appFileListMap );
	}
}
