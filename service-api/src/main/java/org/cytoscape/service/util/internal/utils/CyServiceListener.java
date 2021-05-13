package org.cytoscape.service.util.internal.utils;

import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

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

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CyServiceListener<S> extends ServiceTracker {

	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
	
	private final BiConsumer<S,Map<String,String>> registerConsumer;
	private final BiConsumer<S,Map<String,String>> unregisterConsumer;
	private final Class<S> serviceClass;
	
	public CyServiceListener(BundleContext bc, Object target, String registerMethodName, String unregisterMethodName, Class<S> serviceClass, Class<?> methodClass, String additionalFilter) throws NoSuchMethodException {
		super(bc, genFilter(bc, serviceClass, additionalFilter), null);
		this.registerConsumer   = new MethodRefectionConsumer<>(target, registerMethodName, serviceClass, methodClass, true);
		this.unregisterConsumer = new MethodRefectionConsumer<>(target, unregisterMethodName, serviceClass, methodClass, false);
		this.serviceClass = serviceClass;
	}
	
	public CyServiceListener(BundleContext bc, BiConsumer<S,Map<String,String>> registerConsumer, BiConsumer<S,Map<String,String>> unregisterConsumer, Class<S> serviceClass, String additionalFilter) {
		super(bc, genFilter(bc, serviceClass, additionalFilter), null);
		this.registerConsumer   = Objects.requireNonNull(registerConsumer);
		this.unregisterConsumer = Objects.requireNonNull(unregisterConsumer);
		this.serviceClass = serviceClass;
	}
	

	private static class MethodRefectionConsumer<S> implements BiConsumer<S,Map<String,String>> {

		private final boolean register;
		private final Method method;
		private final Object target;
		private final Class<?> serviceClass;
		private final Class<?> methodClass;
		
		MethodRefectionConsumer(Object target, String methodName, Class<S> serviceClass, Class<?> methodClass, boolean register) throws NoSuchMethodException {
			this.target = target;
			this.serviceClass = serviceClass;
			this.methodClass = methodClass;
			this.method = getMethod(methodName);
			this.register = register;
		}
		
		@Override
		public void accept(S service, Map<String,String> properties) {
			try {
				method.invoke(target, serviceClass.cast(service), properties);
			} catch (Exception e) {
				e.printStackTrace();
				logger.warn("Failed to " + (register ? "register" : "unregister") + " service: ", e);
			}
		}
		
		/**
		 * Tries the different possible method declarations.
		 */
		private Method getMethod(String name) throws NoSuchMethodException {
			try {
				return target.getClass().getMethod(name, methodClass, Dictionary.class);
			} catch (NoSuchMethodException e) {
				// Ignore exception and try different signature.
				// If we throw an exception here, we WANT it to 
				// propagate, because that signals an error.
				return target.getClass().getMethod(name, methodClass, Map.class);
			}
		}
	}
	

	private static Filter genFilter(BundleContext context, Class<?> serviceClass, String additionalFilter) {
		// create class filter
		String filter = "(" + Constants.OBJECTCLASS + "=" + serviceClass.getName() + ")";

		// append any additional filter
		if ( additionalFilter != null && !additionalFilter.isEmpty() )
			filter = "(&" + filter + additionalFilter + ")";

		// create OSGi filter
		try {
			return context.createFilter(filter);
		} catch (InvalidSyntaxException e) {
			IllegalArgumentException iae = new IllegalArgumentException( "Invalid filter syntax: " + filter, e);
			iae.initCause(e);
			throw iae;
		}
	}

	/**
	 * Invokes the register method on the listener target class with the specified service.
	 */
	@Override
	public Object addingService(ServiceReference ref) {
		Object service = super.addingService(ref);
		registerConsumer.accept(serviceClass.cast(service), getProperties(ref));
		return service;
	}

	/**
	 * Invokes the UNregister method on the listener target class with the specified service.
	 */
	@Override
	public void removedService(ServiceReference ref, Object service) {
		super.removedService(ref, service);
		unregisterConsumer.accept(serviceClass.cast(service), getProperties(ref));
	}

	/**
	 * Converts the service properties contained in a ServiceReference to a Properties object.
	 */
	private Map<String,String> getProperties(ServiceReference ref) {
		// The old style service listeners supported both Dictionary and Map for the second argument. 
		// So we must use Hashtable because it extends Dictionary and implements Map.
		Hashtable<String,String> props = new Hashtable<>();
		for(String key : ref.getPropertyKeys()) 
			props.put(key, ref.getProperty(key).toString());
		return props;
	}
	
}
