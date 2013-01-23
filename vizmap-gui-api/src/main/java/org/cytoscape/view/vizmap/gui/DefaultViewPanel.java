package org.cytoscape.view.vizmap.gui;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.RenderingEngine;

/**
 * GUI component to display default appearance of current Visual Style.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface DefaultViewPanel {

	/**
	 * Get dummy network view.
	 * Dummy network view is a network displayed on the default view editor.
	 * Usually it is a network with an edge and two nodes.
	 * 
	 * @return rendering engine object for the default view.
	 */
	RenderingEngine<CyNetwork> getRenderingEngine();

}