package org.cytoscape.property;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;


public class PropertyUpdateEventTest {
	
	CyProperty prop;
	PropertyUpdatedEvent event;
	@Before
	public void init(){
		prop = mock(CyProperty.class);
		event = new PropertyUpdatedEvent(prop);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullConstructor(){
		PropertyUpdatedEvent e = new PropertyUpdatedEvent(null);
	}
	
	@Test
	public void testGetSource(){
		assertEquals(prop, event.getSource());
	}
}
