package org.cytoscape.application.swing;

import java.awt.Component;

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

/**
 * An interface that allows a component to be registered as a service
 * that will then be added to the ToolBar.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface ToolBarComponent {

	/**
	 * Returns the gravity used to place this component in the toolbar.
	 * @return The gravity used to place this component in the toolbar.
	 */
	float getToolBarGravity();
	
	/**
	 * Returns the Component to be added to the ToolBar. 
	 * @return The Component to be added to the ToolBar. 
	 */
	Component getComponent();

}
