package org.cytoscape.application.events;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.*;

import org.cytoscape.application.CyApplicationManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CyShutdownEventTest {


	@Mock
	private CyApplicationManager source;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetSource() {
		CyShutdownEvent e = new CyShutdownEvent(source);
		assertEquals( source, e.getSource() ); 
	}

	@Test
	public void testGetListenerClass() {
		Object i = new Object(); 
		CyShutdownEvent e = new CyShutdownEvent(i);
		assertEquals( CyShutdownListener.class, e.getListenerClass() ); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		new CyShutdownEvent(null);
	}
	
	@Test
	public void testGoodCyShutdownEvent() {
		
		final CyShutdownEvent e = new CyShutdownEvent(source);
		assertNull(e.abortShutdownReason()); //reason should be initially null
	}

	@Test
	public void testAbortShutdownWithReason(){
		String r = "dummy reason";
		final CyShutdownEvent e = new CyShutdownEvent(source);
		e.abortShutdown(r);
		assertEquals(e.abortShutdownReason(), r);

	}
	
	@Test
	public void testAbortShutdownEmptyReason(){
		String r = "";
		final CyShutdownEvent e = new CyShutdownEvent(source);
		e.abortShutdown(r);
		assertNull(e.abortShutdownReason()); 
	}
	
	@Test
	public void testAbortShutdownNullReason(){
		final CyShutdownEvent e = new CyShutdownEvent(source);
		e.abortShutdown(null);
		assertNull(e.abortShutdownReason()); 
	}
	
	@Test
	public void testActuallyShutdown(){
		final CyShutdownEvent e = new CyShutdownEvent(source);
		assertTrue(e.actuallyShutdown()); 
		e.abortShutdown("dummy reason");
		assertFalse(e.actuallyShutdown());
	}

}
