package org.cytoscape.application.events;

import java.util.Collection;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;


/**
 * An event signaling that the a network has been set to current.
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class SetCurrentNetworkEvent extends AbstractCyEvent<CyApplicationManager> {


	private final CyNetwork net;

	private Collection<CyNode> selectedNodes;
	private Collection<CyNode> unselectedNodes;
	private Collection<CyEdge> selectedEdges;
	private Collection<CyEdge> unselectedEdges;
	
	/**
	 * Returns the network associated with this event. The network returned may be null!
	 * @return the network associated with this event.
	 */
	public final CyNetwork getNetwork() {
		return net;
	}

	/**
	 * Constructor.
	 * @param source The application manager that is the source of the event.
	 * @param net The network that has been set to the current network.
	 */
	public SetCurrentNetworkEvent(final CyApplicationManager source, final CyNetwork net) {
		super(source, SetCurrentNetworkListener.class);
		this.net = net;
	}
	
	
	public Collection<CyNode> getSelectedNodes() {
		if(selectedNodes == null) {
			selectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedNodes;
	}
	
	public Collection<CyNode> getUnselectedNodes() {
		if(unselectedNodes == null) {
			unselectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedNodes;
	}
	
	public Collection<CyEdge> getSelectedEdges() {
		if(selectedEdges == null) {
			selectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedEdges;
	}
	
	public Collection<CyEdge> getUnselectedEdges() {
		if(unselectedEdges == null) {
			unselectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedEdges;
	}
}
