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

	@Mock
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
