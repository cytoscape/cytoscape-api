package org.cytoscape.application;

import java.io.File;

/**
 * Application-wide setting will be accessible through this interface.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule application-api
 */
public interface CyApplicationConfiguration {
	
	/**
	 * Returns absolute path to .cytoscape location.
	 * By default, this is user's home directory.
	 * 
	 * @return location of .cytoscape setting directory.
	 * 
	 * TODO: How can we set this location?
	 */
	File getConfigurationDirectoryLocation();

	/**
	 * Returns the absolute path to the configuration directory of a
	 * particular app.  This path might not physically exist and may
	 * need to be created by the caller.
	 * @param appClass Any class defined by the app's bundle
	 * @return the absolute path to the configuration directory of a
	 *         particular app.
	 */
	File getAppConfigurationDirectoryLocation(Class<?> appClass);
}
