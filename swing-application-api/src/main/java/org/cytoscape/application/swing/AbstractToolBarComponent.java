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

/**
 * An abstract, convenience implementation of ToolBarComponent.  
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule swing-application-api
 */
public abstract class AbstractToolBarComponent implements ToolBarComponent {

	/**
	 * The gravity for this toolbar component.
	 */
	protected float toolbarGravity = 100.0f;

	/**
	 * Whether the toolbar component is enabled or not.
	 */
	protected boolean isEnabled = true;
	
	/**
	 * Sets the gravity for this toolbar component.
	 * @param gravity The gravity for this toolbar component.
	 */
	public void setToolBarGravity(float gravity) {
		toolbarGravity = gravity;
	}

	/**
	 * Returns the gravity value for this toolbar component.
	 * @return the gravity value for this toolbar component.
	 */
	@Override
	public float getToolBarGravity() {
		return toolbarGravity;
	}
}
