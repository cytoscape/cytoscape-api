package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;

import javax.swing.AbstractAction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DropDownMenuButtonTest {
	DropDownMenuButton button;
	@Mock private AbstractAction action;
	@Mock private Graphics2D g;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		button = new DropDownMenuButton(action);
	}


	@Test
	public void testDropDownMenuButton() {
		assertNotNull(action);
	}

	@Test
	public void testPaintComponentGraphics() {
		button.repaint();
	}

}
