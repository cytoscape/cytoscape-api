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
 * {@link CyRow},
 * with a couple of extra semantics, including the addition of support for
 * CyJobData as a potential type.  Since CyJobData supports a {@link Map}-like 
 * framework, the additional of CyJobData as a supported type allows for easy
 * serialization of Maps, Lists, and data, which aligns very well with JSON,
 * for example.
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
	 * @return the raw data, if that key exists or null
	 */
	public Object getRaw(String key);

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
	public boolean isSet(String key);

	/**
	 * Return all of the values in this CyJobData
	 *
	 * @return a {@link Map} of all of the data in this CyJobData object.
	 */
	public Map<String, Object> getAllValues();

	/**
	 * Get the data for a particular key.  Note that if the type of the data
	 * isn't assignable from type, this will throw an exception.
	 *
	 * @param key the key to use to access the data
	 * @param type the class of the data
	 * @return the data
	 * @throws IllegalArgumentException if the type of the data isn't assignable to type
	 */
	public <R> R getData(String key, Class<? extends R> type) throws IllegalArgumentException;

	/**
	 * Get List data for a particular key.  Note that if the type of the list
	 * element data isn't assignable from listElementType, this will throw an exception.
	 *
	 * @param key the key to use to access the data
	 * @param listElementType the class of the data elements in the list
	 * @return a list with all of the data elements
	 * @throws IllegalArgumentException if the type of the list elements isn't assignable to listElementType
	 */
	public <R> List<R> getListData(String key, 
	                               Class<? extends R> listElementType) throws IllegalArgumentException;

	/**
	 * Set the value for a particular key.
	 *
	 * @param key the key to set the value for
	 * @param value the value to set
	 */
	public <R> void setData(String key, R value);

	/**
	 * Remove the data for a key
	 *
	 * @param key the key to remove the data for
	 */
	public void removeData(String key);

	/**
	 * Return the serialized object for transmission to the remote execution.
	 *
	 * @return serialized object
	 */
	public Object getSerializedObject();
}
