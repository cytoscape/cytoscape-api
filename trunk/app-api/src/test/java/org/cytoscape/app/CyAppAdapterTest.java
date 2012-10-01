package org.cytoscape.app;


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
