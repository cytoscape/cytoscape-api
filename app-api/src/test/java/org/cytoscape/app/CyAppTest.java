
package org.cytoscape.app;


import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CyAppTest {
	private static class MyApp extends AbstractCyApp {
		MyApp(CyAppAdapter a) {
			super(a);
		}
	}
	
	@Test
	public void testGoodAdapter() { 
		CyAppAdapter ad = mock(CyAppAdapter.class);
		MyApp m = new MyApp(ad);
		assertEquals(m.adapter,ad);
	}

	@Test(expected=NullPointerException.class)
	public void testNullAdapter() { 
		MyApp m = new MyApp(null);
	}
}
