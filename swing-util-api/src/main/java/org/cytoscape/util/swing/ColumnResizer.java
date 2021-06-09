package org.cytoscape.util.swing;

import javax.swing.JTable;

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
	 * This method considers the preferred width of the cells in all rows in addition to the column headers.
	 * @param table The table whose columns should be adjusted.
	 */
	public static void adjustColumnPreferredWidths(JTable table) {
		adjustColumnPreferredWidths(table, true);
	}
	
	/**
	 * Adjust the columns in the table to their preferred widths.
	 * @param table The table whose columns should be adjusted.
	 * @param checkAllRows If false, only the preferred width of the column headers are considered.
	 */
	public static void adjustColumnPreferredWidths(JTable table, boolean checkAllRows) {
		// Get max width for cells in column and make that the preferred width
		int columnCount = table.getColumnModel().getColumnCount();
		
		for (int col = 0; col < columnCount; col++)
			adjustColumnPreferredWidth(table, col, checkAllRows);
	}

	/**
	 * Adjust one table column to its preferred width.
	 * This method considers the preferred width of the cells in all rows in addition to the column header.
	 * @param table The table whose columns should be adjusted.
	 * @param col The column index.
	 */
	public static void adjustColumnPreferredWidth(JTable table, int col) {
		adjustColumnPreferredWidth(table, col, true);
	}
	
	/**
	 * Adjust one table column to its preferred width.
	 * @param table The table whose columns should be adjusted.
	 * @param col The column index.
	 * @param checkAllRows If false, only the preferred width of the column header is considered.
	 */
	public static void adjustColumnPreferredWidth(JTable table, int col, boolean checkAllRows) {
		// Get max width for cells in column and make that the preferred width
		int newWidth = 0;

		if (checkAllRows) {
			for (int row = 0; row < table.getRowCount(); row++) {
				var rend = table.getCellRenderer(row, col);
				var value = table.getValueAt(row, col);
				var comp = rend.getTableCellRendererComponent(table, value, false, false, row, col);
				newWidth = Math.max(comp.getPreferredSize().width, newWidth);
			}
		}

		// This version of the width set considers the column header's preferred width too
		var column = table.getColumnModel().getColumn(col);
		var headerRenderer = column.getHeaderRenderer();

		if (headerRenderer == null)
			headerRenderer = table.getTableHeader().getDefaultRenderer();

		var headerValue = column.getHeaderValue();
		var headerComp = headerRenderer.getTableCellRendererComponent(table, headerValue, false, false, 0, col);
		newWidth = Math.max(newWidth, headerComp.getPreferredSize().width);
		newWidth = Math.min(newWidth, DEFLMAX_WIDTH);
		newWidth += 20;

		column.setPreferredWidth(newWidth);
		column.setWidth(newWidth);
	}
}
