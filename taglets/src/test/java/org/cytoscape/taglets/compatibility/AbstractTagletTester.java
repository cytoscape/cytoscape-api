
package org.cytoscape.taglets.compatibility;

/*
 * #%L
 * Cytoscape Documentation Taglets
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
import com.sun.source.doctree.DocTree;

import jdk.javadoc.doclet.Taglet;
import jdk.javadoc.doclet.Taglet.Location;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.HashMap;

public abstract class AbstractTagletTester {
	
	protected Taglet taglet; 
	


	@Test
	public void testType() {
		assertEquals(taglet.getAllowedLocations().size(),1);
		assertTrue(taglet.getAllowedLocations().contains(Location.TYPE));
	}

	@Test
	public void testIsInlineTag() {
		assertFalse(taglet.isInlineTag());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRegisterNormal() {
		Map map = new HashMap();
		doRegister(map);
		assertTrue( map.containsKey( taglet.getName() ) );
		assertNotNull( map.get( taglet.getName() ) ); 
	}

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void testRegisterContains() {
		Map map = new HashMap();
		Taglet exists = mock(Taglet.class);
		when(exists.getName()).thenReturn(taglet.getName());
		map.put(exists.getName(),exists);
		doRegister(map);
		assertTrue( map.containsKey( taglet.getName() ) );
		assertNotNull( map.get( taglet.getName() ) ); 
		assertTrue( map.get( taglet.getName() ) != exists );
	}

	abstract void doRegister(Map map);

	@Test
	@Ignore
	public void testToString() {
		/*
		DocTree t = mock(DocTree.class);
		when(t.text()).thenReturn("test text");
		assertNotNull(taglet.toString(t));
		*/
	}

	@Test
	@Ignore
	public void testToStringArray() {
		/*
		Tag t = mock(Tag.class);
		when(t.text()).thenReturn("test text");
		Tag t2 = mock(Tag.class);
		when(t2.text()).thenReturn("test text");
		Tag[] tags = new Tag[]{t,t2};
		assertNotNull(taglet.toString(tags));
		*/
	}
}
