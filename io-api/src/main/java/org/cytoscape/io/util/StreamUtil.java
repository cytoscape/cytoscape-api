package org.cytoscape.io.util;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A stateless utility class that provides special handling to support
 * InputStreams and URLConnections over the network. If you intend to
 * support the use of proxy servers, you should use this service.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule io-api
 */
public interface StreamUtil {

	String URL_PATTERN = "^(jar\\:)?((http|https|ftp|file)+\\:\\/+\\S+)(\\!\\/\\S*)?$";

	/**
	 * Gets the input stream from given a string.  Attempts to
	 * determine if the string is a URL or a File and then creates
	 * the appropriate InputStream.
	 * Proxy will be used if available.
	 * 
	 * @param source The string from which to generate the InputStream.
	 * @return An input stream from the specified string.
	 * @throws IOException 
	 */
	InputStream getInputStream(final String source) throws IOException;

	/**
	 * Gets the input stream from given {@link java.net.URL}.
	 * Proxy will be used if available.
	 * 
	 * @param source The URL from which to generate the InputStream.
	 * @return An input stream from the specified URL.
	 * @throws IOException 
	 */
	InputStream getInputStream(final URL source) throws IOException;


	/**
	 * Obtain a {@link java.net.URLConnection} for a given {@link java.net.URL}.
	 * Proxy will be used if available.
	 * 
	 * @param source The URL from which to generate the URLConnection.
	 * @return An URLConnection from the specified URL.
	 * @throws IOException 
	 */
	URLConnection getURLConnection(final URL source) throws IOException;

}
