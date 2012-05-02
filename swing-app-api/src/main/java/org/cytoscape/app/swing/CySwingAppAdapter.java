package org.cytoscape.app.swing;

import java.util.Properties;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.work.swing.DialogTaskManager;
import org.cytoscape.work.swing.PanelTaskManager;
import org.cytoscape.app.CyAppAdapter;


/**
 * A Java-only api providing access to Cytoscape functionality.
 * This class will provide access the various Manager and 
 * Factory interfaces defined in different API jars that are
 * normally made available to apps as OSGi services. Through
 * these interfaces developers will have access to most management
 * and creational facilities defined in the Cytoscape API.
 * This is a convenience interface intended make app development
 * as simple as possible.
 * @CyAPI.Api.Interface
 */
public interface CySwingAppAdapter extends CyAppAdapter {

	//
	// work swing api
	//

	/**
	 * Returns an instance of {@link DialogTaskManager}.
	 * @return an instance of {@link DialogTaskManager}.
	 */
	DialogTaskManager getDialogTaskManager();

	/**
	 * Returns an instance of {@link PanelTaskManager}.
	 * @return an instance of {@link PanelTaskManager}.
	 */
	PanelTaskManager getPanelTaskManager();

	//
	// swing application api
	//

	/**
	 * Returns an instance of {@link CySwingApplication}.
	 * @return an instance of {@link CySwingApplication}.
	 */
	CySwingApplication getCySwingApplication();

}
