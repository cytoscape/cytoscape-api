package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNetworkTaskFactoryTest {

	private class NetworkTaskFactory extends AbstractNetworkTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyNetwork network) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		NetworkTaskFactory ntf = new NetworkTaskFactory();
		assertTrue(ntf.isReady(mock(CyNetwork.class)));
	}
}
