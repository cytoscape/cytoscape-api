package org.cytoscape.util.swing;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileChooserFilterTest {
	
	private FileChooserFilter filter;
	private String description = "test filter";
	private String extension = "xml";
	private String[] extensions = new String[] {"xml", "rdf"};

	@Before
	public void setUp() throws Exception {
		filter = new FileChooserFilter(description, extensions);
	}


	@Test
	public void testFileChooserFilterConstructor() {
		assertNotNull(filter);
		FileChooserFilter filter2 = new FileChooserFilter(description, extension);
		assertNotNull(filter2);
	}

	@Test
	public void testAcceptFile() {
		File rdfFile = new File("test.rdf");
		File xmlFile = new File("test.xml");
		File textFile = new File("test.txt");
		
		assertTrue(filter.accept(rdfFile));
		assertTrue(filter.accept(xmlFile));
		assertFalse(filter.accept(textFile));
	}

	@Test
	public void testGetDescription() {
		assertEquals(description, filter.getDescription());
	}

	@Test
	public void testGetExtensions() {
		final String[] exts = filter.getExtensions();
		assertEquals(2, exts.length);
	}

	@Test
	public void testEqualsObject() {
		FileChooserFilter filter2 = new FileChooserFilter(description, extension);
		assertFalse(filter.equals(filter2));
	}

}
