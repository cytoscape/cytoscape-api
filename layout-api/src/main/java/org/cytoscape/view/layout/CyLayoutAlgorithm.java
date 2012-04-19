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


import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;

/**
 * An extension of NetworkViewTaskFactory specific to layout algorithms.
 *
 * @CyAPI.Spi.Interface
 */
public interface CyLayoutAlgorithm<T extends CyLayoutContext> {
	
	/**
	 * 
	 * @param networkView
	 * @param layoutContext
	 * @param nodesToLayOut
	 * 
	 * @return taskIterator contains layout tasks.
	 */
	TaskIterator createTaskIterator(CyNetworkView networkView, T layoutContext, Set<View<CyNode>> nodesToLayOut);
	
	
	/**
	 * 
	 * @param networkView
	 * @param layoutContext
	 * @param nodesToLayOut
	 * 
	 * @return
	 */
	boolean isReady(CyNetworkView networkView, T layoutContext, Set<View<CyNode>> nodesToLayOut);
	
	/**
	 * 
	 * @return
	 */
	T createLayoutContext();
	
	/**
	 * Tests to see if this layout supports doing a layout based on node attributes.
	 *
	 * @return types of allowable attribute types.
	 */
	Set<Class<?>> getSupportedNodeAttributeTypes();

	/**
	 * Tests to see if this layout supports doing a layout based on edge attributes.
	 *
	 * @return types of allowable attribute types.
	 */
	Set<Class<?>> getSupportedEdgeAttributeTypes();

	/**
	 * This returns a (possibly empty) List of Strings that is used for
	 * the attribute list in the menu for attribute-based layouts.  This
	 * allows layout algorithms to provide "special" attributes.  For example,
	 * a force directed layout might want to set the list to ["(unweighted)"]
	 * to allow the user to perform an unweighted layout.  Note that this value
	 * will be set using setLayoutAttribute() just like other attributes, so the
	 * layout algorithm will need to check for it.
	 *
	 * @return List of column names (i.e. attributes) used for attribute-based layouts.
	 */
	List<String> getInitialAttributeList();

	/**
	 * Returns the computer-readable name of the layout.  To get
	 * a human readable name, use toString().
	 *
	 * @return The computer-readable name of the layout.
	 */
	String getName();
}
