package org.cytoscape.service.util.internal.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public final class ServiceUtil {
	
	private ServiceUtil(){
		
	}

	public static <S> S getService(BundleContext bc, Class<S> serviceClass, List<ServiceReference> gottenServices) {
		try {
			ServiceReference ref = bc.getServiceReference(serviceClass.getName());
			if ( ref == null ) 
				throw new NullPointerException("ServiceReference is null for: " + serviceClass.getName());
			if (gottenServices != null)
				gottenServices.add(ref);	
			return serviceClass.cast( bc.getService(ref) );

		} catch (Exception e) {
			throw new RuntimeException("Couldn't find service: " + serviceClass.getName(),e);
		}
	}

	public static <S> S getService(BundleContext bc, Class<S> serviceClass, String filter, List<ServiceReference> gottenServices) {
		try { 
			ServiceReference[] refs = bc.getServiceReferences(serviceClass.getName(),filter);
			if ( refs == null ) 
				throw new NullPointerException("ServiceReference is null for: " + serviceClass.getName() + " with filter: " + filter);
			if (gottenServices != null)
				gottenServices.add(refs[0]);	
			return serviceClass.cast( bc.getService(refs[0]) );
		} catch (Exception e) {
			throw new RuntimeException("Couldn't find service: " + serviceClass.getName() + " with filter: " + filter, e);
		}
	}
	
	public static void registerServiceListener(final BundleContext bc, final Object listener, final String registerMethodName, 
			final String unregisterMethodName, final Class<?> serviceClass, final Class<?> methodClass, final String additionalFilter , 
			List<CyServiceListener> serviceListeners) {
		try {
			CyServiceListener serviceListener = new CyServiceListener(bc, listener, registerMethodName, unregisterMethodName, serviceClass, methodClass, additionalFilter);
			serviceListener.open();
			if (serviceListeners != null)
				serviceListeners.add( serviceListener );
		} catch (Exception e) {
			throw new RuntimeException("Could not listen to services for object: " + listener + " with methods: " + registerMethodName + ", " + unregisterMethodName + ", service type: " + serviceClass + ", and additional filter: " + additionalFilter, e); 
		}
	}
	
	public static void registerService(final BundleContext bc, final Object service, final Class<?> serviceClass, final Properties props, 
			Map<Class,Map<Object,ServiceRegistration>> serviceRegistrations) {
		
		if ( service == null )
			throw new NullPointerException( "service object is null" );
		if ( serviceClass == null )
			throw new NullPointerException( "class is null" );
		if ( props == null )
			throw new NullPointerException( "props are null" );
		if ( bc == null )
			throw new IllegalStateException( "BundleContext is null" );

		//logger.debug("attempting to register service: " + service.toString() + " of type " + serviceClass.getName());
		ServiceRegistration s = bc.registerService( serviceClass.getName(), service, props );

		Map<Object, ServiceRegistration> registrations = serviceRegistrations.get(serviceClass);
		if ( registrations == null ) {
			registrations = new HashMap<Object,ServiceRegistration>();
			serviceRegistrations.put(serviceClass, registrations );
		}

		registrations.put(service,s);
	}

}
