package org.cytoscape.equations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;


public class IdentDescriptorTest {
	@Test
	public void testIntegerToLongMapping() {
		final IdentDescriptor id = new IdentDescriptor(Integer.valueOf(-108));
		assertEquals("Integer to Long translation is broken!", -108L, id.getValue());
	}

	@Test(expected=NullPointerException.class)
	public void testConstructionWithNullArgument() {
		final IdentDescriptor id = new IdentDescriptor(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructionBadArgument() {
		final IdentDescriptor id = new IdentDescriptor(Float.valueOf(10.3f));
	}

	@Test
	public void testConstructionWithLong() {
		final IdentDescriptor id = new IdentDescriptor(Long.valueOf(8L));
		assertEquals("Construction with Long is broken!", 8L, id.getValue());
	}

	@Test
	public void testConstructionWithBoolean() {
		final IdentDescriptor id = new IdentDescriptor(Boolean.valueOf(false));
		assertEquals("Construction with Boolean is broken!", false, id.getValue());
	}

	@Test
	public void testConstructionWithDouble() {
		final IdentDescriptor id = new IdentDescriptor(Double.valueOf(2e-13));
		assertEquals("Construction with Double is broken!", 2e-13, id.getValue());
	}

	@Test
	public void testConstructionWithString() {
		final IdentDescriptor id = new IdentDescriptor("abc");
		assertEquals("Construction with Long is broken!", "abc", id.getValue());
	}

	@Test
	public void testConstructionWithAListTypeAndGetType() {
		final IdentDescriptor id = new IdentDescriptor(new ArrayList<String>());
		assertEquals("Construction with a List type is broken!", List.class, id.getType());
	}
}
