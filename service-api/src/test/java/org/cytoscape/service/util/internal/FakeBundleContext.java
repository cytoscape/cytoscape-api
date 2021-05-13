package org.cytoscape.service.util.internal;

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
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.BundleListener;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.Bundle;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import static org.mockito.Mockito.mock;

public class FakeBundleContext implements BundleContext {
	private final Map<String,ServiceReference> refs = new HashMap<String,ServiceReference>();
	/**
	 * Creates a bundle context with no services registered.  Use registerService to
	 * register mock services.
	 */
	public FakeBundleContext() { }

	/**
	 * Creates a bundle context with a mock service registered for each
	 * Class listed in the mockServices param.
	 * @param mockServices one or more Class objects for which a mock object
	 * will be created and registered as a service of the specified type.
	 */
	public FakeBundleContext(Class<?>... mockServices) {
		for (Class<?> c : mockServices)
			registerService(c.getName(),mock(c),new Properties());
	}
	public ServiceReference getServiceReference(String className) {
		return refs.get(className);
	}
	public ServiceReference[] getServiceReferences(String className, String filter) {
		return new ServiceReference[] {refs.get(className)};
	}
	public Object getService(ServiceReference ref) {
		if ( ref instanceof FakeServiceReference )
			return ((FakeServiceReference)ref).getObject();
		else
			return null;
	}
	public ServiceRegistration registerService(String clazz, Object service, Dictionary properties) {
		FakeServiceReference ref = new FakeServiceReference(service);
		refs.put(clazz,ref);
		FakeServiceRegistration reg = new FakeServiceRegistration(ref);
		return reg;
	}
	public ServiceRegistration registerService(String[] clazzes, Object service, Dictionary properties) {
		return null;
	}
	public void addBundleListener(BundleListener listener) { }
	public void addFrameworkListener(FrameworkListener listener) { }
	public void addServiceListener(ServiceListener listener) { }
	public void addServiceListener(ServiceListener listener, String filter) { }
	public Filter createFilter(String filter) {
		return null;
	}
	public ServiceReference[] getAllServiceReferences(String clazz, String filter) {
		return null;
	}
	public Bundle getBundle() {
		return null;
	}
	public Bundle getBundle(long id) {
		return null;
	}
	public Bundle[] getBundles() {
		return null;
	}
	public File getDataFile(String filename) {
		return null;
	}
	public String getProperty(String key) {
		return null;
	}
	public Bundle installBundle(String location) {
		return null;
	}
	public Bundle installBundle(String location, InputStream input) {
		return null;
	}
	public void removeBundleListener(BundleListener listener) { }
	public void removeFrameworkListener(FrameworkListener listener) { }
	public void removeServiceListener(ServiceListener listener) { }
	public boolean ungetService(ServiceReference reference) {
		return true;
	}

	@Override
	public <S> ServiceRegistration<S> registerService(Class<S> clazz, S service, Dictionary<String, ?> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S> ServiceRegistration<S> registerService(Class<S> clazz, ServiceFactory<S> factory,
			Dictionary<String, ?> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S> ServiceReference<S> getServiceReference(Class<S> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S> Collection<ServiceReference<S>> getServiceReferences(Class<S> clazz, String filter)
			throws InvalidSyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S> ServiceObjects<S> getServiceObjects(ServiceReference<S> reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle getBundle(String location) {
		// TODO Auto-generated method stub
		return null;
	}
}

