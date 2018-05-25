package org.cytoscape.app.swing;

import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.work.swing.DialogTaskManager;
import org.cytoscape.work.swing.PanelTaskManager;


/**
 * A Swing-specific extension of {@link CyAppAdapter} that
 * serves as a Java-only api providing access to core 
 * Cytoscape functionality.
 * This class will provide access the various Swing-specific
 * Manager and Factory interfaces in addition to those defined in
 * {@link CyAppAdapter}.
 * Through these interfaces developers will have access to most management
 * and creational facilities defined in the Cytoscape API.
 * This is a convenience interface intended make app development
 * as simple as possible.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-app-api
 * 
 * @deprecated (As of Cytoscape 3.7) 
 * Support for simple apps will be removed in a future version 
 * of Cytoscape, please provide an OSGi bundle app instead.
 */
@Deprecated
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
