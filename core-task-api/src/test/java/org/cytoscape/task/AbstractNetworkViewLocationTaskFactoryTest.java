package org.cytoscape.task;

import java.awt.geom.Point2D;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractNetworkViewLocationTaskFactoryTest {

	private class NetworkViewLocationTaskFactory extends AbstractNetworkViewLocationTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyNetworkView networkView,
				Point2D javaPt, Point2D xformPt) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		NetworkViewLocationTaskFactory nvltf = new NetworkViewLocationTaskFactory();
		assertTrue(nvltf.isReady(mock(CyNetworkView.class), mock(Point2D.class), mock(Point2D.class)));
	}
	
}
