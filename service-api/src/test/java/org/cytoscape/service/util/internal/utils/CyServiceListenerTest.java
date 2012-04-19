package org.cytoscape.service.util.internal.utils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;

public class CyServiceListenerTest<T> {

	
	private class DummyClass{
		public void add(T factory, Map props){
			
		}
		public void remove(T factory, Map props){
			
		}
	}
	
	private CyServiceListener sl ;
	BundleContext bc;
	Object target;
	
	@Test
	public void testCyServiceListenerTest() throws Exception{
		String additionalFilter = "dummy filter";
		String filter = "(" + Constants.OBJECTCLASS + "=" + DummyClass.class.getName() + ")";
		filter = "(&" + filter + additionalFilter + ")";
		bc = mock(BundleContext.class);
		Filter f = mock(Filter.class);
		when(bc.createFilter(filter)).thenReturn(f);
		
		target = new DummyClass();
		
		sl = new CyServiceListener(bc, target, "add", "remove", DummyClass.class,  Object.class, additionalFilter);
	}
	
}
