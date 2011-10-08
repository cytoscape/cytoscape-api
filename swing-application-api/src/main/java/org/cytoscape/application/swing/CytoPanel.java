/*
  File: CytoPanel.java

  Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

  This library is free software; you can redistribute it and/or modify it
  under the terms of the GNU Lesser General Public License as published
  by the Free Software Foundation; either version 2.1 of the License, or
  any later version.

  This library is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
  MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
  documentation provided hereunder is on an "as is" basis, and the
  Institute for Systems Biology and the Whitehead Institute
  have no obligations to provide maintenance, support,
  updates, enhancements or modifications.  In no event shall the
  Institute for Systems Biology and the Whitehead Institute
  be liable to any party for direct, indirect, special,
  incidental or consequential damages, including lost profits, arising
  out of the use of this software and its documentation, even if the
  Institute for Systems Biology and the Whitehead Institute
  have been advised of the possibility of such damage.  See
  the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.application.swing;


import javax.swing.*;
import java.awt.*;


/**
 * Interface to a CytoPanel.
 *
 * @author Ben Gross.
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
	 * Returns the component at index.
	 * @param index the index of the component.
	 *
	 * @return component at the given index.
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
