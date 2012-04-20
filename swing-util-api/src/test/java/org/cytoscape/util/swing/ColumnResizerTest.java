package org.cytoscape.util.swing;

import static org.junit.Assert.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ColumnResizerTest {
	
	@Test
	public void testAdjustColumnPreferredWidths() {
		JTable table = new JTable();
		final TableColumn col = new TableColumn();
		col.setHeaderValue("Test");
		col.setWidth(1);
		int w = col.getWidth();
		table.addColumn(col);
		ColumnResizer.adjustColumnPreferredWidths(table);
		
		assertTrue(table.getColumn("Test").getWidth() == w);
	}

}
