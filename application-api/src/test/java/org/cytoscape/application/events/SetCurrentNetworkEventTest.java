package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.events.SetCurrentNetworkEvent;
import org.cytoscape.model.CyNetwork;
import org.junit.Test;

public class SetCurrentNetworkEventTest {

	@Test
	public void testGoodGetNetwork() {
		CyApplicationManager source = mock(CyApplicationManager.class);
		CyNetwork n = mock(CyNetwork.class);
		SetCurrentNetworkEvent e = new SetCurrentNetworkEvent(source,n);
		assertNotNull( e.getNetwork() );
		assertEquals( n,e.getNetwork() );
	}

	@Test
	public void testNullGetNetwork() {
		CyApplicationManager source = mock(CyApplicationManager.class);
		SetCurrentNetworkEvent e = new SetCurrentNetworkEvent(source,null);
		assertNull( e.getNetwork() );
	}

}
