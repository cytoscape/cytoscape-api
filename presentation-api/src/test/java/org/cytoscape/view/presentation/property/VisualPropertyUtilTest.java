package org.cytoscape.view.presentation.property;

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
		dummy = new DoubleVisualProperty(new Double(10), MinimalVisualLexicon.ARBITRARY_DOUBLE_RANGE, "DUMMY", "Dummy Prop", CyNode.class);
		
		// Create root node.
		twoDRoot = new NullVisualProperty("TWO_D_ROOT",
				"2D Root Visual Property");

		lexicon = new MinimalVisualLexicon(twoDRoot);
	}

	@Test
	public void testIsChildOf1() {
		assertTrue(VisualPropertyUtil.isChildOf(twoDRoot, MinimalVisualLexicon.EDGE, lexicon));
		
		try {
			VisualPropertyUtil.isChildOf(twoDRoot, dummy, lexicon);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		assertFalse(VisualPropertyUtil.isChildOf(MinimalVisualLexicon.NODE, MinimalVisualLexicon.EDGE, lexicon));
	}
	
	@Test
	public void testIsChildOf2() {
		assertEquals(false, VisualPropertyUtil.isChildOf(twoDRoot, twoDRoot, lexicon));
	}
	
	@Test
	public void testIsChildOf3() {
		assertEquals(true, VisualPropertyUtil.isChildOf(MinimalVisualLexicon.NODE_PAINT, MinimalVisualLexicon.NODE_FILL_COLOR, lexicon));
	}
	
	@Test
	public void testIsChildOf4() {
		assertEquals(true, VisualPropertyUtil.isChildOf(MinimalVisualLexicon.NODE_PAINT, MinimalVisualLexicon.NODE_LABEL_COLOR, lexicon));
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
