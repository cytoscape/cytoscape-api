
package org.cytoscape.service.util;

import java.util.Dictionary;

/** 
 * An interface to hide the OSGi dependencies needed to register 
 * services dynamically at runtime.  You should only use this interface
 * if you need to register services while running based on data not 
 * available at startup. 
 */
public interface CyServiceRegistrar {

	/**
	 * This method registers an object as an OSGi service
	 * with the specified service interface and properties.
	 * @param o The object to be registered as a service.
	 * @param c The service interface the object should be registered as. 
	 * @param props The service properties. 
	 */
	void registerService(Object o, Class c, Dictionary props);

	/**
	 * This method unregisters an object as an OSGi service
	 * for the specified service interface. 
	 * @param o The object to be unregistered as a service.
	 * @param c The service interface the object should be unregistered as. 
	 */
	void unregisterService(Object o, Class c);

	/**
	 * This method registers an object as an OSGi service
	 * for all interfaces that the object implements
	 * and with the specified properties.
	 * @param o The object to be registered as a service for all
	 * interfaces that the object implements.
	 * @param props The service properties. 
	 */
	void registerAllServices(Object o, Dictionary props);

	/**
	 * This method unregisters an object as all OSGi service
	 * interfaces that the object implements.
	 * @param o The object to be unregistered for services it provids.
	 */
	void unregisterAllServices(Object o);
}
