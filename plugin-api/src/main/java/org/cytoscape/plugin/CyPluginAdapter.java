package org.cytoscape.plugin;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.io.read.CyNetworkReaderManager;
import org.cytoscape.io.read.CyPropertyReaderManager;
import org.cytoscape.io.read.CySessionReaderManager;
import org.cytoscape.io.read.CyTableReaderManager;
import org.cytoscape.io.write.CyNetworkViewWriterManager;
import org.cytoscape.io.write.CyPropertyWriterManager;
import org.cytoscape.io.write.CySessionWriterManager;
import org.cytoscape.io.write.PresentationWriterManager;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyTableFactory;
import org.cytoscape.model.CyTableManager;
import org.cytoscape.model.subnetwork.CyRootNetworkFactory;
import org.cytoscape.property.CyProperty;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.session.CySessionManager;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.presentation.RenderingEngineManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.swing.GUITaskManager;
import org.cytoscape.work.undo.UndoSupport;

/**
 * A Java-only api providing access to Cytoscape functionality.
 * This class will provide access the various Manager and 
 * Factory interfaces defined in different API jars that are
 * normally made available to plugins as OSGi services. Through
 * these interfaces developers will have access to most management
 * and creational facilities defined in the Cytoscape API.
 * This is a convenience interface intended make plugin development
 * as simple as possible.
 */
public interface CyPluginAdapter {

	//
	// model api
	//

	/**
	 * Returns an instance of {@link CyNetworkFactory}.
	 * @return an instance of {@link CyNetworkFactory}.
	 */
	CyNetworkFactory getCyNetworkFactory(); 

	/**
	 * Returns an instance of {@link CyTableFactory}.
	 * @return an instance of {@link CyTableFactory}.
	 */
	CyTableFactory getCyTableFactory(); 

	/**
	 * Returns an instance of {@link CyTableManager}.
	 * @return an instance of {@link CyTableManager}.
	 */
	CyTableManager getCyTableManager();

	/**
	 * Returns an instance of {@link CyRootNetworkFactory}.
	 * @return an instance of {@link CyRootNetworkFactory}.
	 */
	CyRootNetworkFactory getCyRootNetworkFactory(); 

	/**
	 * Returns an instance of {@link CyEventHelper}.
	 * @return an instance of {@link CyEventHelper}.
	 */
	CyEventHelper getCyEventHelper(); 

	/**
	 * Returns an instance of {@link CyNetworkManager}.
	 * @return an instance of {@link CyNetworkManager}.
	 */
	CyNetworkManager getCyNetworkManager(); 

	//
	// viewmodel api
	//

	/**
	 * Returns an instance of {@link CyNetworkViewFactory}.
	 * @return an instance of {@link CyNetworkViewFactory}.
	 */
	CyNetworkViewFactory getCyNetworkViewFactory();

	/**
	 * Returns an instance of {@link CyNetworkViewManager}.
	 * @return an instance of {@link CyNetworkViewManager}.
	 */
	CyNetworkViewManager getCyNetworkViewManager();

	//
	// session api
	//

	/**
	 * Returns an instance of {@link CyApplicationManager}.
	 * @return an instance of {@link CyApplicationManager}.
	 */
	CyApplicationManager getCyApplicationManager();

	/**
	 * Returns an instance of {@link CySessionManager}.
	 * @return an instance of {@link CySessionManager}.
	 */
	CySessionManager getCySessionManager();

	//
	// work api
	//

	/**
	 * Returns an instance of {@link TaskManager}.
	 * @return an instance of {@link TaskManager}.
	 */
	TaskManager getTaskManager();

	/**
	 * Returns an instance of {@link UndoSupport}.
	 * @return an instance of {@link UndoSupport}.
	 */
	UndoSupport getUndoSupport();
	
	//
	// work swing api
	//

	/**
	 * Returns an instance of {@link GUITaskManager}.
	 * @return an instance of {@link GUITaskManager}.
	 */
	GUITaskManager getGUITaskManager();

	//
	// presentation api
	//

	/**
	 * Returns an instance of {@link RenderingEngineManager}.
	 * @return an instance of {@link RenderingEngineManager}.
	 */
	RenderingEngineManager getRenderingEngineManager();

	//
	// vizmap api
	//

	/**
	 * Returns an instance of {@link VisualMappingManager}.
	 * @return an instance of {@link VisualMappingManager}.
	 */
	VisualMappingManager getVisualMappingManager();
	
	/**
	 * Returns an instance of {@link VisualStyleFactory}.
	 * @return an instance of {@link VisualStyleFactory}.
	 */
	VisualStyleFactory getVisualStyleFactory();

	//
	// layout api
	//
	/**
	 * Returns an instance of {@link CyLayouts}.
	 * @return an instance of {@link CyLayouts}.
	 */
	CyLayoutAlgorithmManager getCyLayouts();

	//
	// swing application api
	//

	/**
	 * Returns an instance of {@link CySwingApplication}.
	 * @return an instance of {@link CySwingApplication}.
	 */
	CySwingApplication getCySwingApplication();

	//
	// property api
	//
	/**
	 * Returns an instance of {@link CyProperty<Properties>}.
	 * @return an instance of {@link CyProperty<Properties>}.
	 */
	CyProperty<Properties> getCoreProperties();

	//
	// io api
	//
	/**
	 * Returns an instance of {@link CyNetworkViewReaderManager}.
	 * @return an instance of {@link CyNetworkViewReaderManager}.
	 */
	CyNetworkReaderManager getCyNetworkViewReaderManager();

	/**
	 * Returns an instance of {@link CyPropertyReaderManager}.
	 * @return an instance of {@link CyPropertyReaderManager}.
	 */
	CyPropertyReaderManager getCyPropertyReaderManager();

	/**
	 * Returns an instance of {@link CySessionReaderManager}.
	 * @return an instance of {@link CySessionReaderManager}.
	 */
	CySessionReaderManager getCySessionReaderManager();

	/**
	 * Returns an instance of {@link CyTableReaderManager}.
	 * @return an instance of {@link CyTableReaderManager}.
	 */
	CyTableReaderManager getCyTableReaderManager();

	/**
	 * Returns an instance of {@link CyNetworkViewWriterManager}.
	 * @return an instance of {@link CyNetworkViewWriterManager}.
	 */
	CyNetworkViewWriterManager getCyNetworkViewWriterManager();

	/**
	 * Returns an instance of {@link CyPropertyWriterManager}.
	 * @return an instance of {@link CyPropertyWriterManager}.
	 */
	CyPropertyWriterManager getCyPropertyWriterManager();

	/**
	 * Returns an instance of {@link CySessionWriterManager}.
	 * @return an instance of {@link CySessionWriterManager}.
	 */
	CySessionWriterManager getCySessionWriterManager();

	/**
	 * Returns an instance of {@link CyTableWriterManager}.
	 * @return an instance of {@link CyTableWriterManager}.
	CyTableWriterManager getCyTableWriterManager();
	 */

	/**
	 * Returns an instance of {@link PresentationWriterManager}.
	 * @return an instance of {@link PresentationWriterManager}.
	 */
	PresentationWriterManager getPresentationWriterManager();

	//
	// service util
	//
	/**
	 * Returns an instance of {@link CyServiceRegistrar}.
	 * @return an instance of {@link CyServiceRegistrar}.
	 */
	CyServiceRegistrar getCyServiceRegistrar();
}
