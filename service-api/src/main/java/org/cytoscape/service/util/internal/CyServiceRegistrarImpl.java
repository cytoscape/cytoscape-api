package org.cytoscape.service.util.internal;

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


import java.lang.annotation.Inherited;
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
import org.cytoscape.service.util.internal.utils.RegisterUtil;
import org.cytoscape.service.util.internal.utils.ServiceUtil;

/**
 * Implementation of {@code CyServiceRegistrar}.
 *
 * This is part of {@code service-api} instead of {@code service-impl}. This class uses code from {@code ServiceUtil},
 * which is also used by {@code AbstractCyActivator}. This class has to be
 * part of {@code service-api} so that it can use {@code ServiceUtil}.
 */
public class CyServiceRegistrarImpl  implements CyServiceRegistrar{
	
	//private static final Logger logger = LoggerFactory.getLogger(CyServiceRegistrarImpl.class);
	
	private final BundleContext bc;
	private Map<Class,Map<Object,ServiceRegistration>> serviceRegistrations;
	
	public CyServiceRegistrarImpl(BundleContext bc) {
		this.bc = bc;
		serviceRegistrations = new HashMap<Class,Map<Object,ServiceRegistration>>();
	}
	
	/**
	 * This method is provided only for testing reason and shuld not be used
	 * or overwritten anywhere else.
	 * @return the map of service registrations in this implementation class.  
	 */
	 Map<Class,Map<Object,ServiceRegistration>> getServiceRegistrations(){
		return this.serviceRegistrations;
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerAllServices(Object service, Properties props) {
		ServiceUtil.registerAllServices(bc, service, props, serviceRegistrations);
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerService(Object service, Class serviceClass, Properties props) {
		
		ServiceUtil.registerService(bc, service, serviceClass, props, serviceRegistrations);
		
	}


	/**
	 * {@inheritDoc}
	 */
	public void unregisterAllServices(Object o) {
		for ( Class c : RegisterUtil.getAllInterfaces(o.getClass()) ) {
			if ( !c.getName().startsWith("java") ) { 
				unregisterService(o,c);
			}
		}
	}


	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S> S getService(Class<S> serviceClass) {
		
		return ServiceUtil.getService(bc, serviceClass, null);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S> S getService(Class<S> serviceClass, String filter) {
	
		
		return ServiceUtil.getService(bc, serviceClass, filter, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerServiceListener(Object listener,
		String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, Class<?> methodClass, String additionalFilter) {
	
		ServiceUtil.registerServiceListener(bc, listener, registerMethodName, unregisterMethodName, serviceClass, methodClass, additionalFilter, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass,null);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, String additionalFilter) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,serviceClass, additionalFilter);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerServiceListener(Object listener,
			String registerMethodName, String unregisterMethodName,
			Class<?> serviceClass, Class<?> methodClass) {
		registerServiceListener(listener,registerMethodName,unregisterMethodName,serviceClass,methodClass,null);
		
	}

	


}
