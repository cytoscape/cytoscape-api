package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class NetworkViewAddedEventTest {
	@Test
	public final void testGetNetworkView() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		final CyNetworkView networkView = mock(CyNetworkView.class);
		final NetworkViewAddedEvent event =
			new NetworkViewAddedEvent(networkViewManager, networkView);
		assertEquals("Network returned by getNetworkView() is *not* the one passed into the constructor!",
			     networkView, event.getNetworkView());
	}

	@Test
	public final void testNullNetworkConstructorFailure() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		try {
			final NetworkViewAddedEvent event =
				new NetworkViewAddedEvent(networkViewManager, null);
		} catch (final NullPointerException e) {
			return;
		}

		fail("The expected NullPointerException was *not* triggered!");
	}
}