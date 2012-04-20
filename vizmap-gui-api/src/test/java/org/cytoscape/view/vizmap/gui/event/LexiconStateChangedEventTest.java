package org.cytoscape.view.vizmap.gui.event;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.cytoscape.view.model.VisualProperty;
import org.junit.Test;

public class LexiconStateChangedEventTest {
	
	@Test
	public void testLexiconStateChangedEvent(){
		
		Set<VisualProperty<?>> enabled = (Set<VisualProperty<?>>) mock(Set.class);
		Set<VisualProperty<?>> disabled = (Set<VisualProperty<?>>) mock(Set.class);
		LexiconStateChangedEvent event = new LexiconStateChangedEvent(mock(Object.class), enabled , disabled);
		
		assertEquals(enabled, event.getEnabled());
		assertEquals(disabled, event.getDisabled());
	}

}
