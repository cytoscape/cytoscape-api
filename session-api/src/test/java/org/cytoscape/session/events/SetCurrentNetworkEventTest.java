package org.cytoscape.session.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.session.CyApplicationManager;
import org.junit.Test;

public class SetCurrentNetworkEventTest {

	@Test
	public void testGoodGetNetwork() {
		CyApplicationManager source = mock(CyApplicationManager.class);
		CyNetwork n = mock(CyNetwork.class);
		SetCurrentNetworkEvent e = new SetCurrentNetworkEvent(source,n);
		assertNotNull( e.getNetwork() );
		assertEquals( n,e.getNetwork() );
	}

	@Test
	public void testNullGetNetwork() {
		CyApplicationManager source = mock(CyApplicationManager.class);
		SetCurrentNetworkEvent e = new SetCurrentNetworkEvent(source,null);
		assertNull( e.getNetwork() );
	}

}
