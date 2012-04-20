package org.cytoscape.service.util.internal.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * Providing a set of functionalities used in common by multiple classes for services.  
 * @author rozagh
 *
 */
public final class ServiceUtil {
	
	private ServiceUtil(){
		
	}

	/**
	 * A method that attempts to get a service of the specified type. If an 
	 * appropriate service is not found, an exception will be thrown.
	 * @param <S> The generic type of the class defining the type of service desired.
	 * @param bc The BundleContext used to find services.
	 * @param serviceClass The class defining the type of service desired.
	 * @param gottenServices A reference to the list of services that has been already gotten, 
	 * The returned service will be added to this list if the list is not null.
	 * @return A reference to a service of type serviceClass.
	 * @throws RuntimeException If the requested service can't be found.
	 */
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

	/**
	 * A method that attempts to get a service of the specified type and that
	 * passes the specified filter. If an appropriate service is not found, an 
	 * exception will be thrown.
	 * @param <S> The generic type of the class defining the type of service desired.
	 * @param bc The BundleContext used to find services.
	 * @param serviceClass The class defining the type of service desired.
	 * @param filter The string defining the filter the service must pass.  See OSGi's 
	 * service filtering syntax for more detail.
	 * @param gottenServices A reference to the list of services that has been already gotten, 
	 * The returned service will be added to this list if the list is not null.
	 * @return A reference to a service of type serviceClass that passes the specified filter.
	 * @throws RuntimeException If the requested service can't be found.
	 */
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
	
	
	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or
	 * unregistered. 
	 * @param bc The BundleContext used to find services.
	 * @param listener Your object listening for service registrations.
	 * @param registerMethodName The name of the method to be called when a service is registered.
	 * @param unregisterMethodName The name of the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 * @param methodClass There are situations where, because of the use of generic and type
	 * erasure that the serviceClass is a subclass of the class used by the registration method,
	 * in which case, this extra argument allows that class to be specified. 
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 * @param serviceListeners A reference to the list of service listeners that has been already registered, 
	 * The given service listener will be added to this list if the list is not null.
	 */
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
	

	/**
	 * A utility method that registers the specified service object as an OSGi service of
	 * the specified type.
	 * @param bc The BundleContext used to find services.
	 * @param service The object to be registered as one or more services.
	 * @param serviceClass The class defining the type of service to be registered.
	 * @param props The service properties to be registered with each service. 
	 * @param serviceRegistrations A reference to the map of the classes and the objects that register the services. 
	 * The service registered by this class will be added to the map if the reference is not null.
	 */
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
	/**
	 * This method registers an object as an OSGi service
	 * for all interfaces that the object implements
	 * and with the specified properties. Note that this method
	 * will NOT register services for any packages with names that
	 * begin with "java", which is an effort to avoid registering
	 * meaningless services for core Java APIs.
	 * @param bc The BundleContext used to find services.
	 * @param service The object to be registered as one or more services.
	 * @param props The service properties to be registered with each service. 
	 * @param serviceRegistrations A reference to the map of the classes and the objects that register the services. 
	 * The service registered by this class will be added to the map if the reference is not null.
	 */
	public static void registerAllServices(final BundleContext bc, final Object service, final Properties props, 
			Map<Class,Map<Object,ServiceRegistration>> serviceRegistrations) {
		for ( Class c : RegisterUtil.getAllInterfaces(service.getClass()) ) {
			if ( !c.getName().startsWith("java") ) 
				registerService(bc, service, c, props, serviceRegistrations);
		}
	}


}
