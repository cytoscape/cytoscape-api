package org.cytoscape.app;

/*
 * #%L
 * Cytoscape App API (app-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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



import org.junit.Test;
import static org.junit.Assert.*;


public abstract class CyAppAdapterTest {
	protected CyAppAdapter adapter;
	
	@Test
	public void testGetCyTableFactory() { 
		assertNotNull("dataTable exists", adapter.getCyTableFactory());
	}
	
	@Test
	public void testGetCyEventHelper() { 
		assertNotNull("dataTable exists", adapter.getCyEventHelper());
	} 

	@Test
	public void testGetCyLayoutAlgorithmManager() { 
		assertNotNull("CyLayouts exists", adapter.getCyLayoutAlgorithmManager());
	} 

	@Test
	public void testGetCyNetworkFactory() { 
		assertNotNull("CyNetworkFactory exists", adapter.getCyNetworkFactory());
	}

	@Test
	public void testGetCyNetworkManager() { 
		assertNotNull("CyNetworkManager exists", adapter.getCyNetworkManager());
	} 

	@Test
	public void testGetCyNetworkViewFactory() {
		assertNotNull("CyNetworkViewFactory exists", adapter.getCyNetworkViewFactory());
	}

	@Test
	public void testGetCyRootNetworkManager() {
		assertNotNull("CyRootNetworkFactory exists", adapter.getCyRootNetworkManager());
	} 

	@Test
	public void testGetCySessionManager() { 
		assertNotNull("CySessionManager exists", adapter.getCySessionManager());
	} 

	@Test
	public void testGetTaskManager() { 
		assertNotNull("TaskManager exists", adapter.getTaskManager());
	}

	@Test
	public void testGetVisualMappingManager() { 
		assertNotNull("VisualMappingManager exists", adapter.getVisualMappingManager());
	} 

	@Test
	public void testGetCyNetworkViewManager() { 
		assertNotNull("NetworkViewManager exists", adapter.getCyNetworkViewManager());
	} 

	@Test
	public void testGetCyApplicationManager() { 
		assertNotNull("CyApplicationManager exists", adapter.getCyApplicationManager());
	} 

	@Test
	public void testCyTableManager() { 
		assertNotNull("CyTableManager exists", adapter.getCyTableManager());
	} 

	@Test
	public void testUndoSupport() { 
		assertNotNull("UndoSupport exists", adapter.getUndoSupport());
	} 

	@Test
	public void testGetRenderingEngineManager() { 
		assertNotNull("RenderingEngineManager exists", adapter.getRenderingEngineManager());
	} 

	@Test
	public void testVisualStyleFactory() { 
		assertNotNull("VisualStyleFactory exists", adapter.getVisualStyleFactory());
	} 

	@Test
	public void testCyPropertyProperties() { 
		assertNotNull("CyProperty<Properties> exists", adapter.getCoreProperties());
	} 

	@Test
	public void testCyNetworkViewReaderManager() { 
		assertNotNull("CyNetworkViewReaderManager exists", adapter.getCyNetworkViewReaderManager());
	} 

	@Test
	public void testCyPropertyReaderManager() { 
		assertNotNull("CyPropertyReaderManager exists", adapter.getCyPropertyReaderManager());
	} 

	@Test
	public void testCySessionReaderManager() { 
		assertNotNull("CySessionReaderManager exists", adapter.getCySessionReaderManager());
	} 

	@Test
	public void testCyTableReaderManager() { 
		assertNotNull("CyTableReaderManager exists", adapter.getCyTableReaderManager());
	} 

	@Test
	public void testCyNetworkViewWriterManager() { 
		assertNotNull("CyNetworkViewWriterManager exists", adapter.getCyNetworkViewWriterManager());
	} 

	@Test
	public void testCyPropertyWriterManager() { 
		assertNotNull("CyPropertyWriterManager exists", adapter.getCyPropertyWriterManager());
	} 

	@Test
	public void testCySessionWriterManager() { 
		assertNotNull("CySessionWriterManager exists", adapter.getCySessionWriterManager());
	} 

	@Test
	public void testCyTableWriterManager() { 
		assertNotNull("CyTableWriterManager exists", adapter.getCyTableWriterManager());
	} 

	@Test
	public void testPresentationWriterManager() { 
		assertNotNull("PresentationWriterManager exists", adapter.getPresentationWriterManager());
	} 

	@Test
	public void testCyServiceRegistrar() { 
		assertNotNull("CyServiceRegistrar exists", adapter.getCyServiceRegistrar());
	} 

}
