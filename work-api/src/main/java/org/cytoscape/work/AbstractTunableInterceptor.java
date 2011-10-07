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
 * Interceptor for Tunables : detect them, create an appropriate <code>Handler</code> from the <code>HandlerFactory</code> for each of them, and store them in a HashMap for further use.
 *
 * <p><pre>
 * <b>example :</b>
 * <code>
 * public class Test{
 * 	<code>@Tunable(description="Number of Modules", group={"General Parameters"})</code>
 * 	public BoundedInteger numMod = new BoundedInteger(0,5,1000,false,false);
 * 	<code>@Tunable(description="Overlap Threshold", group={"General Parameters"})</code>
 * 	public BoundedDouble overlap = new BoundedDouble(0.0,0.8,1.0,false,false);
 * 	<code>@Tunable(description="Adjust for size?", group={"General Parameters"})</code>
 * 	public boolean adjustForSize = true;
 * }
 * </code></pre></p>
 *
 * <p><pre>
 * Here are the steps to get a list of handlers for each object annotated as a<code> @Tunable </code>, in order to provide :
 * <ul>
 * 	<li>a Graphic User Interface for <code>Tunables</code> in the Cytoscape Desktop(use of a <code>GuiTunableInterceptor</code>)</li>
 * 	<li>a Command-line interface to execute the Tasks by just typing the name of the class implementing the <code>TaskFactory</code> interface (use of <code>CLTunableInterceptor</code>)</li>
 * 	<li>access to the properties of the <code>Tunables</code>(use of <code>LoadPropsInterceptor</code> or <code>StorePropsInterceptor</code></li>
 * </ul>
 *
 * <ol>
 * 	<li>Detection of a field or getter method annonated with <code>@Tunable </code> in the class the <code>TunableInterceptor</code> is applied to</li>
 * 	<li>The <code>Handlers</code> are created for each kind of <code>Tunable</code> Object (In
 *          this example : creation of a <code>AbstractBounded<Integer></code>, <code>AbstractBounded&lt;Double&gt;</code> and <code>Boolean</code>  <code>Handlers<code>)</li>
 * 	<li>The <code>Handlers</code> are stored in a <i>handlerList</i>, and are used by different <code>TunableInterceptor</code> types</li>
 * 	<li>Create a GUI, provide command-line options, store or load properties for those <code>Tunables</code> by using those <code>TunableHandler</code>s</li>
 * </ol>
 * </pre></p>
 *
 * @param <TH>  <code>TunableHandler</code>s created in the factory
 */
public abstract class AbstractTunableInterceptor<TH extends TunableHandler> implements TunableInterceptor<TH> {
	private boolean throwException;


	/**
	 *  Store the Handlers
	 */
	protected final Map<Object, LinkedHashMap<String, TH>> handlerMap;

	/**
	 *  Store the JPanel-returning methods
	 */
	protected final Map<Object, Method> guiProviderMap;

	/**
	 * A list of TunableHandlerFactory services that have been registered.
	 */
	protected final List<TunableHandlerFactory<TH>> tunableHandlerFactories;

	private final static Logger logger = LoggerFactory.getLogger(AbstractTunableInterceptor.class);

	/**
	 * Creates a new AbstractTunableInterceptor object.
	 */
	public AbstractTunableInterceptor() {
		throwException = false;
		handlerMap = new HashMap<Object, LinkedHashMap<String, TH>>();
		guiProviderMap = new HashMap<Object, Method>();
		tunableHandlerFactories = new ArrayList<TunableHandlerFactory<TH>>();
	}

	/** Used for testing only! */
	void setThrowExceptions(final boolean throwException) {
		this.throwException = throwException;
	}

	/**
	 *  To detect fields and methods annotated with <code>Tunable</code>, create a <code>Handler</code> for
	 *  each from the factory, and store it in <code>handlerMap</code>.
	 *  In addition we also detect methods annotated with <code>ProvidesGUI</code> and store those in
	 *  <code>guiProviderMap</code>.
	 *
	 *  @param obj A class that contains <code>Tunable</code> that need to be caught to interact with the users
	 */
	public void loadTunables(final Object obj) {
		if (!handlerMap.containsKey(obj)) {
			LinkedHashMap<String, TH> handlerList = new LinkedHashMap<String, TH>();

			// Find each public field in the class.
			for (final Field field : obj.getClass().getFields()) {
				// See if the field is annotated as a Tunable.
				if (field.isAnnotationPresent(Tunable.class)) {
					try {
						// Get the tunable's annotations
						final Tunable tunable = field.getAnnotation(Tunable.class);

						// Get a Handler for this type of Tunable and...
						TH handler = getHandler(field, obj, tunable);

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
   						final TH handler = getHandler(method, setter, obj, tunableMap.get(rootName));
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
	 *  To get the Map of the <code>Handlers</code> that are contained in this <code>TunableInterceptor</code> Object applied to an external Object(class).
	 *
	 * @param o The Object the TunableInterceptor has been applied to.
	 *
	 * @return  The map that contains all the <code>Handlers</code> that have been created for the Object o
	 */
	public final Map<String, TH> getHandlers(final Object o) {
		if (o == null)
			return null;

		return handlerMap.get(o);
	}

	/** Tests an object for having tunable annotations.
	 *
	 *  @return true if "o" has tunable annotations and else false.
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
	
	private TH getHandler(Field field, Object instance, Tunable tunable) {
		for ( TunableHandlerFactory<TH> thf : tunableHandlerFactories ) {
			TH th = thf.getHandler(field, instance, tunable);
			if ( th != null )
				return th;
		}
		return null;
	}
	
	private TH getHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
		for ( TunableHandlerFactory<TH> thf : tunableHandlerFactories ) {
			TH th = thf.getHandler(getter, setter, instance, tunable);
			if ( th != null )
				return th;
		}
		return null;
	}
	
	/**
	 * Allows TunableHandlerFactory services to be added to the list of factories used to process Tunables.
	 * @param thf The factory to be added.
	 * @param properties OSGi service metadata.  May be null.
	 */
	public void addTunableHandlerFactory(TunableHandlerFactory<TH> thf, Map properties) {
		if ( thf != null )
			tunableHandlerFactories.add(thf);
	}

	/**
	 * Allows TunableHandlerFactory services to be removed from the list of factories used to process Tunables.
	 * @param thf The factory to be removed.
	 * @param properties OSGi service metadata.  May be null.
	 */
	public void removeTunableHandlerFactory(TunableHandlerFactory<TH> thf, Map properties) {
		tunableHandlerFactories.remove(thf);
	}

	private final void logOrThrowException(final String msg, Throwable ex) {
		if (throwException)
			throw new IllegalArgumentException(msg, ex);
		else
			logger.warn(msg, ex);
	}
}
