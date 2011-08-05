
package org.cytoscape.session.events;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.property.session.Cytopanel;
import org.cytoscape.property.session.Desktop;
import org.cytoscape.session.CySessionManager;

/**
 * This event is fired synchronously by the CySessionManager at beginning of the
 * {@link CySessionManager#getCySession()} method.  The intent is to allow
 * listeners to provide information to this event object or to update their 
 * state before that state is interrogated by the CySessionManager. 
 */
public final class SessionAboutToBeSavedEvent extends AbstractCyEvent<CySessionManager> {
	
	private final Map<String,List<File>> pluginFileListMap;
	private final List<Cytopanel> cytopanels;
	private Desktop desktop;
	
	// TODO should the source be the session manager??
	/**
	 * Constructor.
	 * @param source The CySessionManager that will be saving the session.
	 */
	public SessionAboutToBeSavedEvent(final CySessionManager source) {
		super(source, SessionAboutToBeSavedListener.class);

		pluginFileListMap = new HashMap<String,List<File>>();
		cytopanels = new ArrayList<Cytopanel>();
	}

	/**
	 * A method that allows plugins to specify a list of files that should be
	 * stored in the session.
	 * @param pluginName The name of the plugin that these files should be stored for.
	 * @param files The list of File objects to be stored in the session file.
	 */
	public void addPluginFiles(final String pluginName, final List<File> files) throws Exception {
		// Throw checked Exceptions here to force plugin authors to deal with
		// problems they might create.
		if ( pluginName == null )
			throw new NullPointerException("plugin name is null");
			
		if ( pluginName == "" )
			throw new IllegalArgumentException("plugin name is empty");

		if ( pluginFileListMap.containsKey( pluginName ) )
			throw new IllegalArgumentException("The plugin file list already contains a list of files identified by the name: " + pluginName);

		if ( files == null )
			throw new NullPointerException("file list is null");

		// allow empty lists
		pluginFileListMap.put(pluginName, new ArrayList<File>(files));
	}

	/**
	 * This method is not meant to be used by listeners for this event, 
	 * although you can and no harm should come to you.  This method is
	 * used by the CySessionManager to retrieve the list of files from
	 * plugins.
	 * @return A map of plugin names to lists of files to be stored in the
	 * session for that plugin.
	 */
	public Map<String,List<File>> getPluginFileListMap() {
		// Make the return value immutable so that listeners
		// can't mess with us.
		return Collections.unmodifiableMap( pluginFileListMap );
	}

	/**
	 * This method is not meant to be used by listeners for this event.
	 * @return
	 */
	public Desktop getDesktop() {
		return desktop;
	}

	/**
	 * A method that allows plugins to specify desktop-related data to be
	 * stored in the session.
	 * @param desktop
	 */
	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}

	/**
	 * This method is not meant to be used by listeners for this event.
	 * @return
	 */
	public List<Cytopanel> getCytopanels() {
		// Make the return value immutable so that listeners
		// can't mess with us.
		return Collections.unmodifiableList( cytopanels );
	}

	/**
	 * A method that allows plugins to add Cytopanel-related information to be
	 * stored in the session.
	 * @param cytopanel
	 */
	public void addCytopanel(Cytopanel cytopanel) throws Exception {
		if (cytopanel == null)
			throw new NullPointerException("cytopanel is null");
		
		cytopanels.add(cytopanel);
	}
	
}
