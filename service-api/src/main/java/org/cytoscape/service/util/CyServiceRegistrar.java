
package org.cytoscape.service.util;

import java.util.Properties;

import org.osgi.framework.BundleContext;

/** 
 * An interface to hide the OSGi dependencies needed to register 
 * services dynamically at runtime.  You should only use this interface
 * if you need to register services while running based on data not 
 * available at startup. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule service-api
 */
public interface CyServiceRegistrar {


	/**
	 * A method that attempts to get a service of the specified type. If an 
	 * appropriate service is not found, an exception will be thrown.
	 * @param <S> The generic type of the class defining the type of service desired.
	 * @param serviceClass The class defining the type of service desired.
	 * @return A reference to a service of type serviceClass.
	 * @throws RuntimeException If the requested service can't be found.
	 */
	 <S> S getService( Class<S> serviceClass);
	 
	 /**
		 * A method that attempts to get a service of the specified type and that
		 * passes the specified filter. If an appropriate service is not found, an 
		 * exception will be thrown.
		 * @param <S> The generic type of the class defining the type of service desired.
		 * @param serviceClass The class defining the type of service desired.
		 * @param filter The string defining the filter the service must pass.  See OSGi's 
		 * service filtering syntax for more detail.
		 * @return A reference to a service of type serviceClass that passes the specified filter.
		 * @throws RuntimeException If the requested service can't be found.
		 */
	<S> S getService( Class<S> serviceClass, String filter);

	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or
	 * unregistered. 
	 * @param listener Your object listening for service registrations.
	 * @param registerMethodName The name of the method to be called when a service is registered.
	 * @param unregisterMethodName The name of the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 * @param methodClass There are situations where, because of the use of generics and type
	 * erasure that the serviceClass is a subclass of the class used by the registration method,
	 * in which case, this extra argument allows that class to be specified. 
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 */
	void registerServiceListener( final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final Class<?> methodClass, final String additionalFilter);

	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or
	 * unregistered. 
	 * @param listener Your object listening for service registrations.
	 * @param registerMethodName The name of the method to be called when a service is registered.
	 * @param unregisterMethodName The name of the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 */
	void registerServiceListener( final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass);
	
	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or
	 * unregistered. 
	 * @param listener Your object listening for service registrations.
	 * @param registerMethodName The name of the method to be called when a service is registered.
	 * @param unregisterMethodName The name of the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 */
	void registerServiceListener( final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final String additionalFilter);
	
	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or
	 * unregistered. 
	 * @param listener Your object listening for service registrations.
	 * @param registerMethodName The name of the method to be called when a service is registered.
	 * @param unregisterMethodName The name of the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 * @param methodClass There are situations where, because of the use of generics and type
	 * erasure that the serviceClass is a subclass of the class used by the registration method,
	 * in which case, this extra argument allows that class to be specified. 
	 */
	void registerServiceListener(final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final Class<?> methodClass);
	
	/**
	 * This method registers an object as an OSGi service
	 * with the specified service interface and properties.
	 * @param service The object to be registered as a service.
	 * @param serviceClass The service interface the object should be registered as. 
	 * @param props The service properties. 
	 */
	void registerService(Object service, Class<?> serviceClass, Properties props);

	/**
	 * This method unregisters an object as an OSGi service
	 * for the specified service interface. 
	 * @param service The object to be unregistered as a service.
	 * @param serviceClass The service interface the object should be unregistered as. 
	 */
	void unregisterService(Object service, Class serviceClass);

	/**
	 * This method registers an object as an OSGi service
	 * for all interfaces that the object implements
	 * and with the specified properties. Note that this method
	 * will NOT register services for any packages with names that
	 * begin with "java", which is an effort to avoid registering
	 * meaningless services for core Java APIs.
	 * @param service The object to be registered as a service for all
	 * interfaces that the object implements.
	 * @param props The service properties. 
	 */
	void registerAllServices(Object service,  Properties props);

	/**
	 * This method unregisters an object as all OSGi service
	 * interfaces that the object implements.
	 * @param service The object to be unregistered for services it provides.
	 */
	void unregisterAllServices(Object service);
}
