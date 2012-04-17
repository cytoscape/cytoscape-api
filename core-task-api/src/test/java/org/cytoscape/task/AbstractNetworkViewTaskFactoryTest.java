package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNetworkViewTaskFactoryTest {

	private class NetworkViewTaskFactory extends AbstractNetworkViewTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyNetworkView networkView) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		NetworkViewTaskFactory nvtf = new NetworkViewTaskFactory();
		assertTrue(nvtf.isReady(mock(CyNetworkView.class)));
	}
}

