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
	 * Returns a column presentation associated with the given namespace, or null
	 * if no presentation exists for that namespace.
	 */
	CyColumnPresentation getColumnPresentation(String namespace);
	
}
