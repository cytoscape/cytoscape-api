package org.cytoscape.model.events;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class NetworkAddedEventTest {
	@Test
	public final void testGetNetwork() {
		final CyNetworkManager networkManager = mock(CyNetworkManager.class);
		final CyNetwork network = mock(CyNetwork.class);
		final NetworkAddedEvent event =
			new NetworkAddedEvent(networkManager, network);
		assertEquals("Network returned by getNetwork() is *not* the one passed into the constructor!",
			     network, event.getNetwork());
	}

	@Test
	public final void testNullNetworkConstructorFailure() {
		final CyNetworkManager networkManager = mock(CyNetworkManager.class);
		try {
			final NetworkAddedEvent event =
				new NetworkAddedEvent(networkManager, null);
		} catch (final NullPointerException e) {
			return;
		}

		fail("The expected NullPointerException was *not* triggered!");
	}
}