package org.cytoscape.service.util.internal;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.cytoscape.service.util.internal.utils.ServiceUtil;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CyServiceRegistrarImplTest {

	private interface DummyInterface {
	}
	private class DummyClass implements DummyInterface{
		
	}
	
	private interface DummyInterface2 extends DummyInterface{
		
	}

	private class DummyClass2 implements DummyInterface2{
		
	}
	CyServiceRegistrarImpl sri;
	BundleContext bc;
	@Before
	public void testCyServiceRegistrarImpl(){
		bc = mock(BundleContext.class);
		sri = new  CyServiceRegistrarImpl(bc);
	}
	
	
	@Test
	public void testRegisterAllServices (){
		
		DummyClass2 service = mock(DummyClass2.class);
		Properties props = mock (Properties.class);
		
		sri.registerAllServices(service, props);
		sri.unregisterAllServices(service);
		
	}
	
}
