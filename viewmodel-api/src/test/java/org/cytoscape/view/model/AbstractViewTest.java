package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractViewTest<S> {

	protected VisualProperty<Integer> integerVP;
	protected VisualProperty<String> stringVP;
	
	protected View<S> view;
	
	@Before
	public void setUp() throws Exception {
		integerVP = new IntegerVisualProperty();
		stringVP = new StringVisualProperty();
	}

	
	@Test
	public void testVisualPropertyGetterAndSetter() {
		view.setVisualProperty(integerVP, 1);
		assertEquals(Integer.valueOf(1), view.getVisualProperty(integerVP));
		
		// For Null, return default value. 
		view.setVisualProperty(integerVP, null);
		assertNotNull(view.getVisualProperty(integerVP));
		
		view.setVisualProperty(integerVP,-12345);
		assertEquals(Integer.valueOf(-12345), view.getVisualProperty(integerVP));
		
		view.setVisualProperty(stringVP, "Test string.");
		assertEquals("Test string.", view.getVisualProperty(stringVP));
		
		view.setVisualProperty(stringVP, "");
		assertEquals("", view.getVisualProperty(stringVP));
		
		// For Null, return default value.
		view.setVisualProperty(stringVP, null);
		assertNotNull(view.getVisualProperty(stringVP));
		
	}
	
	@Test
	public void testIsSet() {
		assertFalse(view.isSet(integerVP));
		view.setVisualProperty(integerVP, 1);
		assertTrue(view.isSet(integerVP));
		view.setVisualProperty(integerVP, null);
		assertFalse(view.isSet(integerVP));
	}

	@Test
	public void testLock() {
		final Integer unlocked = 123;
		final Integer locked = 1000;
		
		view.setVisualProperty(integerVP, unlocked);
		assertFalse(view.isValueLocked(integerVP));
		
		view.setLockedValue(integerVP, locked);
		assertTrue(view.isValueLocked(integerVP));
		assertEquals(locked, view.getVisualProperty(integerVP));
		
		view.clearValueLock(integerVP);
		assertEquals(unlocked, view.getVisualProperty(integerVP));
		
	}

	

	@Test
    public void testAddViewChangeListener() {
//		ViewChangeListener mock = createMock(ViewChangeListener.class);
//		mock.visualPropertySet(integerVP,5);
//		replay(mock);

//		view.addViewChangeListener( mock );
//		view.setVisualProperty(integerVP,5);
//
//		verify(mock);
	}

	@Test
    public void testRemoveViewChangeListener() {
//		ViewChangeListener mock = createMock(ViewChangeListener.class);
//		mock.visualPropertySet(integerVP,5);
//		replay(mock);

//		view.addViewChangeListener( mock );
//		view.setVisualProperty(intVP,5);
//
//		view.removeViewChangeListener( mock );
//		view.setVisualProperty(intVP,10);
//
//		verify(mock);
	}
}

