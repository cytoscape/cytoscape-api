
package org.cytoscape.service.util.internal;

import org.cytoscape.service.util.CyServiceRegistrar;
import java.util.Map;
import java.util.HashMap;
import java.util.Dictionary;
import org.osgi.framework.BundleContext; 
import org.osgi.framework.ServiceRegistration; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyServiceRegistrarImpl implements CyServiceRegistrar {
	
	private static final Logger logger = LoggerFactory.getLogger(CyServiceRegistrarImpl.class);
	
	private final BundleContext bc;
	private Map<Class,Map<Object,ServiceRegistration>> refs;
	
	public CyServiceRegistrarImpl(BundleContext bc) {
		this.bc = bc;
		refs = new HashMap<Class,Map<Object,ServiceRegistration>>();
	}

	public void registerAllServices(Object o, Dictionary props) {
		for ( Class c : RegisterUtil.getAllInterfaces(o.getClass()) ) {
			if ( c.getName().startsWith("java") ) 
				logger.debug("NOT registering service: " + o.toString() + " as type " + c.getName() + " because it is a core java interface.");
			else   
				registerService(o,c,props);
		}
	}

	public void registerService(Object o, Class c, Dictionary props) {
		if ( o == null )
			throw new NullPointerException( "service object is null" );
		if ( c == null )
			throw new NullPointerException( "class is null" );
		if ( props == null )
			throw new NullPointerException( "props are null" );
		if ( bc == null )
			throw new IllegalStateException( "BundleContext is null" );

		logger.debug("attempting to register service: " + o.toString() + " of type " + c.getName());
		ServiceRegistration s = bc.registerService( c.getName(), o, props );

		Map<Object, ServiceRegistration> registrations = refs.get(c);
		if ( registrations == null ) {
			registrations = new HashMap<Object,ServiceRegistration>();
			refs.put(c, registrations );
		}
		registrations.put(o,s);
	}


	public void unregisterAllServices(Object o) {
		for ( Class<?> c : o.getClass().getInterfaces() ) 
			unregisterService(o,c);
	}


	public void unregisterService(Object o, Class c) {
		if ( o == null )
			throw new NullPointerException( "service object is null" );
		if ( c == null )
			throw new NullPointerException( "class is null" );

		logger.debug("attempting to UNregister service: " + o.toString() + " of type " + c.getName());

		Map<Object, ServiceRegistration> registrations = refs.get(c);
		if ( registrations == null )
			return;
		
		ServiceRegistration s = registrations.get(o);

		if ( s == null )
			return;

		s.unregister();

		refs.get(c).remove(o);
	}
}
