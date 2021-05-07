package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


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
	private final static Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
	
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
