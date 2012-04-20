package org.cytoscape.view.vizmap.gui.editor;


import static org.junit.Assert.*;

import org.junit.Test;

public class ContinuousEditorTypeTest {

	@Test
	public void testContinuousEditorType(){
		assertNotNull(ContinuousEditorType.valueOf("DISCRETE"));
		assertNotNull(ContinuousEditorType.valueOf("CONTINUOUS"));
		assertNotNull(ContinuousEditorType.valueOf("COLOR"));
	
	}
}
