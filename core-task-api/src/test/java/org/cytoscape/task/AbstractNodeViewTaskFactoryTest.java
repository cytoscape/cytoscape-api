package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNodeViewTaskFactoryTest {

	private class NodeViewTaskFactory extends AbstractNodeViewTaskFactory{

		@Override
		public TaskIterator createTaskIterator(View<CyNode> nodeView,
				CyNetworkView networkView) {
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		NodeViewTaskFactory nvtf = new NodeViewTaskFactory();
		assertTrue(nvtf.isReady((View<CyNode>)mock(View.class),  mock(CyNetworkView.class)));
	}
	
}
