package org.cytoscape.work;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * A factory service to create a <code>TunableHandler</code> for a single type of object,
 * determined by the type of the field or the return value of the getter method in the
 * appropriate methods.  Each type of object (int, float, String) generally needs a different
 * implementation of this interface. Each implementation is expected to be registered
 * as an OSGi service. TunableInterceptors register the TunableHandlerFactory services of
 * the appropriate type. 
 *
 * @param <T> The specific type of <code>TunableHandler</code> that will be created by this factory. 
 * @CyAPI.Spi.Interface
 */

public interface TunableHandlerFactory<T extends TunableHandler> {
	/**
	 * This method returns a {@link TunableHandler} for a field annotated as a {@link Tunable}
	 * if this factory can produce an appropriate handler for this type of field.  If the factory
	 * cannot produce an appropriate handler, it will return null.
	 * 
	 * @param field     Field that needs to be handled. 
	 * @param instance  The object on which we want to read/write the Field. 
	 * @param tunable   Tunable that contains all the information concerning the user interface.
	 * @return T       The newly constructed <code>TunableHandler</code> or null if this factory
	 * can't handle a field of the specified type.
	 */
	 T createTunableHandler(final Field field, final Object instance, final Tunable tunable);

	/**
	 * This method returns a <code>TunableHandler</code> for a method annotated as a {@link Tunable}
	 * if this factory can produce an appropriate handler for this type of method.  If the factory
	 * cannot produce an appropriate handler, it will return null.
	 * <br/>
	 * Note that method annotations require both a getter and a setter method to get and set a 
	 * value. Only the getter method needs to be annotated.  The getter method must take no arguments
	 * and return a value and be named with the prefix "get".  The setter method does not need a 
	 * {@link Tunable} annotation, however the method must take a single argument of the same type as
	 * the getter method, it must return void, it must be named with the prefix "set", and the
	 * rest of the name must match that of the getter method.  See {@link Tunable} for an example.
	 * 
	 * @param getter    A getter method that has been annotated with {@link Tunable}. 
	 * @param setter    A setter method that matches the getter method. 
	 * @param instance  The object on which we want to invoke the <code>getter</code> and <code>setter</code> methods.
	 * @param tunable   The Tunable that contains all the information concerning the user interface from the getter method.
	 * @return T       The newly constructed <code>TunableHandler</code> or null if this factory
	 * can't handle the specified type.
	 */
	 T createTunableHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable);
	 
}
