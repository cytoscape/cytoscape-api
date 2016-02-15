package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CyJobData presents a general interface to sending data to and receiving
 * data from a job.  The api is meant to be very similar to that of a 
 * {@link Map}, with {@link String} keys and arbirary {@link Object Objects}
 * as values.  Note that no type checking is done -- the values might
 * contain other {@link Map Maps}, {@link List Lists} or a variety of
 * other objects (notably Cytoscape model and view model objects).  The
 * detailed implementation for this object is managed by a {@link CyJobDataService}
 * which should be able to use the information contains within the object to
 * create the appropriate data structure to send to a remote job 
 * ({@see CyJobDataService#getSerializedData(CyJobData)}) and retrieve the
 * information from the remote job ({@see CyJobDataService#unSerialize(Object)}).
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJobData {
	/**
	 * Returns the Object that contains the value for the specified key, or null
	 * if that key doesn't exist.
	 *
	 * @param key the key to access the requested data
	 * @return the data, if that key exists or null
	 */
	public Object get(String key);

	/**
	 * Return the enumerated set of keys in this CyJobData.
	 *
	 * @return the set of keys
	 */
	public Set<String> keySet();

	/**
	 * Test to see if a key exists in the current CyJobData.
	 *
	 * @param key the key to test
	 * @return true if that key exists, false otherwise
	 */
	public boolean containsKey(String key);

	/**
	 * Return all of the values in this CyJobData
	 *
	 * @return a {@link Map} of all of the data in this CyJobData object.
	 */
	public Map<String, Object> getAllValues();

	/**
	 * Set the value for a particular key.
	 *
	 * @param key the key to set the value for
	 * @param value the value to set
	 */
	public void put(String key, Object value);

	/**
	 * Remove the data for a key
	 *
	 * @param key the key to remove the data for
	 * @return the removed object or null if the key doesn't exit
	 */
	public Object remove(String key);

	/**
	 * Clear all of the data
	 */
	public void clear();
}
