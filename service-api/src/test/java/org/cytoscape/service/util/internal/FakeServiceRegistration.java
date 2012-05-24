
package org.cytoscape.service.util;

import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.ServiceReference;
import java.util.Dictionary;

public class FakeServiceRegistration implements ServiceRegistration {
	private final ServiceReference ref;
	public FakeServiceRegistration(ServiceReference ref) {
		this.ref = ref;
	}
	public ServiceReference getReference() {
		return ref;
	}
	public void setProperties(Dictionary properties) {}
	public void unregister() {}
}
