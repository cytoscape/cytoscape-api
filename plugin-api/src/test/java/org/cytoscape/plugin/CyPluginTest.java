
package org.cytoscape.plugin;


import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CyPluginTest {
	private static class MyPlugin extends CyPlugin {
		MyPlugin(CyPluginAdapter a) {
			super(a);
		}
	}
	
	@Test
	public void testGoodAdapter() { 
		CyPluginAdapter ad = mock(CyPluginAdapter.class);
		MyPlugin m = new MyPlugin(ad);
		assertEquals(m.adapter,ad);
	}

	@Test(expected=NullPointerException.class)
	public void testNullAdapter() { 
		MyPlugin m = new MyPlugin(null);
	}
}
