package org.cytoscape.work.properties;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2015 The Cytoscape Consortium
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

import java.util.Properties;

import org.cytoscape.work.Tunable;


/**
 * This is a type of tunable interceptor that converts the values
 * annotated with {@link Tunable} annotations to and from a Properties object.
 * The generated Properties can be used with <code>CyProperty&lt;Properties&gt;</code> 
 * to save the values.
 * <br><br>
 * Example: 
 * 
 * <pre><code>
 * public class Foo {
 *     &commat;Tunable public int x = 5;
 * }
 * 
 * public class Bar {
 *     &commat;ContainsTunables public Foo foo = new Foo();
 *     &commat;Tunable public int y = 10;
 * }
 * 
 * Properties props = tunablePropertySerializer.toProperties(new Bar());
 * </code></pre>
 * 
 * Produces the following key/value pairs:
 * 
 * <pre><code>
 * foo.x=5
 * y=10
 * </code></pre>
 * 
 * Values are converted to and from Strings using available {@link TunablePropertyHandler}s.
 * By default the following types are supported:
 * <ul>
 * <li>"Basic" types
 *   <ul>
 *   <li>Strings</li>
 *   <li>Enums</li>
 *   <li>primitive types (eg: int, double) </li>
 *   <li>wrappers for primitive types (eg: java.lang.Integer, java.lang.Double)</li>
 *   </ul></li>
 * <li>ListSingleSelection&lt;T&gt; where T is a Basic type</li>
 * <li>ListMultipleSelection&lt;T&gt; where T is a Basic type</li>
 * <li>BoundedFloat</li>
 * <li>BoundedDouble</li>
 * <li>BoundedInteger</li>
 * <li>BoundedLong</li>
 * </ul>
 *
 * Support for additional types can be provided by registering a {@link TunablePropertyHandlerFactory} OSGi service.
 * 
 * <br><br>
 * Note: Implementations of this interface are typically not thread safe. 
 * Use {@link TunablePropertySerializerFactory} to create thread-local instances.
 * <br><br>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TunablePropertySerializer {
	
	
	/**
	 * Takes the properties and applies them to the tunables in the object.
	 * Each property is converted from a String into a value of the 
	 * type of the tunable. A {@link TunablePropertyHandler} of the appropriate
	 * type must be available.
	 * 
	 * @param objectWithTunables Object with &commat;Tunable annotations.
	 * @param properties Properties object.
	 * @throws IllegalArgumentException If any of the property values cannot be parsed or contain an illegal value.
	 */
	void setTunables(Object objectWithTunables, Properties properties); 
	
	/**
	 * Returns a Properties object where each property key is a qualified
	 * field name and the property value is the result of applying
	 * a {@link TunablePropertyHandler} to the tunable value.
	 * 
	 * @param objectWithTunables Object with &commat;Tunable annotations.
	 * @return Properties object.
	 */
	Properties toProperties(Object objectWithTunables);
}
