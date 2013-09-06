package org.cytoscape.io.read;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.junit.Test;

public abstract class AbstractCyNetworkReaderTest {
	
	private AbstractCyNetworkReader reader;


	@Test
	public void testAbstractCyNetworkReader() {
		assertNotNull(reader);
	}

	@Test
	public void testGetNetworks() {
		// Should return empty array, not null.
		final CyNetwork[] networks = reader.getNetworks();
		assertNotNull(networks);
		
	}


	@Test
	public void testGetNodeMap() {
		final Map<Object, CyNode> nodeMap = reader.getNodeMap();
		assertNotNull(nodeMap);
	}

}
