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

import java.awt.Component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractToolBarComponentTest {
	
	private AbstractToolBarComponent toolBarComponent;

	@Before
	public void setUp() throws Exception {
		toolBarComponent = new DummyToolBarComponent();
	}

	@Test
	public void testSetToolBarGravity() {
		toolBarComponent.setToolBarGravity(12f);
		assertTrue(12f == toolBarComponent.getToolBarGravity());
	}
	
	private static final class DummyToolBarComponent extends AbstractToolBarComponent {

		@Override
		public Component getComponent() {
			return null;
		}
		
	}

}
