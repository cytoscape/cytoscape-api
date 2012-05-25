package org.cytoscape.service.util.internal;

import org.osgi.framework.ServiceReference;
import org.osgi.framework.Bundle;

public class FakeServiceReference implements ServiceReference {

	private final Object obj;

	public FakeServiceReference(Object o) {
		this.obj = o;
	}
	public int compareTo(Object reference) {
		return 0;	
	}
	public Bundle getBundle() {
		return null;
	}
	public Object getProperty(String key) {
		return null;
	}
	public String[] getPropertyKeys() {
		return null;
	}
	public Bundle[] getUsingBundles() {
		return null;
	}
	public boolean isAssignableTo(Bundle bundle, String className) {
		return false;
	}
	Object getObject() {
		return obj;
	}
}
