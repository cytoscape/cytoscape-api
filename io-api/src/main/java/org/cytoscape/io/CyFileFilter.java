package org.cytoscape.io;

import java.io.InputStream;
import java.net.URI;
import java.util.Set;

/**
 * An interface that captures the metadata description of a file type
 * so that files of the specified type can be handled correctly by the
 * application and the user interface.
 */
public interface CyFileFilter {

	/**
	 * A method that attempts to determine whether the specified URI can be read
	 * by the Reader using this filter.
	 * @param uri The URI to be checked.
	 * @param category The data category of URI.
	 * @return True if we believe the URI can be read, false otherwise.
	 */
	boolean accepts(URI uri, DataCategory category); 

	/**
	 * A method that attempts to determine whether the specified InputStream can be read
	 * by the Reader using this filter.
	 * @param stream The input steam to be checked.
	 * @param category The data category of the input steam.
	 * @return True if we believe the stream can be read, false otherwise.
	 */
	boolean accepts(InputStream stream, DataCategory category);

	/**
	 * Returns a list of file extensions (xml, xgmml, sif) suitable for for use
	 * in FileChoosers. 
	 * @return A list of file extensions (xml, xgmml, sif) suitable for for use
	 * in FileChoosers. 
	 */
	Set<String> getExtensions();

	/**
	 * The MIME content types suppored by this filter.
	 * @return A set of strings the describe the different MIME types supported by this filter.
	 */
	Set<String> getContentTypes();

	/**
	 * A short, human readable description of the file extensions suitable for
	 * display in FileChoosers. 
	 * @return A string describing this file type.
	 */
	String getDescription();

	/**
	 * The DataCategory supported by this filter.
	 * @return The DataCategory supported by this filter.
	 */
	DataCategory getDataCategory();

}
