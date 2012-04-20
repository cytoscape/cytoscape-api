package org.cytoscape.view.vizmap.gui.event;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.cytoscape.view.vizmap.VisualStyle;
import org.junit.Test;

public class SelectedVisualStyleSwitchedEventTest {

	@Test
	public void testSelectedVisualStyleSwitchedEvent(){
		
		VisualStyle lastVS = mock(VisualStyle.class);
		VisualStyle newVS = mock(VisualStyle.class);
		SelectedVisualStyleSwitchedEvent event = new SelectedVisualStyleSwitchedEvent(mock(Object.class),lastVS , newVS);
		assertEquals(lastVS, event.getLastVisualStyle());
		assertEquals(newVS, event.getNewVisualStyle());
		
	}
}
