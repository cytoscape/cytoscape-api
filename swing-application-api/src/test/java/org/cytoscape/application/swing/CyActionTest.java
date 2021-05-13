package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.Map;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.ServiceProperties;
import org.cytoscape.work.TaskFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class CyActionTest extends AbstractCyActionTest {

	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		configProps.put(ServiceProperties.PREFERRED_MENU, "File");
		configProps.put(ServiceProperties.ACCELERATOR, "cmd s");
		configProps.put(ServiceProperties.COMMAND, "test");
		configProps.put(ServiceProperties.COMMAND_NAMESPACE, "dummy");
		configProps.put(ServiceProperties.ENABLE_FOR, ActionEnableSupport.ENABLE_FOR_NETWORK);
		configProps.put(ServiceProperties.ID, "dummyAction");
		configProps.put(ServiceProperties.IN_MENU_BAR, "true");
		configProps.put(ServiceProperties.IN_TOOL_BAR, "false");
		configProps.put(ServiceProperties.LARGE_ICON_URL, "test.png");
		configProps.put(ServiceProperties.SMALL_ICON_URL, "small.png");
		configProps.put(ServiceProperties.MENU_GRAVITY, "10.0");
		configProps.put(ServiceProperties.TITLE, "Dummy Cy Action");
		configProps.put(ServiceProperties.TOOL_BAR_GRAVITY, "200");
		configProps.put(ServiceProperties.TOOLTIP, "This is a dummy action.");
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
