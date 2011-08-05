package org.cytoscape.view.model.events;

import static org.junit.Assert.assertEquals;

import org.cytoscape.view.model.CyNetworkView;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FitSelectedEventTest {
	
	@Test
	public void testEvents() {
		CyNetworkView networkView = mock(CyNetworkView.class);
		FitSelectedEvent ev3 = new FitSelectedEvent(networkView);
		assertEquals(networkView, ev3.getSource());
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		FitSelectedEvent ev3 = new FitSelectedEvent(null);	
	}
}
