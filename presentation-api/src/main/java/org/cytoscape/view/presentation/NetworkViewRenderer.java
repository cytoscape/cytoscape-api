package org.cytoscape.view.presentation;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkViewFactory;

public interface NetworkViewRenderer {
	static final String DEFAULT_CONTEXT = "";
	static final String BIRDS_EYE_CONTEXT = "birdsEye";
	static final String VISUAL_STYLE_PREVIEW_CONTEXT = "visualStylePreview";
	
	RenderingEngineFactory<CyNetwork> getRenderingEngineFactory(String contextId);
	CyNetworkViewFactory getNetworkViewFactory();
}
