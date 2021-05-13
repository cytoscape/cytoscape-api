package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class AbstractNetworkEventTest {
	@Test
	public final void testGetNetwork() {
		final CyNetworkManager networkManager = mock(CyNetworkManager.class);
		final CyNetwork network = mock(CyNetwork.class);
		final AbstractNetworkEvent event = new AbstractNetworkEvent(networkManager, Object.class, network);
		assertEquals("Network returned by getNetwork() is *not* the one passed into the constructor!",
			     network, event.getNetwork());
	}

	@Test(expected=NullPointerException.class)
	public final void testNullNetworkConstructorFailure() {
		final CyNetworkManager networkManager = mock(CyNetworkManager.class);
		new AbstractNetworkEvent(networkManager, Object.class, null);
	}
}