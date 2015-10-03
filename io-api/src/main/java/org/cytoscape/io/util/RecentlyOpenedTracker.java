package org.cytoscape.io.util;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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
	 * Clears the list of recently opened file names.
	 */
	void clear();


	/**
	 * Writes the list of recently opened files to the file specified by the
	 * constructor argument.
	 * @throws FileNotFoundException 
	 */
	void writeOut() throws FileNotFoundException;

}