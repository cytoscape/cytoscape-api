package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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
