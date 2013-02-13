package org.cytoscape.view.presentation;

public interface NetworkViewRendererManager {
	void setCurrentNetworkViewRenderer(NetworkViewRenderer renderer);
	NetworkViewRenderer getCurrentNetworkViewRenderer();
}
