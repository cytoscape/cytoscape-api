package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JTreeTableTest {
	
	JTreeTable table;
	
	@Mock TreeTableModel model;
	@Mock MouseEvent mouseEvent;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		table = new JTreeTable(model);
	}


	@Test
	public void testUpdateUI() {
		table.updateUI();
	}

	@Test
	public void testSetRowHeightInt() {
		table.setRowHeight(100);
		assertEquals(100, table.getRowHeight());
	}

	@Test
	public void testGetEditingRow() {
		assertEquals(-1,table.getEditingRow());
	}

	@Test
	public void testJTreeTable() {
		assertNotNull(table);
		assertTrue(table.getModel() instanceof TreeTableModelAdapter);
	}

	@Test
	public void testGetToolTipTextMouseEvent() {
		when(mouseEvent.getPoint()).thenReturn(new Point(10, 20));
		
		table.setToolTipText("test");
		assertEquals("test", table.getToolTipText(mouseEvent));
	}

	@Test
	public void testGetTree() {
		assertNotNull(table.getTree());
	}
}
