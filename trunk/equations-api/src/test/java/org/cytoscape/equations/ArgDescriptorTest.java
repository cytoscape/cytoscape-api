package org.cytoscape.equations;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class ArgDescriptorTest {
	private final ArgDescriptor argDesc = new ArgDescriptor(ArgType.INT, "count", "A fine int.");

	@Test
	public void testGetArgType() {
		assertEquals("getArgType() is broken.", ArgType.INT, argDesc.getArgType());
	}

	@Test
	public void testGetDescription() {
		assertEquals("getDescription() is broken.", "A fine int.", argDesc.getDescription());
	}

	@Test
	public void testIsCompatibleWithWithACompatibleType() {
		assertTrue("isCompatibleWith() is broken.", argDesc.isCompatibleWith(Long.class));
	}

	@Test
	public void testIsCompatibleWithWithAnIncompatibleType() {
		assertFalse("isCompatibleWith() is broken.", argDesc.isCompatibleWith(Integer.class));
	}

	@Test
	public void testIsCompatibleWithWithAnIncompatibleListType() {
		assertFalse("isCompatibleWith() is broken.", argDesc.isCompatibleWith(List.class));
	}

	@Test
	public void testAnArgDescriptorThatTakesAList() {
		final ArgDescriptor listArgDesc = new ArgDescriptor(ArgType.STRINGS, "strings", "A list of strings.");
		assertTrue("isCompatibleList() is broken.", listArgDesc.isCompatibleList(String.class));
		assertFalse("isCompatibleList() is broken.", listArgDesc.isCompatibleList(Object.class));
	}
}
