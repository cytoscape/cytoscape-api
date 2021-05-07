package org.cytoscape.view.vizmap.gui;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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

import java.awt.Component;

import org.cytoscape.view.vizmap.VisualStyle;

/**
 * Default View Editor is the component to display available visual properties
 * and its default view for given Visual Style.
 * This component will be provided as a service.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
@Deprecated
public interface DefaultViewEditor {

	/**
	 * For a given visual style name, return a component containing
	 * a graphical view of the specified visual style.
	 * 
	 * @param vs The {@link VisualStyle} to get the default view of. 
	 * @return a component containing a graphical view of the specified visual style.
	 */
	@Deprecated
	Component getDefaultView(VisualStyle vs);

	/**
	 * Show the default view editor in the specified parent component.
	 * 
	 * @param parent The component in which the default view should be shown.
	 */
	@Deprecated
	void showEditor(Component parent);
}
