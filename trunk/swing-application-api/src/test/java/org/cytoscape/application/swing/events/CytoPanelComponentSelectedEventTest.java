package org.cytoscape.application.swing.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.application.swing.CytoPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CytoPanelComponentSelectedEventTest {

	CytoPanelComponentSelectedEvent event;
	@Mock
	private Object source;
	@Mock
	private CytoPanel cp;

	private int index = 0;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new CytoPanelComponentSelectedEvent(source, cp, index);
	}

	@Test
	public void testCytoPanelComponentSelectedEvent() {
		assertNotNull(event);
	}

	@Test
	public void testGetCytoPanel() {
		assertEquals(cp, event.getCytoPanel());
	}

	@Test
	public void testGetSelectedIndex() {
		assertEquals(index, event.getSelectedIndex());
	}

}
