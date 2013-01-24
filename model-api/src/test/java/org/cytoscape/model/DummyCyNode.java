package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.util.List;
import java.util.ArrayList;

public class DummyCyNode extends DummyGraphObject implements CyNode {
	long ind;
	CyNetwork nested;

	public DummyCyNode(long x) {
		super();
		ind = x;
		nested = null;
	}

	public List<CyNode> getNeighborList(CyEdge.Type edgeType) {
		return new ArrayList<CyNode>();
	}

	public List<CyEdge> getAdjacentEdgeList(CyEdge.Type edgeType) {
		return new ArrayList<CyEdge>();
	}

	public List<CyEdge> getConnectingEdgeList(CyNode target, CyEdge.Type edgeType) {
		return new ArrayList<CyEdge>();
	}

	public CyNetwork getNetworkPointer() {
		return nested;
	}

	public void setNetworkPointer(CyNetwork n) {
		nested = n;
	}
}
