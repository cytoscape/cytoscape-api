package org.cytoscape.application.swing.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CytoPanelStateChangedEventTest {

	CytoPanelStateChangedEvent event;
	
	@Mock
	private Object source;
	@Mock
	private CytoPanel cp;
	private CytoPanelState newState = CytoPanelState.DOCK;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new CytoPanelStateChangedEvent(source, cp, newState);
	}

	@Test
	public void testCytoPanelStateChangedEvent() {
		assertNotNull(event);
	}

	@Test
	public void testGetCytoPanel() {
		assertEquals(cp, event.getCytoPanel());
	}

	@Test
	public void testGetNewState() {
		assertEquals(newState, event.getNewState());
	}
}