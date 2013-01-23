package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.util.concurrent.atomic.AtomicLong;

/**
 * This singleton class returns unique, positive SUID (session unique ID) values. 
 * @CyAPI.Static.Class
 * @CyAPI.InModule model-api
 */
public abstract class SUIDFactory {

	private SUIDFactory() { }

	// Maintain thread-safety by CAS, not by locking.
	private static final AtomicLong count = new AtomicLong(1);

	/**
	 * Returns the next available SUID.
	 * @return the next available SUID.
	 */
	public static long getNextSUID() {
		return count.incrementAndGet();
	}
}
