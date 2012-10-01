package org.cytoscape.application.events;

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
