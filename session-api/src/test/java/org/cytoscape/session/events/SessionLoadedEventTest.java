package org.cytoscape.session.events;

/*
 * #%L
 * Cytoscape Session API (session-api)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cytoscape.model.CyTableMetadata;
import org.cytoscape.property.CyProperty;
import org.cytoscape.session.CySession;
import org.cytoscape.session.CySessionManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SessionLoadedEventTest {

	@Mock
	private CySessionManager sessionManager;

	private CySession session;

	@Mock
	private Set<CyProperty<?>> props;

	@Mock
	private Map<String, List<File>> appMap;

	@Mock
	private Set<CyTableMetadata> tables;

	@Mock
	private Set<CyNetworkView> netViews;

	@Mock
	private Set<VisualStyle> styles;

	@Mock
	private Map<CyNetworkView, String> stylesMap;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		
		session = new CySession.Builder().properties(props).appFileListMap(appMap).tables(tables)
				.networkViews(netViews).visualStyles(styles).viewVisualStyleMap(stylesMap)
				.build();
	}
	
	@Test
	public void testSessionLoadedEvent() {
		final String sessionFileName = "sampleSession";
		
		final SessionLoadedEvent e = new SessionLoadedEvent(sessionManager, session, sessionFileName);
		assertNotNull(e.getLoadedSession());
		assertEquals(session, e.getLoadedSession());
		
		assertNotNull(e.getLoadedFileName());
		assertEquals(sessionFileName, e.getLoadedFileName());
	}
}
