package org.cytoscape.view.presentation.customgraphics;

import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a {@link CustomGraphicLayer} that
 * paints an image on a node. 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface ImageCustomGraphicLayer extends CustomGraphicLayer {
	/**
 	 * Return the {@link java.awt.TexturePaint} to be used as
 	 * an image to fill a node
 	 *
 	 * @param bounds the bounding box of the {@link org.cytoscape.model.CyNode}.
 	 * @return the fill {@link java.awt.TexturePaint}
 	 */
	@Override
	public TexturePaint getPaint(Rectangle2D bounds);
}
