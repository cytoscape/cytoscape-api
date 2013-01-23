package org.cytoscape.event;

/*
 * #%L
 * Cytoscape Event API (event-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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


import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 */
public class AbstractCyEventTest {

	private static class TestEvent<T> extends AbstractCyEvent<T> {
		TestEvent(T src, Class<?> c) {
			super(src,c);
		}
	}

	@Test
	public void testGetSource() {
		Integer i = new Integer(1);
		TestEvent<Integer> e = new TestEvent<Integer>(i,Integer.class);
		assertEquals( i, e.getSource() ); 
	}

	@Test
	public void testGetListenerClass() {
		Object i = new Object(); 
		TestEvent<Object> e = new TestEvent<Object>(i,Object.class);
		assertEquals( Object.class, e.getListenerClass() ); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		new TestEvent<Object>(null, Object.class);
	}

	@Test(expected=NullPointerException.class)
	public void testNullListenerClass() {
		new TestEvent<Object>(new Object(), null);
	}

}
