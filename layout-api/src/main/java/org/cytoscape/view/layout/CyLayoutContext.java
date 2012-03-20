package org.cytoscape.view.layout;

import org.cytoscape.view.model.CyNetworkView;

public interface CyLayoutContext {
	CyNetworkView getNetworkView();
	void setNetworkView(CyNetworkView networkView);
}
