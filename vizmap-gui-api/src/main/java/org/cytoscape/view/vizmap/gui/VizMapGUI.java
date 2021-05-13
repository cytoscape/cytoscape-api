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

import javax.swing.JPanel;


/**
 * <p>
 * The top-level GUI component of VizMap GUI.
 * <p>
 * VizMapGUI consists of three parts:
 * <ul>
 * 	<li>Visual Style switch (combo box)
 *  <li>Default View Panel
 *  <li>Property Sheet
 * </ul>
 * <p>
 * Since there is no "current Visual Style" in the vizmap api, the replacement for 
 * "current VS" is "selected visual style" managed here.
 * <p>
 * This component tracks the selected Visual Style.
 * Because of this, Visual Style is editable even if current Network View does not exist.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
@Deprecated
public interface VizMapGUI {
	
	/**
	 * Returns Default View Editor.
	 * Default View Editor is immutable.
	 * 
	 * @return default view editor
	 */
	@Deprecated
	DefaultViewEditor getDefaultViewEditor();
	
	
	/**
	 * Returns Default View Panel.
	 * This panel is immutable.
	 * 
	 * @return Default view on {@link JPanel}.
	 */
	@Deprecated
	JPanel getDefaultViewPanel();
}
