package org.cytoscape.io.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A stateless utility class that provides special handling to support
 * InputStreams and URLConnections over the network. 
 * @CyAPI.Api.Interface
 */
public interface StreamUtil {

	// TODO what's the difference between these two methods?
	/**
	 * Gets the input stream from given {@link java.net.URL}.
	 * @param source The URL from which to generate the InputStream.
	 * @return An input stream from the specified URL.
	 * @throws IOException 
	 */
	InputStream getInputStream(URL source) throws IOException;

	/**
	 * Obtain an InputStream for a given URL. Ensure proxy servers and an input
	 * stream to the real URL source is created--not a locally cached and out of
	 * date source. Proxy servers and other characteristics can cause pages to
	 * be cached.
	 * @param source The URL from which to generate the InputStream.
	 * @return An input stream from the specified URL.
	 * @throws IOException 
	 */
	InputStream getBasicInputStream(URL source) throws IOException;

	/**
	 * Obtain a {@link java.net.URLConnection} for a given {@link java.net.URL}.
	 * @param source The URL from which to generate the URLConnection.
	 * @return An URLConnection from the specified URL.
	 * @throws IOException 
	 */
	URLConnection getURLConnection(URL source) throws IOException;

}
