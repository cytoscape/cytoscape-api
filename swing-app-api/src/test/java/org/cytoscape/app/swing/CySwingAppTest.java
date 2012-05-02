
package org.cytoscape.app.swing;


import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CySwingAppTest {
	private static class MySwingApp extends AbstractCySwingApp {
		MySwingApp(CySwingAppAdapter a) {
			super(a);
		}
	}
	
	@Test
	public void testGoodAdapter() { 
		CySwingAppAdapter ad = mock(CySwingAppAdapter.class);
		MySwingApp m = new MySwingApp(ad);
		assertEquals(m.swingAdapter,ad);
	}

	@Test(expected=NullPointerException.class)
	public void testNullAdapter() { 
		MySwingApp m = new MySwingApp(null);
	}
}
