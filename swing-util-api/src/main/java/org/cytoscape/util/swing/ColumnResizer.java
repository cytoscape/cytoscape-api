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

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


/**
 *
 * Automatically resize column based on the objects in the cell.<br>
 *
 * <p>
 * From <i>Swing Hacks</i> by Joshua Marinacci and Chris Adamson.<br>
 * 2005 Oreilly & Associates Inc. ISBN: 0-596-00907-0<br>
 * </p>
 *
 * @author Joshua Marinacci, Chris Adamson, Keiichiro Ono
 * @CyAPI.Static.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class ColumnResizer {
	
	private static final int DEFLMAX_WIDTH = 280;

	private ColumnResizer() {}

	/**
	 * Adjust the columns in the table to their preferred widths.
	 * @param table The table whose columns should be adjusted.
	 */
	public static void adjustColumnPreferredWidths(final JTable table) {
		// Get max width for cells in column and make that the preferred width
		final int columnCount = table.getColumnModel().getColumnCount();
		
		for (int col = 0; col < columnCount; col++)
			adjustColumnPreferredWidth(table, col);
	}

	/**
	 * Adjust one table column to its preferred width.
	 * @param table The table whose columns should be adjusted.
	 * @param col The column index.
	 */
	public static void adjustColumnPreferredWidth(final JTable table, final int col) {
		// Get max width for cells in column and make that the preferred width
		int maxwidth = 0;

		for (int row = 0; row < table.getRowCount(); row++) {
			TableCellRenderer rend = table.getCellRenderer(row, col);
			Object value = table.getValueAt(row, col);
			Component comp = rend.getTableCellRendererComponent(table, value, false, false,
			                                                    row, col);
			maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
		} 

		// This version of the width set considers the column header's preferred width too
		TableColumn column = table.getColumnModel().getColumn(col);
		TableCellRenderer headerRenderer = column.getHeaderRenderer();

		if (headerRenderer == null)
			headerRenderer = table.getTableHeader().getDefaultRenderer();

		Object headerValue = column.getHeaderValue();
		Component headerComp = headerRenderer.getTableCellRendererComponent(table, headerValue,
		                                                                    false, false, 0, col);
		maxwidth = Math.max(maxwidth, headerComp.getPreferredSize().width);

		// If the value is too big, adjust to fixed maximum value
		if (DEFLMAX_WIDTH < maxwidth)
			maxwidth = DEFLMAX_WIDTH;

		column.setPreferredWidth(maxwidth + 20);
	}
}
