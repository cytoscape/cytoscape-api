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

import java.security.Provider.Service;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;

public class CyServiceListenerTest {

	
	private class DummyListener{
		int counter  = 0;

		public void add (DummyServiceInterface factory, Map props){
			counter = 1;
		}
		public void remove (DummyServiceInterface factory, Map props){
			counter = -1;
		}
		
	}
	
	private interface DummyServiceInterface {
	}
	private class DummyServiceClass implements DummyServiceInterface{
		
	}

	private CyServiceListener sl ;
	BundleContext bc;
	DummyListener target;
	DummyServiceInterface service;
	ServiceReference ref;
	@Before
	public void testCyServiceListenerTest() throws Exception{
		String additionalFilter = "dummy filter";
		String filter =  "(&" + "(" + Constants.OBJECTCLASS + "=" + DummyServiceInterface.class.getName() + ")" + additionalFilter + ")";
		bc = mock(BundleContext.class);
		Filter f = mock(Filter.class);
		when(bc.createFilter(filter)).thenReturn(f);
		
		service = new  DummyServiceClass();
		ref = mock(ServiceReference.class);
		when(ref.getPropertyKeys()).thenReturn(new String[]{""});
		when(ref.getProperty("")).thenReturn(new Properties());
		when(bc.getService(ref)).thenReturn((Object) service);
		target = new DummyListener();
		
		sl = new CyServiceListener(bc, target, "add", "remove", DummyServiceInterface.class,  DummyServiceInterface.class, additionalFilter);
		
	}
	@Test 
	public void testConstructor(){
		assertTrue(sl.getRegisMethod()!= null);
		assertEquals("add",	 sl.getRegisMethod().getName());
	}

	@Test
	public void testAddingService(){
		
		assertEquals(service, sl.addingService(ref));
		assertEquals(1, target.counter);
		
	}
	
	@Test
	public void testRemovingService(){
		
		sl.removedService(ref, service);
		assertEquals(-1, target.counter);
		
	}
}
