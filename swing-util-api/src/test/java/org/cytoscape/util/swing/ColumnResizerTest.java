package org.cytoscape.util.swing;

import static org.junit.Assert.assertTrue;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.junit.Test;

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

public class ColumnResizerTest {
	
	@Test
	public void testAdjustColumnPreferredWidths() {
		final String name = "Test";
		
		var table = new JTable();
		var col = new TableColumn();
		col.setHeaderValue(name);
		col.setMinWidth(1); // making sure the minimum width is not already bigger than our initial value
		col.setPreferredWidth(1);
		col.setWidth(1);
		table.addColumn(col);
		
		ColumnResizer.adjustColumnPreferredWidths(table);
		int w = table.getColumn(name).getWidth();
		assertTrue(w > 1);
		
		col.setHeaderValue(name + name);
		ColumnResizer.adjustColumnPreferredWidths(table);
		assertTrue(table.getColumn(name + name).getWidth() > w);
	}
}
