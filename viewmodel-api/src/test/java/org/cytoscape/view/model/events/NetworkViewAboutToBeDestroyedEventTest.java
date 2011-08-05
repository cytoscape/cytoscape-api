package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class NetworkViewAboutToBeDestroyedEventTest {
	@Test
	public final void testGetNetworkView() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		final CyNetworkView networkView = mock(CyNetworkView.class);
		final NetworkViewAboutToBeDestroyedEvent event =
			new NetworkViewAboutToBeDestroyedEvent(networkViewManager, networkView);
		assertEquals("Network returned by getNetworkView() is *not* the one passed into the constructor!",
			     networkView, event.getNetworkView());
	}

	@Test
	public final void testNullNetworkConstructorFailure() {
		final CyNetworkViewManager networkViewManager = mock(CyNetworkViewManager.class);
		try {
			final NetworkViewAboutToBeDestroyedEvent event =
				new NetworkViewAboutToBeDestroyedEvent(networkViewManager, null);
		} catch (final NullPointerException e) {
			return;
		}

		fail("The expected NullPointerException was *not* triggered!");
	}
}