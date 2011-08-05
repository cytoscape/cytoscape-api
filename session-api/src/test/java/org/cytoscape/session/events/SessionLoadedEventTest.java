package org.cytoscape.session.events;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.cytoscape.model.CyTableMetadata;
import org.cytoscape.property.bookmark.Bookmarks;
import org.cytoscape.property.session.Cysession;
import org.cytoscape.session.CyApplicationManager;
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
	private Properties props;

	@Mock
	private Bookmarks bkmarks;

	@Mock
	private Cysession cysess;

	@Mock
	private Map<String, List<File>> pluginMap;

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
		
		session = new CySession.Builder().cytoscapeProperties(props).bookmarks(bkmarks).cysession(cysess)
				.pluginFileListMap(pluginMap).tables(tables).networkViews(netViews).visualStyles(styles)
				.viewVisualStyleMap(stylesMap).build();
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
