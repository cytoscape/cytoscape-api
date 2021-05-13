package org.cytoscape.service.util;

/*
 * #%L
 * Cytoscape Service API (service-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;


import java.util.Dictionary;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
		
		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class))).thenReturn(s2); 
		when(bc.registerService(eq(DummyInterface.class.getName()), same(service), any(Dictionary.class))).thenReturn(s); 
		
		Properties props = mock(Properties.class);
		activator.registerAllServices(bc, service, props);
		assertEquals(2, activator.getServiceRegistrations().size());
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));

	}
	
	@Test
	public void testRegisterService (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();
		
		final String DUMMY_KEY = "dummyKey";
		final String DUMMY_VALUE = "dummyValue";
		props.put(DUMMY_KEY, DUMMY_VALUE);
		
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		doAnswer(new Answer<ServiceRegistration>() {
			public ServiceRegistration answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				assertTrue(args[2] instanceof Dictionary);
				Dictionary dictionary = (Dictionary) args[2];
				assertEquals(1, dictionary.size());
				assertEquals(DUMMY_VALUE, dictionary.get(DUMMY_KEY));
				return s2;
			}
		}).when(bc).registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class));
			
		activator.registerService(bc, service, DummyInterface2.class, props);
		assertEquals(1, activator.getServiceRegistrations().size());
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));

	}
	
	@Test
	public void testStop (){
		
		DummyClass2 service = new DummyClass2();
		Properties props = new Properties();

		ServiceRegistration s = mock(ServiceRegistration.class);
		ServiceRegistration s2 = mock(ServiceRegistration.class);
		when(bc.registerService(eq(DummyInterface2.class.getName()), same(service), any(Dictionary.class))).thenReturn(s2); 
		when(bc.registerService(eq(DummyInterface.class.getName()), same(service), any(Dictionary.class))).thenReturn(s); 
		
		activator.registerAllServices(bc, service, props);
		assertTrue(activator.getServiceRegistrations().get(DummyInterface2.class).get(service).equals(s2));
		assertTrue(activator.getServiceRegistrations().get(DummyInterface.class).get(service).equals(s));
	
		activator.stop(bc);
		assertTrue(activator.getServiceRegistrations().isEmpty());
	}
}
