package org.cytoscape.work;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A convenience implementation of TunableHandlerFactory that will construct a TunableHandler
 * of the specified type given the TunableHandler in question has at least two constructors, one
 * with Field, Object, Tunable parameters and the other with Method, Method, Object, Tunable parameters.  
 * If you need additional parameters to construct your TunableHandler, then
 * it is probably best to implement the TunableHandlerFactory interface directly!
 *
 * @param <T> The specific type of the TunableHandler.
 * @CyAPI.Abstract.Class 
 * @CyAPI.InModule work-api
 */
public class BasicTunableHandlerFactory<T extends TunableHandler> implements TunableHandlerFactory<T> {
	
	private final Class<T> tunableHandlerClass;
	private final Class<?>[] allowedTypes;
	private final static Logger logger = LoggerFactory.getLogger(BasicTunableHandlerFactory.class);
	
	/**
	 * Constructor.
	 * @param tunableHandlerClass The type of the TunableHandler to be created.
	 * @param allowedTypes The object types that the TunableHandler is associated with.
	 * For instance, an IntegerTunableHandler might allow Integer.class and int.class.
	 */
	public BasicTunableHandlerFactory(Class<T> tunableHandlerClass, Class<?>... allowedTypes) {
		this.tunableHandlerClass = tunableHandlerClass;
		this.allowedTypes = allowedTypes;
	}
	
	@Override
	public final T createTunableHandler(final Field field, final Object instance, final Tunable tunable) {
		if ( !properType(field.getType()) )
			return null;
	
		try {
			Constructor<T> con = tunableHandlerClass.getConstructor(Field.class, Object.class, Tunable.class);
			return con.newInstance(field, instance, tunable);
		} catch (Exception e) {
			logger.warn("Failed to construct tunable handler. Missing Field based constructor for class: " + 
					tunableHandlerClass.getName(), e);
			return null;
		}
	}

	@Override
	public final T createTunableHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
		if ( !properType(getter.getReturnType()) )
			return null;
	
		try {
			Constructor<T> con =
				tunableHandlerClass.getConstructor(Method.class, Method.class, Object.class, Tunable.class);
			return con.newInstance(getter, setter, instance, tunable);
		} catch (Exception e) {
			logger.warn("Failed to construct tunable handler. Missing Method based constructor for class: " + 
					tunableHandlerClass.getName(), e);
			return null;
		}		
	}
	
	private boolean properType(Class<?> c) {
		for ( Class<?> allowed : allowedTypes ) 
			if (allowed.isAssignableFrom(c))
				return true;
		return false;
	}
}
