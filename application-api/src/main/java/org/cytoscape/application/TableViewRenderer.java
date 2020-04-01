package org.cytoscape.application;

import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyTableViewFactory;
import org.cytoscape.view.presentation.RenderingEngineFactory;

public interface TableViewRenderer {

	// Will we need more contexts?
	static final String DEFAULT_CONTEXT = "";
	
	RenderingEngineFactory<CyTable> getRenderingEngineFactory(String contextId);
	CyTableViewFactory getTableViewFactory();
	
	String getId();
	
}
