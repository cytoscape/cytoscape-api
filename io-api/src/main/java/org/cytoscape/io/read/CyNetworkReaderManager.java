package org.cytoscape.io.read;

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


import java.io.InputStream;
import java.net.URI;


/**
 * An object that registers all InputStreamReaderFactory singletons,
 * processes specified input to determine the appropriate factory to
 * use and then returns an instance of the correct {@link org.cytoscape.io.read.CyNetworkReader} 
 * for the input.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule io-api
 */
public interface CyNetworkReaderManager {
	/**
	 * Given a URI this method will attempt to find a InputStreamReaderFactory
	 * that can read the URI, will set the InputStream for the factory and
	 * will return the reader task.
	 * @param uri The URI we're attempting to read. 
	 * @param inputName The name of the input. 
	 * @return A reader than can read the specified URI. Will return null if
	 * no reader can be found.
	 */
	CyNetworkReader getReader(URI uri, String inputName); 

	/**
	 * Given an InputStream this method will attempt to find a InputStreamReaderFactory
	 * that can read the stream, will set the InputStream for the factory and
	 * will return the reader task.
	 * @param stream The input stream we're attempting to read. 
	 * @param inputName The name of the input. 
	 * @return A reader that can read the specified stream. Will return null if
	 * no reader can be found.
	 */
	CyNetworkReader getReader(InputStream stream, String inputName); 
}
