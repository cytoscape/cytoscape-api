package org.cytoscape.property;


/**
 * A general property service interface for providing access to different types
 * of property objects as OSGi services. The type P is generally one  of three types: 
 * {@link java.util.Properties}, 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, or
 * {@link org.cytoscape.property.session.Cysession}, although it is possible for 
 * property objects of other types to be registered in this way as well.
 * @param <P> The generic type for the CyProperty. Generally one  of three types: 
 * {@link java.util.Properties}, 
 * {@link org.cytoscape.property.bookmark.Bookmarks}, or
 * {@link org.cytoscape.property.session.Cysession}, although it is possible for 
 * property objects of other types to be registered in this way as well.
 * @CyAPI.Spi.Interface
 */
public interface CyProperty<P> {

	/** 
	 * SavePolicy specifies how the CyProperty will be saved, or if it will not be saved. 
	 * @CyAPI.Enum.Class
	 */
	enum SavePolicy {
		/** 
		 * These properties are preset by Cytoscape to some default value and
		 * are never written out. 
		 */
		DO_NOT_SAVE,

		/** 
		 * These properties are loaded from and written to the default config
		 * directory and are user defaults. 
		 */
		CONFIG_DIR,

		/** 
		 * This properties will be loaded from and saved to session files. 
		 */
		SESSION_FILE,

		/** 
		 * These properties will be loaded from and saved to session files and the default
		 * config directory.  (Settings in a session file will overwrite those from the
		 * default config directory.) 
		 */
		SESSION_FILE_AND_CONFIG_DIR
	}

	/**
	 * The name of the default directory where we will look for properties files.  This
	 * will be a subdirectory of the "user.home" directory defined in the default Java
	 * system properties ({@link System#getProperties()}).
	 */
	String DEFAULT_PROPS_CONFIG_DIR = ".cytoscape";
	
	/**
	 * Returns the name of the CyProperty.
	 * @return The name of the CyProperty.
	 */
	String getName();
	
	/**
	 * Return a property object.
	 * @return A property object of type P.
	 */
	P getProperties();

	/**
	 * Returns the {@link SavePolicy} of the CyProperty.
	 * @return the {@link SavePolicy} of the CyProperty.
	 */
	SavePolicy getSavePolicy();
}
