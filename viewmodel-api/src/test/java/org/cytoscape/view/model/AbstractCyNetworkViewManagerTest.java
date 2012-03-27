package org.cytoscape.view.model;

import static org.junit.Assert.*;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;
import org.junit.Test;

public abstract class AbstractCyNetworkViewManagerTest {
	
	protected CyNetworkViewManager viewManager;
	protected CyNetwork network1;
	protected CyNetwork network2;
	
	// Netwok without view
	protected CyNetwork network3;
	
	// Views associated with network 1
	protected CyNetworkView networkViewA;
	protected CyNetworkView networkViewB;
	protected CyNetworkView networkViewC;
	
	// Views associated with network 2
	protected CyNetworkView networkViewD;
	protected CyNetworkView networkViewE;
	
	
	@Test
	public void testGetNetworkViews() throws Exception {
		final Collection<CyNetworkView> network1Views = viewManager.getNetworkViews(network1);
		assertEquals(3, network1Views.size());
		assertTrue(network1Views.contains(networkViewA));
		assertTrue(network1Views.contains(networkViewB));
		assertTrue(network1Views.contains(networkViewC));
		
		final Collection<CyNetworkView> network2Views = viewManager.getNetworkViews(network2);
		assertEquals(2, network2Views.size());
		assertTrue(network2Views.contains(networkViewD));
		assertTrue(network2Views.contains(networkViewE));
	}
	
	@Test
	public void testRemove() throws Exception {
		viewManager.destroyNetworkView(networkViewA);
		viewManager.destroyNetworkView(networkViewE);
		
		final Collection<CyNetworkView> network1Views = viewManager.getNetworkViews(network1);
		assertEquals(2, network1Views.size());
		assertFalse(network1Views.contains(networkViewA));
		assertTrue(network1Views.contains(networkViewB));
		assertTrue(network1Views.contains(networkViewC));
		
		final Collection<CyNetworkView> network2Views = viewManager.getNetworkViews(network2);
		assertEquals(1, network2Views.size());
		assertTrue(network2Views.contains(networkViewD));
		assertFalse(network2Views.contains(networkViewE));
	}
	
	@Test
	public void testViewExists() throws Exception {
		assertTrue(viewManager.viewExists(network1));
		assertTrue(viewManager.viewExists(network2));
		assertFalse(viewManager.viewExists(network3));
		
		viewManager.destroyNetworkView(networkViewD);
		viewManager.destroyNetworkView(networkViewE);
		
		assertFalse(viewManager.viewExists(network2));
	}
	
	@Test
	public void testReset() throws Exception {
		viewManager.reset();
		
		assertNotNull(viewManager.getNetworkViewSet());
		assertEquals(0, viewManager.getNetworkViewSet().size());
	}
}
