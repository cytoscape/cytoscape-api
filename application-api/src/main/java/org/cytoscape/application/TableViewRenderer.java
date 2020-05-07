package org.cytoscape.application;

import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.table.CyTableViewFactory;
import org.cytoscape.view.presentation.RenderingEngineFactory;

/**
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 */
public interface TableViewRenderer {

	static final String DEFAULT_CONTEXT = "";
	
	RenderingEngineFactory<CyTable> getRenderingEngineFactory(String contextId);
	CyTableViewFactory getTableViewFactory();
	
	String getId();
}
