package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation designed to signal that the annotated field contains
 * fields and methods that are annotated with the {@link Tunable} annotation.
 * The goal is to allow rich tunable collections to be created by
 * combining the tunables found in fields annotated with ContainsTunables. 
 * <br/>
 * For example, given classes A and B:
 * <p><pre><code>
 *
 * public class A {
 *    &#64;Tunable
 *    public int value = 5;
 * }
 *
 * public class B {
 *    &#64;ContainsTunable
 *    public A a = new A(); 
 *    &#64;Tunable
 *    public String name = "homer";
 * }
 *
 * </code></pre></p>
 * When class B is evaluated for Tunables, it should display a tunable
 * for both the "name" and "value" fields.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ContainsTunables {
}
