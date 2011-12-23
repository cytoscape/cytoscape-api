/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.work;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * An abstract base class for TunableRecorder and TunableMutator implementations.  
 * @param <T> The generic type of this AbstractTunableInterceptor.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTunableInterceptor<T extends TunableHandler> {
	private boolean throwException;

	/**
	 *  Store the Handlers.
	 */
	protected final Map<Object, LinkedHashMap<String, T>> handlerMap;

	/**
	 *  Store the JPanel-returning methods.
	 */
	protected final Map<Object, Method> guiProviderMap;

	/**
	 * A list of TunableHandlerFactory services that have been registered.
	 */
	protected final List<TunableHandlerFactory<T>> tunableHandlerFactories;

	private final static Logger logger = LoggerFactory.getLogger(AbstractTunableInterceptor.class);

	/**
	 * Creates a new AbstractTunableInterceptor object.
	 */
	public AbstractTunableInterceptor() {
		throwException = false;
		handlerMap = new HashMap<Object, LinkedHashMap<String, T>>();
		guiProviderMap = new HashMap<Object, Method>();
		tunableHandlerFactories = new ArrayList<TunableHandlerFactory<T>>();
	}

	/** Used for testing only! */
	void setThrowExceptions(final boolean throwException) {
		this.throwException = throwException;
	}

	// TODO make this private!!!
	/**
	 * To detect fields and methods annotated with {@link Tunable}, create 
	 * a {@link TunableHandler} for each from the factory, and store it in handlerMap.
	 *
	 * @param obj A class that contains fields or methods annotated with {@link Tunable} 
	 * whose value needs to be set or recorded. 
	 */
	protected void loadTunables(final Object obj) {
		if (!handlerMap.containsKey(obj)) {
			final LinkedHashMap<String, T> handlerList = new LinkedHashMap<String, T>();

			// Find each public field in the class.
			for (final Field field : obj.getClass().getFields()) {				
				// See if the field is annotated as a Tunable.
				if (field.isAnnotationPresent(Tunable.class)) {
					try {
						// Get the tunable's annotations
						final Tunable tunable = field.getAnnotation(Tunable.class);

						// Get a Handler for this type of Tunable and...
						T handler = getHandler(field, obj, tunable);

						// ...add it to the list of Handlers
						if (handler != null)
							handlerList.put(field.getName(), handler);
						else
							logOrThrowException("No handler for type: " + field.getType().getName(), null);
					} catch (final Throwable ex) {
						logOrThrowException("tunable field intercept failed for "
							    + field.toString(), ex);
					}
				}
			}

			Map<String, Method> getMethodsMap = new HashMap<String,Method>();
			Map<String, Tunable> tunableMap = new HashMap<String,Tunable>();

			guiProviderMap.clear();
			
			// Find each public method in the class.
			for (final Method method : obj.getClass().getMethods()) {
				// See if the method is annotated as a Tunable.
   				if (method.isAnnotationPresent(Tunable.class)) {

   					final Tunable tunable = method.getAnnotation(Tunable.class);
   					if (method.getName().startsWith("get")) {
   						if (!isValidGetter(method)) {
   							throw new IllegalArgumentException("Invalid getter method specified \"" + method.getName()
   									+ "\", maybe this method takes arguments or returns void?");
   						}

   						final String rootName = method.getName().substring(3);
   						getMethodsMap.put(rootName, method);
   						tunableMap.put(rootName, tunable);

   						final Class getterReturnType = method.getReturnType();
   						final Method setter = findCompatibleSetter(obj, rootName, getterReturnType);
   						if (setter == null) {
   							throw new IllegalArgumentException("Can't find a setter compatible with the "
   									+ method.getName() + "() getter!");
   						}

   						// Get a handler with for get and set methods:
   						final T handler = getHandler(method, setter, obj, tunableMap.get(rootName));
   						if (handler == null) {
   							logOrThrowException("Failed to create a handler for " + setter.getName() + "()!",null);
   						} else {
   							handlerList.put("getset" + rootName, handler);
   						}
   					} else {
   						throw new IllegalArgumentException("the name of the method has to start with \"get\" but was "
   								+ method.getName() + "()!");
   					}

   				// See if the method is annotated as providing a GUI...
				} else if (method.isAnnotationPresent(ProvidesGUI.class)) {
					if (!isJPanelOrJPanelDescendent(method.getReturnType())) {
						throw new IllegalArgumentException(method.getName() + " annotated with @ProvidesGUI must return JPanel!");
					} else if (method.getParameterTypes().length != 0) {
						throw new IllegalArgumentException(method.getName() + " annotated with @ProvidesGUI must take 0 arguments!");
					} else {
						if (!guiProviderMap.isEmpty())
							throw new IllegalArgumentException("Classes must have at most a single @ProvidesGUI annotated method but + "
							        + method.getDeclaringClass().getName() + " has more than one!");
						guiProviderMap.put(obj, method);
					}
				}
			}

			handlerMap.put(obj, handlerList);
		}
	}

	private boolean isJPanelOrJPanelDescendent(final Class c) {
		Class c0 = c;
		while (c0 != null && c0 != Object.class) {
			if (c0 == JPanel.class)
				return true;
			c0 = c0.getSuperclass();
		}

		return false;
	}

	private boolean isValidGetter(final Method getterCandidate) {
		// Make sure we're not returning "void":
		try {
			final Type returnType = getterCandidate.getGenericReturnType();
			if (returnType == Void.class)
				return false;
		} catch(final Exception e) {
			return false;
		}

		// Make sure we're not taking any arguments:
		return getterCandidate.getParameterTypes().length == 0;
	}

	private Method findCompatibleSetter(final Object obj, final String rootName, final Class getterReturnType) {
		try {
			return obj.getClass().getMethod("set" + rootName, getterReturnType);
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * Returns a map of {@link TunableHandler} objects that have been found to process
	 * the fields and methods annotated with {@link Tunable}. The keys in the map are
	 * the names of the fields and methods that were annotated.
	 * @param o The object whose fields and methods will be searched for {@link Tunable} 
	 * annotations. 
	 * @return The map that contains all the {@link TunableHandler} objects that have been 
	 * found to process the fields and methods annotated with {@link Tunable}.
	 */
	public final Map<String, T> getHandlers(final Object o) {
		if (o == null)
			return null;

		// this is a no-op if tunables have already been loaded
		loadTunables(o);

		return handlerMap.get(o);
	}

	/** 
	 * Tests an object to see if any of its fields or method have {@link Tunable} annotations.
	 * @param o The object whose fields and methods should be evaluated for {@link Tunable} 
	 * annotations.
	 * @return true if "o" has fields or methods annotated with {@link Tunable} otherwise false.
	 */
	public boolean hasTunables(final Object o) {
		for (final Field field : o.getClass().getFields()) {
			if (field.isAnnotationPresent(Tunable.class))
				return true;
		}
		for (final Method method : o.getClass().getMethods()) {
			if (method.isAnnotationPresent(Tunable.class) || method.isAnnotationPresent(ProvidesGUI.class))
				return true;
		}

		return false;
	}
	
	private T getHandler(final Field field, Object instance, Tunable tunable) {		
		for ( TunableHandlerFactory<T> thf : tunableHandlerFactories ) {
			T th = thf.createTunableHandler(field, instance, tunable);
			if ( th != null )
				return th;
		}
		return null;
	}
	
	private T getHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
		for ( TunableHandlerFactory<T> thf : tunableHandlerFactories ) {
			T th = thf.createTunableHandler(getter, setter, instance, tunable);
			if ( th != null ) {
				return th;
			}
		}
		return null;
	}
	
	/**
	 * Allows TunableHandlerFactory services to be added to the list of factories used 
	 * to process Tunables.
	 * @param thf The factory to be added.
	 * @param properties OSGi service metadata.  May be null.
	 */
	public void addTunableHandlerFactory(TunableHandlerFactory<T> thf, Map properties) {		
		if ( thf != null )
			tunableHandlerFactories.add(thf);
	}

	/**
	 * Allows TunableHandlerFactory services to be removed from the list of factories used 
	 * to process Tunables.
	 * @param thf The factory to be removed.
	 * @param properties OSGi service metadata.  May be null.
	 */
	public void removeTunableHandlerFactory(TunableHandlerFactory<T> thf, Map properties) {
		tunableHandlerFactories.remove(thf);
	}

	private final void logOrThrowException(final String msg, Throwable ex) {
		if (throwException)
			throw new IllegalArgumentException(msg, ex);
		else
			logger.debug(msg, ex);
	}
}
