package org.cytoscape.view.presentation;

import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.View;

public interface TableRenderingEngineFactory extends RenderingEngineFactory<CyTable> {

	@Override
	TableRenderingEngine createRenderingEngine(Object visualizationContainer, View<CyTable> viewModel);

}
