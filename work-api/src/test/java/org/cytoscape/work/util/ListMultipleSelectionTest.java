/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.work.util;


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
		assertTrue("Expected Selected value not found! (1)", lms.getSelectedValues().contains("cabbages"));
		assertTrue("Expected Selected value not found! (1)", lms.getSelectedValues().contains("brocolli"));
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

	@Test(expected=IllegalArgumentException.class)
	public final void testConstructionWithEmptyList() throws Exception {
		new ListMultipleSelection<String>(new ArrayList<String>());
	}
}
