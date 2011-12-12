package org.cytoscape.application;

import java.io.File;

/**
 * Application-wide setting will be accessible through this interface.
 * @CyAPI.Api.Interface
 */
public interface CyApplicationConfiguration {
	
	/** Default configuration directory used for all Cytoscape configuration files */
	public static final String DEFAULT_CONFIG_DIR = ".cytoscape";

	/**
	 * Returns absolute path to .cytoscape location.
	 * By default, this is user's home directory.
	 * 
	 * @return location of .cytoscape setting directory.
	 * 
	 * TODO: How can we set this location?
	 */
	File getConfigurationDirectoryLocation();

}
