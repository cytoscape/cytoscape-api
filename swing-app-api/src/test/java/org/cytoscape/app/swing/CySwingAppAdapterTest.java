package org.cytoscape.app.swing;


import org.junit.Test;
import static org.junit.Assert.*;


public abstract class CySwingAppAdapterTest {
	protected CySwingAppAdapter adapter;

	@Test
	public void testGetCySwingApplication() { 
		assertNotNull("CySwingApplication exists", adapter.getCySwingApplication());
	} 

	@Test
	public void testDialogTaskManager() { 
		assertNotNull("DialogTaskManager exists", adapter.getDialogTaskManager());
	} 

	@Test
	public void testPanelTaskManager() { 
		assertNotNull("PanelTaskManager exists", adapter.getPanelTaskManager());
	} 

}
