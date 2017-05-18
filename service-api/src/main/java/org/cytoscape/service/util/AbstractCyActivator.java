package org.cytoscape.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

import org.cytoscape.service.util.internal.utils.CyServiceListener;
import org.cytoscape.service.util.internal.utils.ServiceUtil;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple BundleActivator with convenience methods for registering
 * OSGi services and either getting references to single services or
 * registering interest in all services of a specified type.  
 *
 * Users should extend this class and implement the start(BundleContext bc)
 * method. Methods in an AbstractCyActivator implementation should return quickly
 * and should not block for user input.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule service-api
 */
public abstract class AbstractCyActivator implements BundleActivator {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCyActivator.class);
	
	private final Map<Class,Map<Object,ServiceRegistration>> serviceRegistrations;
	private final List<CyServiceListener> serviceListeners;
	private final List<ServiceReference> gottenServices;

	/**
	 * Constructor.
	 */
	public AbstractCyActivator() {
		serviceRegistrations = new HashMap<Class,Map<Object,ServiceRegistration>>();
		serviceListeners = new ArrayList<CyServiceListener>();
		gottenServices = new ArrayList<ServiceReference>();
	}

	/**
	 * This method is provided only for testing reason and shuld not be used
	 * or overwritten anywhere else.
	 * @return the map of service registrations in this class.  
	 */
	 Map<Class,Map<Object,ServiceRegistration>> getServiceRegistrations(){
		return this.serviceRegistrations;
	}
	
	/**
	 * A default implementation of the BundleActivator.stop() method that cleans
	 * up any services registered, services gotten, or services being listened
	 * for as determined by calls to the utility methods provided by this class. If
	 * you register a service, get a service, or listen for services outside yourself
	 * using normal calls to the OSGi API, you will need to clean everything up 
	 * yourself!
	 */
	public final void stop(BundleContext bc) {
		shutDown();
		
		// unregister and clear all services registered 
		for ( Map<Object,ServiceRegistration> registrations : serviceRegistrations.values() )  {
			for ( ServiceRegistration reg : registrations.values() ) 
				reg.unregister();
			registrations.clear();
		}
		serviceRegistrations.clear();

		// unregister and clear all service listeners
		for ( CyServiceListener listener : serviceListeners )
			listener.close();
		serviceListeners.clear();

		// unget and clear all services
		for ( ServiceReference ref : gottenServices )
			bc.ungetService(ref);
		gottenServices.clear();
	}

	/**
	 * Cleans up resources used by the app.  If the app creates threads, adds
	 * menu items via Swing, or performs any other operation that modifies the
	 * environment, this method this method should be overridden to
	 * perform any necessary clean up.
	 */
	public void shutDown() {
	}
	
	/**
	 * A method that attempts to get a service of the specified type. If an 
	 * appropriate service is not found, an exception will be thrown.
	 * @param <S> The generic type of the class defining the type of service desired.
	 * @param bc The BundleContext used to find services.
	 * @param serviceClass The class defining the type of service desired.
	 * @return A reference to a service of type serviceClass.
	 * @throws RuntimeException If the requested service can't be found.
	 */
	protected final <S> S getService(BundleContext bc, Class<S> serviceClass) {
		
		return ServiceUtil.getService(bc, serviceClass, gottenServices);
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
	 * @return A reference to a service of type serviceClass that passes the specified filter.
	 * @throws RuntimeException If the requested service can't be found.
	 */
	protected final <S> S getService(BundleContext bc, Class<S> serviceClass, String filter) {
		
		return ServiceUtil.getService(bc, serviceClass, filter, gottenServices);
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
	 * @param methodClass There are situations where, because of the use of generics and type
	 * erasure that the serviceClass is a subclass of the class used by the registration method,
	 * in which case, this extra argument allows that class to be specified. 
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 */
	protected final void registerServiceListener(final BundleContext bc, final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final Class<?> methodClass, final String additionalFilter) {
		ServiceUtil.registerServiceListener(bc, listener, registerMethodName, unregisterMethodName, serviceClass, methodClass, additionalFilter, serviceListeners);
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
	 */
	protected final void registerServiceListener(final BundleContext bc, final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass) {
		registerServiceListener(bc,listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass,null);
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
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 */
	protected final void registerServiceListener(final BundleContext bc, final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final String additionalFilter) {
		registerServiceListener(bc,listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass, additionalFilter);
	}

	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or unregistered. 
	 * 
	 * <pre>
	 * public class MyServiceListener {
	 *    public void addService(MyService s, Map<String,String> props) { ... }
	 *    public void removeService(MyService s, Map<String,String> props { ... }
	 * }
	 * 
	 * registerServiceListener(bc, myServiceListener::addService, myServiceListener::removeService, MyService.class);
	 * </pre>
	 * 
	 * @param bc The BundleContext used to find services.
	 * @param registerConsumer A reference to the method to be called when a service is registered.
	 * @param unregisterConsumer A reference to the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 */
	protected <S> void registerServiceListener(BundleContext bc, BiConsumer<S,Map<String,String>> registerConsumer, BiConsumer<S,Map<String,String>> unregisterConsumer, Class<S> serviceClass) {
		ServiceUtil.registerServiceListener(bc, registerConsumer, unregisterConsumer, serviceClass, null, serviceListeners);
	}
	
	/**
	 * A method that will cause the specified register/unregister methods on the listener
	 * object to be called any time that a service of the specified type is registered or unregistered. 
	 * 
	 * <pre>
	 * public class MyServiceListener {
	 *    public void addService(MyService s, Map<String,String> props) { ... }
	 *    public void removeService(MyService s, Map<String,String> props { ... }
	 * }
	 * 
	 * registerServiceListener(bc, myServiceListener::addService, myServiceListener::removeService, MyService.class);
	 * </pre>
	 * 
	 * @param bc The BundleContext used to find services.
	 * @param registerConsumer A reference to the method to be called when a service is registered.
	 * @param unregisterConsumer A reference to the method to be called when a service is unregistered.
	 * @param serviceClass The class defining the type of service desired.
	 * @param additionalFilter An additional filter to be applied to the OSGi services 
	 */
	protected <S> void registerServiceListener(BundleContext bc, BiConsumer<S,Map<String,String>> registerConsumer, BiConsumer<S,Map<String,String>> unregisterConsumer, Class<S> serviceClass, String additionalFilter) {
		ServiceUtil.registerServiceListener(bc, registerConsumer, unregisterConsumer, serviceClass, additionalFilter, serviceListeners);
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
	 * @param methodClass There are situations where, because of the use of generics and type
	 * erasure that the serviceClass is a subclass of the class used by the registration method,
	 * in which case, this extra argument allows that class to be specified. 
	 */
	protected final void registerServiceListener(final BundleContext bc, final Object listener, final String registerMethodName, final String unregisterMethodName, final Class<?> serviceClass, final Class<?> methodClass) {
		registerServiceListener(bc,listener,registerMethodName,unregisterMethodName,serviceClass,methodClass,null);
	}

	/**
	 * A utility method that registers the specified service object as an OSGi service for
	 * all interfaces that the object implements. Note that this method will NOT register 
	 * services for any packages with names that begin with "java", which is an effort to 
	 * avoid registering meaningless services for core Java APIs.
	 * @param bc The BundleContext used to find services.
	 * @param service The object to be registered as one or more services.
	 * @param props The service properties to be registered with each service. 
	 */
	protected final void registerAllServices(final BundleContext bc, final Object service, final Properties props) {
		
		ServiceUtil.registerAllServices(bc, service, props, serviceRegistrations);
	}
	/*
	 * A special case when no properties are defined.
	 */
	protected final void registerAllServices(final BundleContext bc, final Object service) {
		
		ServiceUtil.registerAllServices(bc, service, new Properties(), serviceRegistrations);
	}

	/**
	 * A utility method that registers the specified service object as an OSGi service of
	 * the specified type.
	 * @param bc The BundleContext used to find services.
	 * @param service The object to be registered as one or more services.
	 * @param serviceClass The class defining the type of service to be registered.
	 * @param props The service properties to be registered with each service. 
	 */
	protected final void registerService(final BundleContext bc, final Object service, final Class<?> serviceClass, final Properties props) {
		
		ServiceUtil.registerService(bc, service, serviceClass, props, serviceRegistrations); 
	}
	/*
	 * A special case when no properties are defined.
	 */
	protected final void registerService(final BundleContext bc, final Object service, final Class<?> serviceClass) {
		
		ServiceUtil.registerService(bc, service, serviceClass, new Properties(), serviceRegistrations); 
	}
	
}
