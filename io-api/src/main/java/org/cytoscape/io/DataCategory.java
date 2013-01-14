package org.cytoscape.io;

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
