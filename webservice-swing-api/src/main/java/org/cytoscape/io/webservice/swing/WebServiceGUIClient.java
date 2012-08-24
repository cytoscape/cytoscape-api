package org.cytoscape.io.webservice.swing;

import java.awt.Container;

/** 
 * An interface that allows web service clients to provide
 * their own GUI component.
 * 
 * @CyAPI.Spi.Interface
 */
public interface WebServiceGUIClient {

	/**
	 * Returns query builder UI. Since this is a TaskFactory,
	 * createTaskIterator() method should use parameters from this GUI.
	 * @return query builder UI.
	 */
	Container getQueryBuilderGUI();

}
