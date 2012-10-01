package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import javax.swing.Action;
import javax.swing.JMenuItem;

import org.cytoscape.work.swing.DynamicSubmenuListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AlwaysEnabledEnableSupportTest {
	
	private AlwaysEnabledEnableSupport support;
	
	@Mock private DynamicSubmenuListener submenuListener;
	@Mock private Action action;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		support = new AlwaysEnabledEnableSupport(action);
	}


	@Test
	public void testUpdateEnableState() {
		support.setEnabled(false);
		assertFalse(support.isCurrentlyEnabled());
		support.updateEnableState();
		assertTrue(support.isCurrentlyEnabled());
	}

	@Test
	public void testAlwaysEnabledEnableSupportConstructor() {
		support = new AlwaysEnabledEnableSupport(action);
		assertNotNull(support);
		support = new AlwaysEnabledEnableSupport(submenuListener);
		assertNotNull(support);
		support = new AlwaysEnabledEnableSupport(new JMenuItem());
		assertNotNull(support);
	}
}
