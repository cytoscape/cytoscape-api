package org.cytoscape.application.swing;


/**
 * This class provides access to registered CyColumnPresentation objects.
 * 
 * @see org.cytoscape.model.CyColumn
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyColumnPresentationManager {

	/**
	 * Returns a column presentation associated with the given namespace.
	 * @return A CyColumnPresentation for the given namespace. If null is given then the
	 * default presentation used for Cytoscape columns is returned. If a CyColumnPresentation
	 * has not been registered for the given namespace then a dummy CyColumnPresentation
	 * is returned. Does not return null.
	 */
	CyColumnPresentation getColumnPresentation(String namespace);
	
}
