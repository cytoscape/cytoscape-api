package org.cytoscape.io;

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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.io.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is meant to be an basic implementation of {@link CyFileFilter} that can
 * either be used directly or extended to provide different acceptance criteria.
 * Only the accepts() methods may be overridden.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule io-api
 */
public class BasicCyFileFilter implements CyFileFilter {

	protected final Set<String> extensions;
	protected final Set<String> contentTypes;
	protected final String description;
	protected final StreamUtil streamUtil;
	/** Type of data that this filter applies to. */
	protected final DataCategory category;
	private static final Logger logger = LoggerFactory.getLogger(BasicCyFileFilter.class);

	/**
	 * Creates a file filter from the specified arguments. Note that a "."
	 * before the extension is not needed and will be ignored.
	 * 
	 * @param extensions
	 *            The set of valid extensions for this filter.
	 * @param contentTypes
	 *            The set of valid MIME content types that this filter should
	 *            recognize.
	 * @param description
	 *            A human readable description of the filter.
	 * @param category
	 *            The type of data this filter is meant to support.
	 * @param streamUtil
	 *            An instance of the StreamUtil service.
	 */
	public BasicCyFileFilter(final Set<String> extensions, final Set<String> contentTypes, final String description,
			final DataCategory category, StreamUtil streamUtil) {

		this.extensions = extensions;
		this.contentTypes = contentTypes;
		this.category = category;

		final StringBuilder builder = new StringBuilder();
		builder.append(description == null ? "(" : description + " (");

		for (String ex : extensions)
			builder.append("*." + ex + ", ");

		String d = builder.toString();
		d = d.substring(0, d.length() - 2);
		d += ")";

		this.description = d;
		this.streamUtil = streamUtil;
	}

	/**
	 * Creates a file filter from the specified arguments. Note that a "."
	 * before the extension is not needed and will be ignored.
	 * 
	 * @param extensions
	 *            The set of valid extensions for this filter.
	 * @param contentTypes
	 *            The set of valid MIME content types that this filter should
	 *            recognize.
	 * @param description
	 *            A human readable description of the filter.
	 * @param category
	 *            The type of data this filter is meant to support.
	 * @param streamUtil
	 *            An instance of the StreamUtil service.
	 */
	public BasicCyFileFilter(final String[] extensions, final String[] contentTypes, final String description,
			final DataCategory category, StreamUtil streamUtil) {
		this(createSet(extensions), createSet(contentTypes), description, category, streamUtil);
	}

	private static Set<String> createSet(String[] values) {
		Set<String> set = new HashSet<String>();
		for (String v : values)
			set.add(v);
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean accepts(URI uri, DataCategory category) {

		// Check data category
		if (category != this.category)
			return false;

		if (extensionsMatch(uri))
			return true;
		else
			return false;

	}

	private boolean extensionsMatch(URI uri) {
		final String extension = getExtension(uri.toString());
		if (extension != null && extensions.contains(extension))
			return true;
		else
			return false;
	}

	/**
	 * This method always returns false in this particular implementation. You
	 * must extend this class and override this method to get alternative
	 * behavior. Ideally this method would return true if this class is capable
	 * of processing the specified InputStream.
	 * 
	 * @param stream
	 *            The stream that references the file we'd like to read.
	 * @param category
	 *            The type of input that we're considering.
	 * @return Always returns false in this particular implementation.
	 */
	public boolean accepts(InputStream stream, DataCategory category) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<String> getExtensions() {
		return extensions;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<String> getContentTypes() {
		return contentTypes;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	public final DataCategory getDataCategory() {
		return category;
	}

	/**
	 * Returns a human readable description of this class.
	 * 
	 * @return a human readable description of this class.
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(description + " [category: " + category + "]  [extensions: ");
		
		for (String ext : extensions)
			builder.append(ext + ",");
		
		String s = builder.toString();
		s += "]   [contentTypes: ";
		for (String c : contentTypes)
			s += c + ",";
		s += "]";

		return s;
	}

	/**
	 * Returns a string of the characters following the last '.' in the input
	 * string, which is to say the file extension assuming that the input string
	 * represents a file name. Will return null if no '.' is found.
	 * 
	 * @param filename
	 *            the file name as a string.
	 * @return a string representing the file extension of the input string.
	 */
	protected final String getExtension(String filename) {
		if (filename != null) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1).toLowerCase();
			}
		}

		return null;
	}

	/**
	 * Returns a string containing the specified number of lines from the
	 * beginning of the file. This is useful for testing input streams.
	 * 
	 * @param stream
	 *            the input stream from which to read the header.
	 * @param numLines
	 *            the number of lines from the beginning of the file.
	 * @return a string containing the specified number of lines from the
	 *         beginning of the file.
	 */
	protected final String getHeader(InputStream stream, int numLines) {

		String header;
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		try {
			header = parseHeader(br, numLines);
		} catch (IOException ioe) {
			logger.warn("failed to read header from stream", ioe);
			header = "";
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}

			br = null;
		}

		return header;
	}

	private final String parseHeader(BufferedReader bufferedReader, int numLines) throws IOException {
		StringBuilder header = new StringBuilder();

		try {
			String line = bufferedReader.readLine();

			int lineCount = 0;

			while ((line != null) && (lineCount < numLines)) {
				header.append(line + "\n");
				line = bufferedReader.readLine();
				lineCount++;
			}
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}

		return header.toString();
	}

}
