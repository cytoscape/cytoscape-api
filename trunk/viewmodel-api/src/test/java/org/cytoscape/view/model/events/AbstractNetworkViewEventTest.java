package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class AbstractNetworkViewEventTest {
	@Test
	public final void testGetNetworkView() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		final CyNetworkView networkView = mock(CyNetworkView.class);
		final AbstractNetworkViewEvent event = new AbstractNetworkViewEvent(networkViewManager, Object.class, networkView);
		assertEquals("Network returned by getNetworkView() is *not* the one passed into the constructor!", networkView, event.getNetworkView());
	}

	@Test(expected=NullPointerException.class)
	public final void testNullNetworkConstructorFailure() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		final AbstractNetworkViewEvent event = new AbstractNetworkViewEvent(networkViewManager, Object.class, null);
	}
}