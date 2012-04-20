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
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
		
		sri.registerAllServices(service, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));

	}
	
	@Test
	public void testRegisterService (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		
		sri.registerService(service, DummyInterface2.class, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));

	}
	
	@Test
	public void testUnRegisterService (){
		
		DummyClass service = new DummyClass();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
		
		sri.registerService(service, DummyInterface.class, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
		
		sri.unregisterService(service, DummyInterface.class);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service)== null);

	}
	
	@Test
	public void testUnRegisterAllServices (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
		
		sri.registerAllServices(service, props);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
	
		sri.unregisterAllServices(service);
		assertTrue(sri.getServiceRegistrations().get(DummyInterface.class).isEmpty());
		assertTrue(sri.getServiceRegistrations().get(DummyInterface2.class).isEmpty());
	}
}
