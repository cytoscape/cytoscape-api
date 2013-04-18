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

import java.util.Properties;

import org.osgi.framework.BundleContext;

/** 
 * An interface to hide the OSGi dependencies needed to register 
 * services dynamically at runtime. This provides similar methods to
 * {@code AbstractCyActivator}, but you should only use {@code CyServiceRegistrar}
 * if you need to register services outside of {@code AbstractCyActivator}'s
 * {@code start} method.
 *
 * This class differs in one important way from {@code AbstractCyActivator}.
 * {@code AbstractCyActivator} maintains a list of requested services. When
 * the bundle is stopped, {@code AbstractCyActivator} releases
 * these services. {@code CyServiceRegistrar} also maintains a list
 * of requested services, but this list is separate from {@code AbstractCyActivator}.
 * Services requested through this class must be released using one of
 * the {@code unregisterService} methods when the bundle is stopped.
 *
 * @CyAPI.Api.Interface
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
