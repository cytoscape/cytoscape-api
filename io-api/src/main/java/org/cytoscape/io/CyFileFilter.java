package org.cytoscape.io;

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

import java.io.InputStream;
import java.net.URI;
import java.util.Set;

/**
 * An interface that captures the metadata description of a file type
 * so that files of the specified type can be handled correctly by the
 * application and the user interface.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
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
	 * The MIME content types supported by this filter.
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
