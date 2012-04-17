package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNetworkViewCollectionTaskFactoryTest {
	
	private class NetworkViewCollectionTaskFactory extends AbstractNetworkViewCollectionTaskFactory{

		@Override
		public TaskIterator createTaskIterator(
				Collection<CyNetworkView> networkViews) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	@Test
	public void testIsReady(){
		NetworkViewCollectionTaskFactory nvctf = new NetworkViewCollectionTaskFactory();
		assertTrue(nvctf.isReady((Collection<CyNetworkView>) mock(Collection.class)));
	}
}
