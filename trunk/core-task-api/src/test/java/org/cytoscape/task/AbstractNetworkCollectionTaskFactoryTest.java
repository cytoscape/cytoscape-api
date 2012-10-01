package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNetworkCollectionTaskFactoryTest {
	
	private class NetworkCollectionTaskFactory extends AbstractNetworkCollectionTaskFactory{

		@Override
		public TaskIterator createTaskIterator(Collection<CyNetwork> networks) {
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		NetworkCollectionTaskFactory nctf  = new NetworkCollectionTaskFactory();
		assertTrue(nctf.isReady((Collection<CyNetwork>)mock(Collection.class) ));

	}

}
