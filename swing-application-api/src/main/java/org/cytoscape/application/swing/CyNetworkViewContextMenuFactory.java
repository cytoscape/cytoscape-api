package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import org.cytoscape.view.model.CyNetworkView;

/**
 * A factory interface used to produce a CyMenuItem (JMenuItem) that
 * will be added to the context menu for the specified network view. 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyNetworkViewContextMenuFactory {

    /**
     * This method should return a CyMenuItem to be added to the context
     * menu of the specified network view.
     * @param netView The CyNetworkView whose menu will be updated with the returned CyMenuItem.
     * @return The CyMenuItem to be added to the network view's context menu.
     */
	CyMenuItem createMenuItem(CyNetworkView netView);
}
