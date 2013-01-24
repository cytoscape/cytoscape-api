package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import javax.swing.JMenuItem;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.swing.DynamicSubmenuListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ActionEnableSupportTest {
	
	private ActionEnableSupport support;
	
	@Mock private DynamicSubmenuListener action;
	private String enableFor = ActionEnableSupport.ENABLE_FOR_NETWORK;
	@Mock private CyApplicationManager applicationManager;
	@Mock private CyNetworkViewManager networkViewManager;
	@Mock private DynamicSubmenuListener submenuListener;

	private JMenuItem menuItem = new JMenuItem();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		support = new ActionEnableSupport(action, enableFor, applicationManager, networkViewManager);
	}

	@Test
	public void testUpdateEnableState() {
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_NETWORK, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_NETWORK_AND_VIEW, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_NETWORK_WITHOUT_VIEW, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_SELECTED_EDGES, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_SELECTED_NODES, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_SELECTED_NODES_OR_EDGES, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,ActionEnableSupport.ENABLE_FOR_TABLE, applicationManager, networkViewManager);
		support.updateEnableState();
		assertFalse(support.isCurrentlyEnabled());
		
		support = new ActionEnableSupport(action,null, applicationManager, networkViewManager);
		support.updateEnableState();
		assertTrue(support.isCurrentlyEnabled());
	}

	@Test
	public void testStringEnableSupportConstructor() {
		support = new ActionEnableSupport(action, enableFor, applicationManager, networkViewManager);
		assertNotNull(support);
		support = new ActionEnableSupport(submenuListener, enableFor, applicationManager, networkViewManager);
		assertNotNull(support);
		support = new ActionEnableSupport(menuItem, enableFor, applicationManager, networkViewManager);
		assertNotNull(support);
	}
}
