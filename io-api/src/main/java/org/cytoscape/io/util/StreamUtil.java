package org.cytoscape.io.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A stateless utility class that provides special handling to support
 * InputStreams and URLConnections over the network. If you intend to
 * support the use of proxy servers, you should use this service.
 * @CyAPI.Api.Interface
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
