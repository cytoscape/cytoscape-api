package org.cytoscape.view.presentation.customgraphics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public interface Cy2DGraphicLayer extends CustomGraphicLayer {

	void draw(Graphics2D g, Rectangle2D area, Shape shape);
	
}
