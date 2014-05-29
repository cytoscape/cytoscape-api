package org.cytoscape.view.presentation.customgraphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface Java2DLayer extends CustomGraphicLayer {

	void draw(Graphics2D g, Rectangle2D area);
	
}
