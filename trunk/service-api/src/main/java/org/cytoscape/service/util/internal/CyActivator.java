

package org.cytoscape.service.util.internal;


import org.cytoscape.service.util.CyServiceRegistrar;
import org.osgi.framework.BundleContext;
import org.cytoscape.service.util.AbstractCyActivator;
import java.util.Properties;


public class CyActivator extends AbstractCyActivator {
	public CyActivator() {
		super();
	}

	public void start(BundleContext bc) {
		CyServiceRegistrarImpl cyServiceRegistrar = new CyServiceRegistrarImpl(bc);
		registerService(bc,cyServiceRegistrar,CyServiceRegistrar.class, new Properties());
	}
}

