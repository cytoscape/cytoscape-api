
package org.cytoscape.service.util.internal;


import java.util.Map;
import java.util.HashMap;
import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleContext; 
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;

public class CyServiceRegistrarImpl  implements CyServiceRegistrar{
	
	//private static final Logger logger = LoggerFactory.getLogger(CyServiceRegistrarImpl.class);
	
	private final BundleContext bc;
	private Map<Class,Map<Object,ServiceRegistration>> serviceRegistrations;
	
	public CyServiceRegistrarImpl(BundleContext bc) {
		this.bc = bc;
		serviceRegistrations = new HashMap<Class,Map<Object,ServiceRegistration>>();
	}

	public void registerAllServices(Object o, Properties props) {
		for ( Class c : RegisterUtil.getAllInterfaces(o.getClass()) ) {
			if ( !c.getName().startsWith("java") ) 
				registerService(o,c,props);
		}
	}

	public void registerService(Object service, Class serviceClass, Properties props) {
		
		ServiceUtil.registerService(bc, service, serviceClass, props, serviceRegistrations);
		
	}


	public void unregisterAllServices(Object o) {
		for ( Class c : RegisterUtil.getAllInterfaces(o.getClass()) ) {
			if ( !c.getName().startsWith("java") ) { 
				unregisterService(o,c);
			}
		}
	}


	public void unregisterService(Object o, Class c) {
		if ( o == null )
			throw new NullPointerException( "service object is null" );
		if ( c == null )
			throw new NullPointerException( "class is null" );

		//logger.debug("attempting to UNregister service: " + o.toString() + " of type " + c.getName());

		Map<Object, ServiceRegistration> registrations = serviceRegistrations.get(c);
		if ( registrations == null )
			return;
		
		ServiceRegistration s = registrations.get(o);

		if ( s == null )
			return;

		s.unregister();

		serviceRegistrations.get(c).remove(o);
	}

	@Override
	public <S> S getService(Class<S> serviceClass) {
		
		return ServiceUtil.getService(bc, serviceClass, null);
		
	}


	@Override
	public <S> S getService(Class<S> serviceClass, String filter) {
	
		
		return ServiceUtil.getService(bc, serviceClass, filter, null);
	}

	@Override
	public void registerServiceListener(Object listener,
		String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, Class<?> methodClass, String additionalFilter) {
	
		ServiceUtil.registerServiceListener(bc, listener, registerMethodName, unregisterMethodName, serviceClass, methodClass, additionalFilter, null);
	}

	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass,null);
		
	}

	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, String additionalFilter) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass, additionalFilter);
		
	}

	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, Class<?> methodClass) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,methodClass,null);
		
	}

	


}
