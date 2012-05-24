package org.cytoscape.service.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.BundleListener;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.Bundle;

import java.io.File;
import java.io.InputStream;
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
}

