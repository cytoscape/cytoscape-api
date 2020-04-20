package org.cytoscape.application;

import org.cytoscape.view.model.table.CyTableViewFactory;
import org.cytoscape.view.presentation.TableRenderingEngineFactory;

/**
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 */
public interface TableViewRenderer {

	static final String DEFAULT_CONTEXT = "";
	
	TableRenderingEngineFactory getRenderingEngineFactory(String contextId);
	CyTableViewFactory getTableViewFactory();
	
	String getId();
}
