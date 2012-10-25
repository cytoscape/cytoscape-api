package org.cytoscape.view.model;

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

