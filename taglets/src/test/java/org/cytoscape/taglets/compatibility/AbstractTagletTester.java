
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

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet; 

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.HashMap;

public abstract class AbstractTagletTester {
	
	protected Taglet taglet; 
	
	@Test
	public void testInField() {
		assertFalse(taglet.inField());
	}

	@Test
	public void testInConstructor() {
		assertFalse(taglet.inConstructor());
	}

	@Test
	public void testInMethod() {
		assertFalse(taglet.inMethod());
	}

	@Test
	public void testInOverview() {
		assertFalse(taglet.inOverview());
	}

	@Test
	public void testInPackage() {
		assertFalse(taglet.inPackage());
	}

	@Test
	public void testInType() {
		assertTrue(taglet.inType());
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
	public void testToString() {
		Tag t = mock(Tag.class);
		when(t.text()).thenReturn("test text");
		assertNotNull(taglet.toString(t));
	}

	@Test
	public void testToStringArray() {
		Tag t = mock(Tag.class);
		when(t.text()).thenReturn("test text");
		Tag t2 = mock(Tag.class);
		when(t2.text()).thenReturn("test text");
		Tag[] tags = new Tag[]{t,t2};
		assertNotNull(taglet.toString(tags));
	}
}
