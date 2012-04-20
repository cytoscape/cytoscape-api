package org.cytoscape.application.swing;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.swing.JMenuItem;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DynamicSubmenuListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TaskFactoryEnableSupportTest {

	private TaskFactoryEnableSupport support;

	@Mock
	private DynamicSubmenuListener action;
	@Mock
	private TaskFactory tfp;

	private DynamicSubmenuListener submenuListener;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		support = new TaskFactoryEnableSupport(action, tfp);
	}

	@Test
	public void testUpdateEnableState() {
		support.updateEnableState();
		verify(tfp, times(1)).isReady();
	}

	@Test
	public void testTaskFactoryEnableSupportConstructor() {
		support = new TaskFactoryEnableSupport(action, tfp);
		assertNotNull(support);
		support = new TaskFactoryEnableSupport(submenuListener, tfp);
		assertNotNull(support);
		support = new TaskFactoryEnableSupport(new JMenuItem(), tfp);
		assertNotNull(support);
	}
}
