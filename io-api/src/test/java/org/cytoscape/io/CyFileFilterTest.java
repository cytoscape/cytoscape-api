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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Set;

import org.junit.Test;

public abstract class CyFileFilterTest {

	protected CyFileFilter filter;
	
	protected Set<String> extensions;
	protected Set<String> contentTypes;
	protected String description;
	protected DataCategory category;
	protected DataCategory invalidCategory;
	
	protected URI validURI;
	protected URI invalidURI;

	@Test
	public void testConstructor() throws Exception {
		assertNotNull(extensions);
		assertNotNull(contentTypes);
		assertNotNull(description);
		assertNotNull(category);
		assertNotNull(invalidCategory);
		assertNotNull(filter);
	}
	
	@Test
	public void testAcceptsURIDataCategory() throws Exception{
		assertNotNull(validURI);
		assertTrue(filter.accepts(validURI, category));
		assertFalse(filter.accepts(invalidURI, category));
		assertFalse(filter.accepts(invalidURI, invalidCategory));
		assertFalse(filter.accepts(validURI, invalidCategory));
	}

	@Test
	public void testAcceptsInputStreamDataCategory() throws Exception {
		assertNotNull(validURI);
		assertTrue(filter.accepts(validURI.toURL().openStream(), category));
		assertFalse(filter.accepts(validURI.toURL().openStream(), invalidCategory));
		assertFalse(filter.accepts(invalidURI.toURL().openStream(), category));
		assertFalse(filter.accepts(invalidURI.toURL().openStream(), invalidCategory));
	}

	@Test
	public void testGetExtensions() {
		assertEquals(extensions, filter.getExtensions());
	}

	@Test
	public void testGetContentTypes() {
		assertEquals(contentTypes, filter.getContentTypes());
	}

	@Test
	public abstract void testGetDescription();

	@Test
	public void testGetDataCategory() {
		assertEquals(category, filter.getDataCategory());
	}

	@Test
	public void testToString() {
		assertNotNull(filter.toString());
	}
}
