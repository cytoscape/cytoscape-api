package org.cytoscape.io;

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
