package org.cytoscape.service.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class AbstractCyActivatorTest {

	private interface DummyInterface {
	}
	private class DummyClass implements DummyInterface{
		
	}
	
	private interface DummyInterface2 extends DummyInterface{
		
	}

	private class DummyClass2 implements DummyInterface2{
		
	}
	
	private class DummyActivator extends AbstractCyActivator{

		@Override
		public void start(BundleContext context) throws Exception {			
		}
		
	}
	DummyActivator activator;
	BundleContext bc;
	@Before
	public void testCyServiceRegistrarImpl(){
		bc = mock(BundleContext.class);
		activator = new DummyActivator();
	}
	
	
	@Test
	public void testRegisterAllServices (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
		
		activator.registerAllServices(bc, service, props);
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));

	}
	
	@Test
	public void testRegisterService (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		
		activator.registerService(bc, service, DummyInterface2.class, props);
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));

	}
	
	@Test
	public void testStop (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
		
		activator.registerAllServices(bc, service, props);
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
	
		activator.stop(bc);
		assertTrue(activator.getServiceRegistrations().isEmpty());
	}
}
