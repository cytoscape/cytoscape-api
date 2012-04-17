package org.cytoscape.application.events;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CyStartEventTest {

	@Test
	public void testGetSource() {
		Integer i = new Integer(1);
		CyStartEvent e = new CyStartEvent(i);
		assertEquals( i, e.getSource() ); 
	}

	@Test
	public void testGetListenerClass() {
		Object i = new Object(); 
		CyStartEvent e = new CyStartEvent(i);
		assertEquals( CyStartListener.class, e.getListenerClass() ); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		new CyStartEvent(null);
	}

}
