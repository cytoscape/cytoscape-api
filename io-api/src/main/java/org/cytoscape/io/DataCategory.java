package org.cytoscape.io;

/*
 * #%L
 * Cytoscape IO API (io-api)
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

/**
 * An enum that captures the types of data the IO package can read and write.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule io-api
 */
public enum DataCategory {
	/** 
	* Data to be import/export is NETWORK
	*/ 
	NETWORK, 
	
	/** 
	* Data to be import/export is Table
	*/ 
	TABLE, 
	
	/** 
	* Data to be import/export is Image
	*/ 
	IMAGE, 
	
	/** 
	* Data to be import/export is Properties
	*/ 
	PROPERTIES, 
	
	/** 
	* Data to be import/export is CySession
	*/ 
	SESSION, 
	
	/** 
	 * Data to be import/export is VizMap
	 */ 
	VIZMAP, 
	
	/**
	 * Data to be imported is Script file
	 */
	SCRIPT,
	
	/** 
	* Data to be import/export is not specified (unknown)
	*/ 
	UNSPECIFIED;
}
