package org.cytoscape.view.vizmap.gui.util;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.junit.Test;

public class PropertySheetUtilTest {
	
	@Test
	public void testPreset (){
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_FILL_COLOR));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_SHAPE));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_WIDTH));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_HEIGHT));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_LABEL));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_BORDER_PAINT));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NODE_BORDER_WIDTH));
		
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.EDGE_STROKE_UNSELECTED_PAINT));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.EDGE_WIDTH));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.EDGE_LABEL));
		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.EDGE_LINE_TYPE));

		assertTrue(PropertySheetUtil.isBasic(BasicVisualLexicon.NETWORK_BACKGROUND_PAINT));
		
	}

	@Test
	public void testIsBasicFalse(){
		assertFalse(PropertySheetUtil.isBasic( mock (VisualProperty.class)));
	}
	
	@Test
	public void testAdvanceMode(){
		PropertySheetUtil.setMode(true);
		assertTrue(PropertySheetUtil.isAdvancedMode());
		
		PropertySheetUtil.setMode(false);
		assertFalse(PropertySheetUtil.isAdvancedMode());
	}
	
	@Test 
	public void testAddBasicVisualProperty(){
		
		VisualProperty<?> vp = null;
		PropertySheetUtil.addBasicVisualProperty(vp);
		assertFalse(PropertySheetUtil.isBasic(vp));
		
		
		 vp = mock(VisualProperty.class);
			PropertySheetUtil.addBasicVisualProperty(vp);
			assertTrue(PropertySheetUtil.isBasic(vp));
	}
}


