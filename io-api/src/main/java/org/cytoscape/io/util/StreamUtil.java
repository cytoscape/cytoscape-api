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
