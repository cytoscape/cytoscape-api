package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.Map;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class CyActionTest extends AbstractCyActionTest {

	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		action = new DummyAction(configProps, applicationManager, networkViewManager);
		action.setName(name);
	}

	@Override
	public void testAbstractCyActionConstructor() {
		action = new DummyAction(configProps, applicationManager, networkViewManager);
		action = new DummyAction(name);
		action = new DummyAction(configProps, factory);
		action = new DummyAction(configProps, applicationManager, networkViewManager, factory);
		action = new DummyAction(name, applicationManager, enableFor, networkViewManager);
	}

	private static final class DummyAction extends AbstractCyAction {

		public DummyAction(Map<String, String> configProps, CyApplicationManager applicationManager,
				CyNetworkViewManager networkViewManager, TaskFactory factory) {
			super(configProps, applicationManager, networkViewManager, factory);
		}

		public DummyAction(String name) {
			super(name);
		}

		public DummyAction(Map<String, String> configProps, TaskFactory predicate) {
			super(configProps, predicate);
		}

		public DummyAction(Map<String, String> configProps, CyApplicationManager applicationManager,
				CyNetworkViewManager networkViewManager) {
			super(configProps, applicationManager, networkViewManager);
		}

		public DummyAction(String name, CyApplicationManager applicationManager, String enableFor,
				CyNetworkViewManager networkViewManager) {
			super(name, applicationManager, enableFor, networkViewManager);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
		}
	}
}
