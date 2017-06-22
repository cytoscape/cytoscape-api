package org.cytoscape.ci;

import java.net.URI;

import org.cytoscape.ci.model.CIError;
/**
 * An interface for services that wish to provide CIError instantiation.
 * 
 * @author David Otasek (dotasek.dev@gmail.com)
 *
 */
public interface CIErrorFactory {
	
	/**
	 * This returns a CIError object, but automatically generates a link to a resource containing the error. Useful if
	 * the CIErrorFactory implementation manages logging resources.
	 * 
	 * @param status
	 * @param type
	 * @param message
	 * @return A CIError object with the passed fields and an automatically generated link resource
	 */
	public CIError getCIError(Integer status, String type, String message);
	
	/**
	 * This returns a CIError object. 
	 * 
	 * @param status
	 * @param type
	 * @param message
	 * @param link
	 * @return A CIError object with the passed fields
	 */
	public CIError getCIError(Integer status, String type, String message, URI link);
}
