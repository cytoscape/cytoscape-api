package org.cytoscape.view.presentation.customgraphics;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
