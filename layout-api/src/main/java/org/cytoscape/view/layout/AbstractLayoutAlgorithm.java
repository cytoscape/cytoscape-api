package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
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
 * @CyAPI.InModule layout-api
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
	
	@Override
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
