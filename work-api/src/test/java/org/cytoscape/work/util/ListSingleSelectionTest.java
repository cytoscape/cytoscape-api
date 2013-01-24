package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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
import org.junit.Before;
import org.junit.Test;
import java.util.Collections; 
import java.util.List; 


public class ListSingleSelectionTest {
	private ListSingleSelection<String> lss;

	@Before
	public void init() {
		lss = new ListSingleSelection<String>("carrots", "cabbages", "brocolli", "cucumbers");
	}

	@Test
	public final void testSetAndGetSelectedValue() throws Exception {
		lss.setSelectedValue("cabbages");
		assertEquals("Selected value not as expected.", "cabbages", lss.getSelectedValue());
	}

	@Test
	public final void testSetSelectedValueWithRiskySelection() throws Exception {
		lss.setSelectedValue("oranges");
		assertEquals("Selected value not as expected.", "oranges", lss.getSelectedValue());
	}

	@Test(expected=NullPointerException.class)
	public final void testNullListInConstructor() throws Exception {
		List<String> l = null;
		lss = new ListSingleSelection<String>(l);
	}

	@Test
	public final void testEmptyListInConstructor() throws Exception {
		lss = new ListSingleSelection<String>(Collections.EMPTY_LIST);
	}
}
