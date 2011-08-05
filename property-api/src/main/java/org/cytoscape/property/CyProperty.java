package org.cytoscape.property;


/**
 * A general property service interface for providing access to different types
 * of property objects as OSGi services. The type P is generally one  of three types: 
 * {@link java.util.Properties}, 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, or
 * {@link org.cytoscape.property.session.Cysession}, although it is possible for 
 * property objects of other types to be registered in this way as well.
 */
public interface CyProperty<P> {
	public static final String DEFAULT_CONFIG_DIR = ".cytoscape";

	enum SavePolicy {
		/** These properties are preset by Cytoscape to some default value and
		 *  are never written out. */
		DO_NOT_SAVE,

		/** These properties are loaded from and written to the default config
		 *  directory and are user defaults. */
		CONFIG_DIR,

		/** This properties will be loaded from and saved to session files. */
		SESSION_FILE,

		/** This properties will be loaded from and saved to session files and the default
		 *  config directory.  (Settings in a session file will overwrite those from the
		 *  default config directory.) */
                SESSION_FILE_AND_CONFIG_DIR
	}

	/**
	 * Return a property object
	 * @return A property object of type P.
	 */
	P getProperties();

	SavePolicy getSavePolicy();
}
