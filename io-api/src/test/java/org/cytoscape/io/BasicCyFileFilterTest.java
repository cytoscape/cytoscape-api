package org.cytoscape.io;

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
		
		this.filter = new BasicCyFileFilter(extensions, contentTypes, description, category, streamUtil);
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
	
}
