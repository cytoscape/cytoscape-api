package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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


import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ListMultipleSelectionTest {
	private ListMultipleSelection<String> lms;
	
	@Before
	public void init() {
		lms = new ListMultipleSelection<String>("carrots", "cabbages", "brocolli", "cucumbers");
	}

	@Test
	public final void testSetAndGetSelectedValues() throws Exception {
		final ArrayList<String> selectedValues = new ArrayList<String>(2);
		selectedValues.add("cabbages");
		selectedValues.add("brocolli");
		lms.setSelectedValues(selectedValues);
		assertTrue("Expected Selected value not found. (1)", lms.getSelectedValues().contains("cabbages"));
		assertTrue("Expected Selected value not found. (1)", lms.getSelectedValues().contains("brocolli"));
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSetSelectedValuesWithInvalidSelection() throws Exception {
		final ArrayList<String> selectedValues = new ArrayList<String>(2);
		selectedValues.add("oranges");
		lms.setSelectedValues(selectedValues);
	}

	@Test(expected=NullPointerException.class)
	public final void testSetSelectedValuesWithNullSelection() throws Exception {
		lms.setSelectedValues(null);
	}

	@Test(expected=NullPointerException.class)
	public final void testConstructionWithNullArgument() throws Exception {
		final ArrayList<String> nullList = null;
		new ListMultipleSelection<String>(nullList);
	}

	@Test
	public final void testConstructionWithEmptyList() throws Exception {
		new ListMultipleSelection<String>(new ArrayList<String>());
	}
}
