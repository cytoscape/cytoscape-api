package org.cytoscape.application.events;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.events.SetSelectedNetworksEvent;
import org.cytoscape.model.CyNetwork;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetSelectedNetworksEventTest {

	@Mock
	private CyApplicationManager source;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGoodGetSelectedNetworksEvent() {
		final List<CyNetwork> networks = new ArrayList<CyNetwork>();
		final SetSelectedNetworksEvent e = new SetSelectedNetworksEvent(source, networks);
		assertNotNull(e.getNetworks());
		assertEquals(networks, e.getNetworks());
	}

	@Test
	public void testNullGetSelectedNetworksEvent() {
		final SetSelectedNetworksEvent e = new SetSelectedNetworksEvent(source, null);
		assertNull(e.getNetworks());
	}

}
