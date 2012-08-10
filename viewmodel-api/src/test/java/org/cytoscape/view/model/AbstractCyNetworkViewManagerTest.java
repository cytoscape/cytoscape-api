package org.cytoscape.view.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractCyNetworkViewManagerTest {
	
	protected CyEventHelper eventHelper;
	protected CyNetworkManager netManager;
	
	protected CyNetwork network1;
	protected CyNetwork network2;
	
	// Network without view
	protected CyNetwork network3;
	
	// Unregistered network
	protected CyNetwork unregisteredNetwork;
	
	// Views associated with network 1
	protected CyNetworkView networkViewA;
	protected CyNetworkView networkViewB;
	protected CyNetworkView networkViewC;
	
	// Views associated with network 2
	protected CyNetworkView networkViewD;
	protected CyNetworkView networkViewE;
	
	
	@Before
	public void setUp() throws Exception {
		eventHelper = mock(CyEventHelper.class);
		netManager = mock(CyNetworkManager.class);
		
		this.network1 = newNetwork(true);
		this.network2 = newNetwork(true);
		this.unregisteredNetwork = newNetwork(false);
		resgisterNetwork(network2);
		
		this.networkViewA = newNetworkView(network1, true);
		this.networkViewB = newNetworkView(network1, true);
		this.networkViewC = newNetworkView(network1, true);
		this.networkViewD = newNetworkView(network2, true);
		this.networkViewE = newNetworkView(network2, true);
	}
	
	@Test
	public void testGetNetworkViews() throws Exception {
		final CyNetworkViewManager viewMgr = getViewManager();
		
		final Collection<CyNetworkView> network1Views = viewMgr.getNetworkViews(network1);
		assertEquals(3, network1Views.size());
		assertTrue(network1Views.contains(networkViewA));
		assertTrue(network1Views.contains(networkViewB));
		assertTrue(network1Views.contains(networkViewC));
		
		final Collection<CyNetworkView> network2Views = viewMgr.getNetworkViews(network2);
		assertEquals(2, network2Views.size());
		assertTrue(network2Views.contains(networkViewD));
		assertTrue(network2Views.contains(networkViewE));
	}
	
	@Test
	public void testRemove() throws Exception {
		final CyNetworkViewManager viewMgr = getViewManager();
		
		viewMgr.destroyNetworkView(networkViewA);
		viewMgr.destroyNetworkView(networkViewE);
		
		final Collection<CyNetworkView> network1Views = viewMgr.getNetworkViews(network1);
		assertEquals(2, network1Views.size());
		assertFalse(network1Views.contains(networkViewA));
		assertTrue(network1Views.contains(networkViewB));
		assertTrue(network1Views.contains(networkViewC));
		
		final Collection<CyNetworkView> network2Views = viewMgr.getNetworkViews(network2);
		assertEquals(1, network2Views.size());
		assertTrue(network2Views.contains(networkViewD));
		assertFalse(network2Views.contains(networkViewE));
	}
	
	@Test
	public void testViewExists() throws Exception {
		CyNetworkViewManager viewMgr = getViewManager();
		
		assertTrue(viewMgr.viewExists(network1));
		assertTrue(viewMgr.viewExists(network2));
		assertFalse(viewMgr.viewExists(network3));
		
		viewMgr.destroyNetworkView(networkViewD);
		viewMgr.destroyNetworkView(networkViewE);
		
		assertFalse(viewMgr.viewExists(network2));
	}
	
	@Test
	public void testReset() throws Exception {
		CyNetworkViewManager viewMgr = getViewManager();
		viewMgr.reset();
		assertNotNull(viewMgr.getNetworkViewSet());
		assertEquals(0, viewMgr.getNetworkViewSet().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCannotRegisterViewIfModelIsNotRegistered() {
		newNetworkView(unregisteredNetwork, true);
	}
	
	protected void resgisterNetwork(CyNetwork net) {
		when(netManager.getNetwork(net.getSUID())).thenReturn(net);
		when(netManager.networkExists(net.getSUID())).thenReturn(true);
	}
	
	protected CyNetworkView newNetworkView(final CyNetwork net, boolean registered) {
		CyNetworkView view = mock(CyNetworkView.class);
		when(view.getModel()).thenReturn(net);
		
		if (registered)
			getViewManager().addNetworkView(view);
		
		return view;
	}
	
	/**
	 * @return The CyNetworkViewManager implementation to be tested.
	 */
	protected abstract CyNetworkViewManager getViewManager();
	
	/**
	 * @param registered If true, the new network must be added to the CyNetworkManager
	 * @return a new CyNetwork
	 */
	protected abstract CyNetwork newNetwork(boolean registered);
}
