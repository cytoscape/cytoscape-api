package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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
import java.awt.Component;


/**
 * Interface to a CytoPanel.
 *
 * @author Ben Gross.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CytoPanel {

	/**
	 * Returns the number of components in the CytoPanel.
	 *
	 * @return int Number of components.
	 */
	public int getCytoPanelComponentCount();

	/**
	 * Returns the currently selected component.
	 *
	 * @return component Currently selected CytoPanelComponent reference.
	 */
	public Component getSelectedComponent();

	/**
	 * Returns the currently selected index.
	 *
	 * @return index Currently selected index.
	 */
	public int getSelectedIndex();

	/**
	 * Returns the component at the passed index.
	 * 
	 * @param index the index of the component, which must be non-negative and less than the component count.
	 * @return component at the given index.
	 * 
	 * @exception IndexOutOfBoundsException if index is out of range {@code (index < 0 || index >= component count)}
	 * @see #getCytoPanelComponentCount()
	 */
	public Component getComponentAt(int index);
	
	/**
	 * Gets the state of the CytoPanel.
	 *
	 * @return A CytoPanelState.
	 */
	public CytoPanelState getState();

	/**
	 * Returns the index for the specified component.
	 *
	 * @param component Component reference.
	 * @return int      Index of the Component or -1 if not found.
	 */
	public int indexOfComponent(Component component);

	/**
	 * Returns the index for the component that has the specified identifier.
	 * The identifier must be the same one provided by {@link CytoPanelComponent2#getIdentifier()}.
	 * <br>
	 * These are the identifiers of the core Cytoscape components that implement {@link CytoPanelComponent2}:
	 * <ul>
	 *   <li><code>"org.cytoscape.Network"</code></li>
	 *   <li><code>"org.cytoscape.Style"</code></li>
	 *   <li><code>"org.cytoscape.Filter"</code></li>
	 * </ul>
	 * 
	 * @param identifier The String that identifies the component.
	 * @return int Index of the Component or -1 if not found.
	 */
	public int indexOfComponent(String identifier);
	
	/**
	 * Sets the selected index on the CytoPanel.
	 *
	 * @param index The desired index.
	 */
	public void setSelectedIndex(int index);

	/**
	 * Sets the state of the CytoPanel.
	 *
	 * @param cytoPanelState A CytoPanelState.
	 */
	public void setState(CytoPanelState cytoPanelState);

	/**
	 * Returns the position in the layout.
	 *
	 * @return The CytoPanelName enum value indicating the position in the layout.
	 */
	public CytoPanelName getCytoPanelName();

	/**
	 * Returns the Component that is this CytoPanel.  This is a hack to allow
	 * CytoPanel to be used as a Component.
	 * @return the Component that is this CytoPanel.
	 */
	public Component getThisComponent();
}
