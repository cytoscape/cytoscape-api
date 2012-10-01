package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class NetworkViewDestroyedEventTest {
	@Test
	public final void testGetNetwork() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		final NetworkViewDestroyedEvent event =
			new NetworkViewDestroyedEvent(networkViewManager);
		assertEquals("NetworkViewManager returned by getSource() is *not* the one passed into the constructor!",
			     networkViewManager, event.getSource());
	}
}