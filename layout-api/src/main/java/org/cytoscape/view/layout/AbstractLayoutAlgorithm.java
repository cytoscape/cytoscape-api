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

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TunableValidator;
import org.cytoscape.work.TunableValidator.ValidationState;
import org.cytoscape.work.undo.UndoSupport;


/**
 * The AbstractLayoutAlgorithm provides a basic implementation of a layout TaskFactory.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractLayoutAlgorithm implements CyLayoutAlgorithm {

	private final String humanName;
	private final String computerName;
	private Object defaultContext;
	
	/**
	 * An undo support object available for use 
	 */
	protected final UndoSupport undoSupport;
	
	/**
	 * The Constructor.
	 * @param computerName a computer readable name used to construct property strings.
	 * @param humanName a user visible name of the layout.
	 */
	public AbstractLayoutAlgorithm(final String computerName, final String humanName, UndoSupport undoSupport) {
		this.computerName = computerName;
		this.humanName = humanName;
		this.undoSupport = undoSupport;
	}

	@Override
	public Object createLayoutContext() {
		return new Object();
	}
	
	public final Object getDefaultLayoutContext() {
		if (defaultContext == null)
				defaultContext = createLayoutContext();
		return defaultContext;
	}
	
	@Override
	public String getName() {
		return computerName;
	}


	@Override
	public String toString() {
		return humanName;
	}


	@Override
	public Set<Class<?>> getSupportedNodeAttributeTypes() {
		return new HashSet<Class<?>>();
	}


	@Override
	public Set<Class<?>> getSupportedEdgeAttributeTypes() {
		return new HashSet<Class<?>>();
	}
	

	@Override
	public boolean getSupportsSelectedOnly() {
		return false;
	}


	@Override
	public boolean isReady(CyNetworkView view, Object tunableContext, Set<View<CyNode>> nodesToLayout, String attributeName) {
		if (view == null || nodesToLayout == null)
			return false;
		
		if (nodesToLayout.size() == 0 && view.getNodeViews().size() == 0)
			return false;
		
		if (tunableContext instanceof TunableValidator) {
			StringBuilder errors = new StringBuilder();
			return ((TunableValidator) tunableContext).getValidationState(errors) == ValidationState.OK;
		}
		return true;
	}
}
