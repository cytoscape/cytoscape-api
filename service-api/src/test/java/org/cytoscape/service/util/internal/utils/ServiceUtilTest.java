package org.cytoscape.service.util.internal.utils;

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


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class ServiceUtilTest {
	BundleContext bc;
	ServiceReference<Object> rf;
	
	@Before
	public void init(){
		bc = mock(BundleContext.class);
		rf = mock(ServiceReference.class);
	}
	
	
//	@Test
//	public void testGetService(){
//		
//		Object result = mock(Object.class);
//		when(bc.getServiceReference(Object.class.getName())).thenReturn(rf);
//		when(bc.getService(rf)).thenReturn(result);
//		
//		Object S = ServiceUtil.getService(bc, Object.class, (List<ServiceReference<?>>) mock(List.class));
//		
//		assertEquals(result, S);
//	
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testGetService_Exception(){
//		Object result = mock(Object.class);
//		when(bc.getServiceReference(Object.class.getName())).thenReturn(null);
//		when(bc.getService(rf)).thenReturn(result);
//		Object S = ServiceUtil.getService(bc, Object.class, (List<ServiceReference<?>>) mock(List.class));
//		
//	}
//
//	@Test
//	public void testGetService_list(){
//		
//		Object result = mock(Object.class);
//		when(bc.getServiceReference(Object.class.getName())).thenReturn(rf);
//		when(bc.getService(rf)).thenReturn(result);
//		List<ServiceReference<?>> list = new ArrayList<ServiceReference<?>>();
//		Object S = ServiceUtil.getService(bc, Object.class, list);
//		
//		assertTrue(list.get(0).equals(rf));
//	
//	}
//	
//	//===========================================
//	
//	@Test
//	public void testGetServiceWithFilter() throws InvalidSyntaxException{
//		
//		Object result = mock(Object.class);
//		String filter = "dummy";
//		ServiceReference[] refs = new ServiceReference[]{mock(ServiceReference.class), mock(ServiceReference.class)};
//		when(bc.getServiceReferences(Object.class.getName(), filter)).thenReturn(refs);
//		when(bc.getService(refs[0])).thenReturn( result);
//		
//		Object S = ServiceUtil.getService(bc, Object.class, filter, (List<ServiceReference<?>>) mock(List.class));
//		
//		assertEquals(result, S);
//	
//	}
//	
//	@Test(expected=RuntimeException.class)
//	public void testGetServiceWithFilter_Exception() throws InvalidSyntaxException{
//		
//		String filter = "dummy";
//		//ServiceReference[] refs = new ServiceReference[]{mock(ServiceReference.class), mock(ServiceReference.class)};
//		when(bc.getServiceReferences(Object.class.getName(), filter)).thenReturn(null);
//
//		Object S = ServiceUtil.getService(bc, Object.class, filter, (List<ServiceReference<?>>) mock(List.class));
//	}
//	
//	@Test
//	public void testGetServiceWithFilter_List() throws InvalidSyntaxException{
//		
//		Object result = mock(Object.class);
//		String filter = "dummy";
//		ServiceReference[] refs = new ServiceReference[]{mock(ServiceReference.class), mock(ServiceReference.class)};
//		when(bc.getServiceReferences(Object.class.getName(), filter)).thenReturn(refs);
//		when(bc.getService(refs[0])).thenReturn(result);
//		List<ServiceReference<?>> list = new ArrayList<ServiceReference<?>>();
//		
//		Object S = ServiceUtil.getService(bc, Object.class, filter, list);
//		
//		assertTrue(list.get(0).equals(refs[0]));
//	}
//	
//	//==========================================
//	
//	@Test(expected=RuntimeException.class)
//	public void TestRegisterServiceListener(){
//		
//		ServiceUtil.registerServiceListener(bc, mock(Object.class), "", "", Object.class, Object.class, "dummy filter" ,  (( List<CyServiceListener>)mock(List.class)));
//		
//	}
//	
//	//==========================================
//	
//	@Test
//	public void testRegisterService (){
//		
//		Object service = mock(Object.class);
//		Dictionary props = mock (Diction.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>();
//		sr.put(Object.class, registrations);
//		
//		ServiceUtil.registerService(bc, service, Object.class, props, sr);
//		
//		assertTrue(registrations.get(service).equals(s));
//	}
//	
//	@Test(expected=NullPointerException.class)
//	public void testRegisterService_ServiceNull (){
//		
//		Object service = null; // mock(Object.class);
//		Properties props = mock (Properties.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>();
//		sr.put(Object.class, registrations); 
//		
//		ServiceUtil.registerService(bc, service, Object.class, props, sr);	
//	}
//	
//	@Test(expected=NullPointerException.class)
//	public void testRegisterService_ServiceClassNull (){
//		
//		Object service = mock(Object.class);
//		Properties props = mock (Properties.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>();  
//		sr.put(Object.class, registrations); 
//		
//		ServiceUtil.registerService(bc, service, null, props, sr);	
//	}
//	
//	@Test(expected=NullPointerException.class)
//	public void testRegisterService_PropsNull (){
//		
//		Object service = mock(Object.class);
//		Properties props = null; // mock (Properties.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>(); 
//		sr.put(Object.class, registrations); 
//		
//		ServiceUtil.registerService(bc, service, Object.class, props, sr);	
//	}
//	
//	@Test(expected=IllegalStateException.class)
//	public void testRegisterService_BCNull (){
//		
//		Object service = mock(Object.class);
//		Properties props =  mock (Properties.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>(); 
//		sr.put(Object.class, registrations); 
//		
//		ServiceUtil.registerService(null, service, Object.class, props, sr);	
//	}
//	
//	@Test
//	public void testRegisterService_EmptySR (){
//		
//		Object service = mock(Object.class);
//		Properties props = mock (Properties.class);
//		Map<Class, Map<Object,ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		when(bc.registerService(Object.class.getName(), service, props)).thenReturn(s);
//		//Map<Object, ServiceRegistration> registrations = new HashMap<Object, ServiceRegistration>();
//		//sr.put(Object.class, registrations); 
//		
//		ServiceUtil.registerService(bc, service, Object.class, props, sr);
//		
//		assertTrue(sr.get(Object.class).get(service).equals(s));
//	}
//	
//	//===================================================
//	
//	private interface DummyInterface {
//	}
//	private class DummyClass implements DummyInterface{
//		
//	}
//	
//	private interface DummyInterface2 extends DummyInterface{
//		
//	}
//
//	private class DummyClass2 implements DummyInterface2{
//		
//	}
//	
//	@Test
//	public void testRegisterAllServices(){
//		
//		DummyClass2 service = new DummyClass2();
//		Properties props = mock (Properties.class);
//		Map<Class, Map<Object, ServiceRegistration>> sr =  new HashMap<Class, Map<Object,ServiceRegistration>>();
//		ServiceRegistration s = mock(ServiceRegistration.class);
//		ServiceRegistration s2 = mock(ServiceRegistration.class);
//		when(bc.registerService(DummyInterface2.class.getName(), service, props)).thenReturn(s2); 
//		when(bc.registerService(DummyInterface.class.getName(), service, props)).thenReturn(s); 
//		
//		ServiceUtil.registerAllServices(bc, service, props, sr);
//		assertTrue(sr.get(DummyInterface2.class).get(service).equals(s2));
//		assertTrue(sr.get(DummyInterface.class).get(service).equals(s));
//
//	}
//	
}

