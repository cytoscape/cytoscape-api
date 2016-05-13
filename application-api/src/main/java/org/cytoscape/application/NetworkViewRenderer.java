package org.cytoscape.application;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.presentation.RenderingEngineFactory;

/**
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 */
public interface NetworkViewRenderer {
	
	static final String DEFAULT_CONTEXT = "";
	static final String BIRDS_EYE_CONTEXT = "birdsEye";
	static final String VISUAL_STYLE_PREVIEW_CONTEXT = "visualStylePreview";
	static final String THUMBNAIL_CONTEXT = "thumbnail";
	
	RenderingEngineFactory<CyNetwork> getRenderingEngineFactory(String contextId);
	CyNetworkViewFactory getNetworkViewFactory();
	
	String getId();
}
