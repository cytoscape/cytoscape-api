package org.cytoscape.view.presentation.customgraphics;

import java.awt.Graphics2D;
import java.awt.Shape;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

public interface Cy2DGraphicLayer extends CustomGraphicLayer {

	void draw(Graphics2D g, Shape shape, CyNetworkView networkView, View<? extends CyIdentifiable> view);
	
}
