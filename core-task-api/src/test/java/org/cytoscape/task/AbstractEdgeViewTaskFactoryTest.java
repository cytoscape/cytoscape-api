package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractEdgeViewTaskFactoryTest {

	private class EdgeViewTaskFactory extends AbstractEdgeViewTaskFactory{

		@Override
		public TaskIterator createTaskIterator(View<CyEdge> edgeView,
				CyNetworkView networkView) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		EdgeViewTaskFactory evtf = new EdgeViewTaskFactory();
		assertTrue(evtf.isReady( (View<CyEdge>)( mock(View.class) ), mock(CyNetworkView.class)));
	}
}
