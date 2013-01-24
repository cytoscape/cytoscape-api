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


import static org.junit.Assert.*;
import org.junit.Test;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import java.lang.RuntimeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractCyIdentifiableTest {
	protected CyNetwork net;

	@Test
	public void testGetSUID() {
		CyNode n1 = net.addNode();
		assertTrue("suid >= 0", n1.getSUID() >= 0);

		CyNode n2 = net.addNode();
		assertTrue("suid >= 0", n2.getSUID() >= 0);

		CyEdge e1 = net.addEdge(n1, n2, true);
		assertTrue("suid >= 0", e1.getSUID() >= 0);

		CyEdge e2 = net.addEdge(n1, n2, false);
		assertTrue("suid >= 0", e2.getSUID() >= 0);

		assertTrue("suid >= 0", net.getSUID() >= 0);
	}
}
