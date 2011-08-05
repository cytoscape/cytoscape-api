
package org.cytoscape.service.util.internal;

import org.cytoscape.service.util.CyServiceRegistrar;
import java.util.Map;
import java.util.HashMap;
import java.util.Dictionary;
import org.osgi.framework.BundleContext; 
import org.osgi.framework.ServiceRegistration; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.osgi.context.BundleContextAware;

public class CyServiceRegistrarImpl implements CyServiceRegistrar, BundleContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(CyServiceRegistrarImpl.class);
	
	private BundleContext bc;
	private Map<Class,Map<Object,ServiceRegistration>> refs;
	
	public CyServiceRegistrarImpl() {
		refs = new HashMap<Class,Map<Object,ServiceRegistration>>();
	}

	public void setBundleContext(BundleContext bc) {
		this.bc = bc;
	}

	public void registerAllServices(Object o, Dictionary props) {
		for ( Class c : o.getClass().getInterfaces() ) 
			registerService(o,c,props);
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

		if ( !refs.containsKey(c) )
			refs.put(c, new HashMap<Object,ServiceRegistration>() );

		refs.get(c).put(o,s);
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

		if ( !refs.containsKey(c) )
			return;
		
		ServiceRegistration s = refs.get(c).get(o);

		if ( s == null )
			return;

		s.unregister();

		refs.get(c).remove(o);
	}
}
