package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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
import static org.junit.Assert.assertTrue;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;
import org.junit.Before;
import org.junit.Test;

public class VisualPropertyUtilTest {

	private VisualLexicon lexicon;
	private NullVisualProperty twoDRoot;
	
	private VisualProperty<Double> dummy;

	@Before
	public void setUp() throws Exception {
		dummy = new DoubleVisualProperty(new Double(10), BasicVisualLexicon.ARBITRARY_DOUBLE_RANGE, "DUMMY", "Dummy Prop", CyNode.class);
		
		// Create root node.
		twoDRoot = new NullVisualProperty("TWO_D_ROOT",
				"2D Root Visual Property");

		lexicon = new BasicVisualLexicon(twoDRoot);
	}

	@Test
	public void testIsChildOf1() {
		assertTrue(VisualPropertyUtil.isChildOf(twoDRoot, BasicVisualLexicon.EDGE, lexicon));
		
		try {
			VisualPropertyUtil.isChildOf(twoDRoot, dummy, lexicon);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		assertFalse(VisualPropertyUtil.isChildOf(BasicVisualLexicon.NODE, BasicVisualLexicon.EDGE, lexicon));
	}
	
	@Test
	public void testIsChildOf2() {
		assertEquals(false, VisualPropertyUtil.isChildOf(twoDRoot, twoDRoot, lexicon));
	}
	
	@Test
	public void testIsChildOf3() {
		assertEquals(true, VisualPropertyUtil.isChildOf(BasicVisualLexicon.NODE_PAINT, BasicVisualLexicon.NODE_FILL_COLOR, lexicon));
	}
	
	@Test
	public void testIsChildOf4() {
		assertEquals(true, VisualPropertyUtil.isChildOf(BasicVisualLexicon.NODE_PAINT,
				BasicVisualLexicon.NODE_LABEL_COLOR, lexicon));
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsChildOfInvalid1() {
		VisualPropertyUtil.isChildOf(twoDRoot, null, lexicon);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsChildOfInvalid2() {
		VisualPropertyUtil.isChildOf(twoDRoot, dummy, null);
	}

}
