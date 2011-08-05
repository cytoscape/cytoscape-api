/*
  File: CyLayoutAlgorithm.java

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
package org.cytoscape.view.layout;


import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyNode;

import javax.swing.*;
import java.util.List;
import java.util.Set;

/**
 * An extension of NetworkViewTaskFactory specific to layout algorithms.
 */
public interface CyLayoutAlgorithm extends NetworkViewTaskFactory {
	/**
	 * Tests to see if this layout supports doing a layout on a subset of the
	 * nodes in this network view.
	 *
	 * @return true if layout supports layouts on a subset of the nodes
	 */
	public boolean supportsSelectedOnly();

	/**
	 * Sets the "selectedOnly" flag
	 *
	 * @param selectedOnly boolean value that tells the layout algorithm whether to
	 * only layout the selected nodes
	 */
	public void setSelectedOnly(boolean selectedOnly);

	/**
	 * Tests to see if this layout supports doing a layout based on node attributes.
	 *
	 * @return byte array of allowable attribute types or "null" if not supported.  If the
	 *              first type is "-1", all types are supported
	 */
	public Set<Class<?>> supportsNodeAttributes();

	/**
	 * Tests to see if this layout supports doing a layout based on edge attributes.
	 *
	 * @return type array of allowable attribute types or "null" if not supported.  If the
	 *              first type is "-1", all types are supported
	 */
	public Set<Class<?>> supportsEdgeAttributes();

	/**
	 * Sets the attribute to use for node- or edge- based attribute layouts
	 *
	 * @param attributeName String with the name of the attribute to use
	 */
	public void setLayoutAttribute(String attributeName);

	/**
	 * This returns a (possibly empty) List of Strings that is used for
	 * the attribute list in the menu for attribute-based layouts.  This
	 * allows layout algorithms to provide "special" attributes.  For example,
	 * a force directed layout might want to set the list to ["(unweighted)"]
	 * to allow the user to perform an unweighted layout.  Note that this value
	 * will be set using setLayoutAttribute() just like other attributes, so the
	 * layout algorithm will need to check for it.
	 *
	 * @return List of Strings
	 */
	public List<String> getInitialAttributeList();

	/**
	 * Get the name of this layout.
	 *
	 * @return String representing the name of the layout.
	 */
	public String getName();
}
