package org.cytoscape.application.events;

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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.events.SetSelectedNetworkViewsEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetSelectedNteworkViewsEventTest {

	@Mock
	private CyApplicationManager source;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGoodGetSelectedNetworkViewsEvent() {
		final List<CyNetworkView> views = new ArrayList<CyNetworkView>();
		final SetSelectedNetworkViewsEvent e = new SetSelectedNetworkViewsEvent(source, views);
		assertNotNull(e.getNetworkViews());
		assertEquals(views, e.getNetworkViews());
	}

	@Test
	public void testNullGetSelectedNetworkViewsEvent() {
		final SetSelectedNetworkViewsEvent e = new SetSelectedNetworkViewsEvent(source, null);
		assertNull(e.getNetworkViews());
	}

}
