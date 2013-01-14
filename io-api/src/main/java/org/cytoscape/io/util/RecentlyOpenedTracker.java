package org.cytoscape.io.util;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

/**
 * Manages list of recently opened files.
 * Currently, this is used for tracking recent sessions.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule io-api
 */
public interface RecentlyOpenedTracker {


	/**
	 * Returns the last addition or null if there are no URLs.
	 * @return the last addition or null if there are no URLs.
	 */
	URL getMostRecentlyOpenedURL();
	
	
	/**
	 * Returns the current list of recently opened file names.
	 * @return the current list of recently opened file names
	 */
	List<URL> getRecentlyOpenedURLs();


	/**
	 * Adds "newURL" to the list of recently opened file names and trims the
	 * list if it has exceeded its maximum length.
	 * @param newURL the URL to add to the list of recently opened file names.
	 */
	void add(final URL newURL);


	/**
	 * Writes the list of recently opened files to the file specified by the
	 * constructor argument.
	 * @throws FileNotFoundException 
	 */
	void writeOut() throws FileNotFoundException;

}