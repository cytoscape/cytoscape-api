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

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URL;
import java.util.HashSet;

import org.cytoscape.io.util.StreamUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BasicCyFileFilterTest extends CyFileFilterTest {
	
	private static final String XML_EXT = "xml";
	private static final String XML_CONTENT_TYPE = "xml";
	private static final String LOCAL_SAMPLE_FILE = "filterTest.xml";
	private static final String LOCAL_SAMPLE_FILE_INVALID = "filterTest.txt";
	
	@Mock
	private StreamUtil streamUtil;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		extensions = new HashSet<String>();
		extensions.add(XML_EXT);
		contentTypes = new HashSet<String>();
		contentTypes.add(XML_CONTENT_TYPE);
		description = "test filter";
		category = DataCategory.NETWORK;
		final URL fileLocaiton = this.getClass().getClassLoader().getResource(LOCAL_SAMPLE_FILE);
		assertNotNull(fileLocaiton);
		validURI = fileLocaiton.toURI();
		
		final URL fileLocaiton2 = this.getClass().getClassLoader().getResource(LOCAL_SAMPLE_FILE_INVALID);
		assertNotNull(fileLocaiton2);
		invalidURI = fileLocaiton2.toURI();
		
		this.filter = new BasicCyFileFilter(extensions, contentTypes, description, category, streamUtil);
	}
	
	@Test
	public void testConstructor() {
		this.filter = new BasicCyFileFilter(new String[] {"xml"}, new String[] {}, LOCAL_SAMPLE_FILE, category, streamUtil);
		assertNotNull(filter);
	}
	
	@Test @Override
	public void testAcceptsInputStreamDataCategory() throws Exception {
		assertNotNull(validURI);
		assertFalse(filter.accepts(validURI.toURL().openStream(), category));
	}

	@Test @Override
	public void testGetDescription() {
		assertEquals(description + " (*.xml)", filter.getDescription());
	}
	
	@Test
	public void testGetHeader() throws Exception {
		String header = ((BasicCyFileFilter) filter).getHeader(validURI.toURL().openStream(),10);
		assertNotNull(header);
		assertTrue(header.contains("xml version="));
	}
}
