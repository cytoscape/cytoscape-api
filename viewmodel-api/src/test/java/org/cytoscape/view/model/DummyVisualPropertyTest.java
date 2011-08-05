package org.cytoscape.view.model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DummyVisualPropertyTest extends AbstractVisualPropertyTest<DummyObject> {

	@Before
	public void setUp() throws Exception {
		id = "DummyVisualProperty";
		defaultVal = new DummyObject();
		displayName = "Dummy Visual Property";
		
		final Set<DummyObject> dummySet = new HashSet<DummyObject>();
		dummySet.add(new DummyObject());
		range = new DiscreteRange<DummyObject>(DummyObject.class, dummySet);
		ignore = false;
		vp = new DummyVisualProperty(defaultVal, id, displayName, range);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDummyVisualProperty() {
		DummyVisualProperty dummy1 = null;
		try {
			dummy1 = new DummyVisualProperty(null, id, displayName, range);
		} catch(Exception ex) {
			assertNull(dummy1);
			assertEquals(NullPointerException.class, ex.getClass());
		}
		
		DummyVisualProperty dummy2 = null;
		try {
			dummy2 = new DummyVisualProperty(defaultVal, null, displayName, range);
		} catch(Exception ex) {
			assertNull(dummy2);
			assertEquals(NullPointerException.class, ex.getClass());
		}
		
		DummyVisualProperty dummy3 = null;
		try {
			dummy3 = new DummyVisualProperty(defaultVal, id, null, range);
		} catch(Exception ex) {
			assertNull(dummy3);
			assertEquals(NullPointerException.class, ex.getClass());
		}
		
		assertNotNull(vp);
	}


	@Test
	public void testToSerializableString() {
		assertNotNull(vp.toSerializableString(new DummyObject()));
	}

	@Test
	public void testParseSerializableString() {
		final DummyObject newObject = vp.parseSerializableString("test");
		assertTrue(newObject instanceof DummyObject);
	}
}
