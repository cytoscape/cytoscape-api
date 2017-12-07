package org.cytoscape.service.util;

/*
 * #%L
 * Cytoscape Service API (service-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;import java.util.Dictionary;
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
	
	
//	@Test
//	public void testRegisterAllServices (){
//		
//		DummyClass2 service = new DummyClass2();
//		Dictionary props = mock(Dictionary<K, V>.c)
//
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		ServiceRegistration s2 = mock(ServiceRegistration.class);
//		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
//		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
//		
//		activator.registerAllServices(bc, service, props);
//		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
//		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
//
//	}
//	
//	@Test
//	public void testRegisterService (){
//		
//		DummyClass2 service = new DummyClass2();
//		Properties props = new Properties();
//
//		ServiceRegistration s2 = mock(ServiceRegistration.class);
//		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
//		
//		activator.registerService(bc, service, DummyInterface2.class, props);
//		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
//
//	}
//	
//	@Test
//	public void testStop (){
//		
//		DummyClass2 service = new DummyClass2();
//		Properties props = new Properties();
//
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		ServiceRegistration s2 = mock(ServiceRegistration.class);
//		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
//		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
//		
//		activator.registerAllServices(bc, service, props);
//		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
//		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
//	
//		activator.stop(bc);
//		assertTrue(activator.getServiceRegistrations().isEmpty());
//	}
}
