package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
