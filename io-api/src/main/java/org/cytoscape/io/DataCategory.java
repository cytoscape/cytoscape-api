package org.cytoscape.io;

/*
 * #%L
 * Cytoscape IO API (io-api)
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

/**
 * An enum that captures the types of data the IO package can read and write.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule io-api
 */
public enum DataCategory {
	/** 
	* Data to be import/export is NETWORK
	*/ 
	NETWORK("Network"), 
	
	/** 
	* Data to be import/export is Table
	*/ 
	TABLE("Table"), 
	
	/** 
	* Data to be import/export is Image
	*/ 
	IMAGE("Image"), 
	
	/** 
	* Data to be import/export is Properties
	*/ 
	PROPERTIES("Properties"), 
	
	/** 
	* Data to be import/export is CySession
	*/ 
	SESSION("Session"), 
	
	/** 
	 * Data to be import/export is VizMap
	 */ 
	VIZMAP("Style"), 
	
	/**
	 * Data to be imported is Script file
	 */
	SCRIPT("Script"),
	
	/**
	 * Data to be exported is an archive file
	 */
	ARCHIVE("Archive"),
	
	/** 
	* Data to be import/export is not specified (unknown)
	*/ 
	UNSPECIFIED("Unspecified");
	
	
	private final String displayName;

	private DataCategory(final String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
