package org.cytoscape.service.util.internal.utils;

/*
 * #%L
 * Cytoscape Service API (service-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 The Cytoscape Consortium
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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Various utilities for registering services. 
 */
public class RegisterUtil {

	private static final Logger logger = LoggerFactory.getLogger(RegisterUtil.class);

	public static List<Class<?>> getAllInterfaces(Class<?> clazz) {
		Set<Class<?>> interfaces = new HashSet<Class<?>>();
		addAllInterfaces(interfaces, clazz);
		return new ArrayList<Class<?>>(interfaces);
	}
	
	private static void addAllInterfaces(Set<Class<?>> list, Class<?> clazz) {
		for ( Class<?> c : clazz.getInterfaces() ) {
			list.add(c);
			addAllInterfaces(list, c);
		}

		// recurse into superclass
		Class<?> superClass = clazz.getSuperclass();
		if ( superClass != null && superClass != Object.class )
			addAllInterfaces(list, superClass);
	}
}
