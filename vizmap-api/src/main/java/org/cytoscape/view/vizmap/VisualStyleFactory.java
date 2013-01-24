package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


/**
 * Factory for {@linkplain VisualStyle}s.  This object will be provided as an OSGi service.
 * @CyAPI.Api.Interface
 */
public interface VisualStyleFactory {
	
	/**
	 * Create a new {@linkplain VisualStyle}.
	 * 
	 * @param title Title of the visual style.  This can be null, but in that case, 
	 * 					default title will be used.
	 * 			Note: This is NOT an identifier of this object, just a title.
	 *
	 * @return New VisualStyle object.
	 */
	VisualStyle createVisualStyle(final String title);
	
	
	/**
	 * Create a copy of given {@linkplain VisualStyle}.
	 *
	 * @param original
	 *            VisualStyle to be copied from.
	 *
	 * @return New VisualStyle copied from the original.
	 * 
	 */
	VisualStyle createVisualStyle(final VisualStyle original);

}
