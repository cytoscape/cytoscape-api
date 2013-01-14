package org.cytoscape.io.webservice.swing;

import java.awt.Window;

/**
 * Cytoscape's unified UI for accessing web services.  This interface provides
 * access to the Swing components used to display the UI.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule webservice-swing-api
 */
public interface WebServiceGUI {
	/**
	 * Returns the Window that contains the unified web service UI.
	 * @param webServiceClientType either NetworkImportWebServiceClient or
	 *        TableImportWebServiceClient. 
	 * @return the Window that contains the unified web service UI. 
	 */
	Window getWindow(Class<?> webServiceClientType);
}
