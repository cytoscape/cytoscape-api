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
 */

public interface TunableHandlerFactory<T extends TunableHandler> {
	/**
	 * This method returns a <code>TunableHandler</code> for a Field annotated as a <code>Tunable</code>
	 * if this factory can produce an appropriate handler for this type of field.  If the factory
	 * cannot produce an appropriate handler, it will return null.
	 * 
	 * @param field     Field that need to have a <code>Handler</code>
	 * @param instance  the object on  which we want to read/write the Field <code>field</code>
	 * @param tunable   Tunable that contains all the information concerning the user interface
	 * @return T       the newly constructed <code>TunableHandler</code> or null if this factory
	 * can't handle a field of the specified type.
	 */
	 T getHandler(final Field field, final Object instance, final Tunable tunable);

	/**
	 * This method returns a <code>TunableHandler</code> for a Method annotated as a <code>Tunable</code>
	 * if this factory can produce an appropriate handler for this type of field.  If the factory
	 * cannot produce an appropriate handler, it will return null.
	 * 
	 * @param getter    a Method that need to be annotated with  <code>@Tunable</code>
	 * @param setter    a Method that need to be annotated with  <code>@Tunable</code>
	 * @param instance  the object on which we want to invoke the <code>setter</code> and <code>getter</code> methods
	 * @param tunable   Tunable that contains all the information concerning the user interface
	 * @return T       the newly constructed <code>TunableHandler</code> or null if this factory
	 * can't handle a field of the specified type.
	 */
	 T getHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable);
	 
}