package org.cytoscape.view.presentation.charts;

import java.util.Map;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;
import org.cytoscape.view.presentation.customgraphics.CyCustomGraphics;
import org.cytoscape.view.presentation.property.values.VisualPropertyValue;

public interface CyChart<T extends CustomGraphicLayer> extends CyCustomGraphics<T>, VisualPropertyValue {

	Map<String, Object> getProperties();
	
}
