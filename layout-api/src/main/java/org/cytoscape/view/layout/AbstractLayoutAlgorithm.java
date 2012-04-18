/*
  File: AbstractLayoutAlgorithm.java

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


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TunableValidator;
import org.cytoscape.work.TunableValidator.ValidationState;


/**
 * The AbstractLayoutAlgorithm provides a basic implementation of a layout TaskFactory.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractLayoutAlgorithm<T extends CyLayoutContext> implements CyLayoutAlgorithm<T> {

	private final String humanName;
	private final String computerName;
	
	/**
	 * The Constructor.
	 * @param undo the UndoSupport object used for allowing undo of layouts.
	 * @param computerName a computer readable name used to construct property strings.
	 * @param humanName a user visible name of the layout.
	 * @param supportsSelectedOnly indicates whether only selected nodes should be laid out.
	 */
	public AbstractLayoutAlgorithm(final String computerName, final String humanName) {
		this.computerName = computerName;
		this.humanName = humanName;
	}

	/**
	 * A computer readable name used to construct property strings.
	 * @return a computer readable name used to construct property strings.
	 */
	public String getName() {
		return computerName;
	}

	/**
	 * Used to get the user-visible name of the layout.
	 * @return the user-visible name of the layout.
	 */
	public String toString() {
		return humanName;
	}

	/**
	 * Returns the types of node attributes supported by
	 * this algorithm.  This should be overridden by the
	 * specific algorithm.
	 *
	 * @return the list of supported attribute types, or null
	 * if node attributes are not supported
	 */
	@Override
	public Set<Class<?>> getSupportedNodeAttributeTypes() {
		return new HashSet<Class<?>>();
	}

	/**
	 * Returns the types of edge attributes supported by
	 * this algorithm.  This should be overridden by the
	 * specific algorithm.
	 *
	 * @return the list of supported attribute types, or null
	 * if edge attributes are not supported
	 */
	@Override
	public Set<Class<?>> getSupportedEdgeAttributeTypes() {
		return new HashSet<Class<?>>();
	}

	/**
	 * This returns the list of "attributes" that are provided
	 * by an algorithm for internal purposes.  For example,
	 * an edge-weighted algorithm might seed the list of
	 * attributes with "unweighted".  This should be overloaded
	 * by algorithms that intend to return custom attributes.
	 *
	 * @return A (possibly empty) list of attributes
	 */
	@Override
	public List<String> getInitialAttributeList() {
		return Collections.emptyList();
	}

	@Override
	public boolean isReady(CyNetworkView view, T tunableContext, Set<View<CyNode>> nodesToLayout) {
		if (view == null || nodesToLayout == null) {
			return false;
		}
		if (nodesToLayout.size() == 0 && view.getNodeViews().size() == 0) {
			return false;
		}
		if (tunableContext instanceof TunableValidator) {
			StringBuilder errors = new StringBuilder();
			return ((TunableValidator) tunableContext).getValidationState(errors) == ValidationState.OK;
		}
		return true;
	}
}
