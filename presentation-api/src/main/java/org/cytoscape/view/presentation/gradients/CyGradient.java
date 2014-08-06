package org.cytoscape.view.presentation.gradients;

import java.util.Map;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;
import org.cytoscape.view.presentation.customgraphics.CyCustomGraphics;

public interface CyGradient<T extends CustomGraphicLayer> extends CyCustomGraphics<T> {

	Map<String, Object> getProperties();
}
