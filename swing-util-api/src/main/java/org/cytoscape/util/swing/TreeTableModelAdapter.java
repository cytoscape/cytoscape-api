/* %% Ignore-License */
/*
 * The contents of this file are subject to the Sapient Public License
 * Version 1.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://carbon.sf.net/License.html.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is The Carbon Component Framework.
 *
 * The Initial Developer of the Original Code is Sapient Corporation
 *
 * Copyright (C) 2003 Sapient Corporation. All Rights Reserved.
 */
package org.cytoscape.util.swing;


/*
 * @(#)TreeTableModelAdapter.java    1.2 98/10/27
 *
 * Copyright 1997, 1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;


/**
 * This is a wrapper class takes a TreeTableModel and implements
 * the table model interface. The implementation is trivial, with
 * all of the event dispatching support provided by the superclass:
 * the AbstractTableModel.
 *
 * @version 1.2 10/27/98
 *
 * @author Philip Milne
 * @author Scott Violet
 * @CyAPI.Final.Class   
 */
public final class TreeTableModelAdapter extends AbstractTableModel {
	private final static long serialVersionUID = 1202339875193043L;
	JTree tree;
	TreeTableModel treeTableModel;

	/**
	 * Creates a new TreeTableModelAdapter object.
	 *
	 * @param treeTableModel The tree table model. 
	 * @param tree The tree itself. 
	 */
	public TreeTableModelAdapter(TreeTableModel treeTableModel, JTree tree) {
		this.tree = tree;
		this.treeTableModel = treeTableModel;

		tree.addTreeExpansionListener(new TreeExpansionListener() {
				// Don't use fireTableRowsInserted() here; the selection model
				// would get updated twice.
				public void treeExpanded(TreeExpansionEvent event) {
					fireTableDataChanged();
				}

				public void treeCollapsed(TreeExpansionEvent event) {
					fireTableDataChanged();
				}
			});

		// Install a TreeModelListener that can update the table when
		// tree changes. We use delayedFireTableDataChanged as we can
		// not be guaranteed the tree will have finished processing
		// the event before us.
		treeTableModel.addTreeModelListener(new TreeModelListener() {
				public void treeNodesChanged(TreeModelEvent e) {
					delayedFireTableDataChanged();
				}

				public void treeNodesInserted(TreeModelEvent e) {
					delayedFireTableDataChanged();
				}

				public void treeNodesRemoved(TreeModelEvent e) {
					delayedFireTableDataChanged();
				}

				public void treeStructureChanged(TreeModelEvent e) {
					delayedFireTableDataChanged();
				}
			});
	}

	// Wrappers, implementing TableModel interface.
	/**
	 *  Returns the number of columns in the TreeTabelModel.
	 *
	 * @return  the int number of columns in the TreeTabelModel.
	 */
	public int getColumnCount() {
		return treeTableModel.getColumnCount();
	}

	/**
	 *  Returns the column name at the given column number.
	 *
	 * @param column The column number.
	 *
	 * @return  The name of the column,.
	 */
	public String getColumnName(int column) {
		return treeTableModel.getColumnName(column);
	}

	/**
	 *  Returns the class of the column at the given column number.
	 *
	 * @param column The column number.
	 *
	 * @return  The class of the column.
	 */
	public Class getColumnClass(int column) {
		return treeTableModel.getColumnClass(column);
	}

	/**
	 *  Returns the number of rows in the {@link JTree}.
	 *
	 * @return  The int number of rows in the JTree.
	 */
	public int getRowCount() {
		return tree.getRowCount();
	}

	private Object nodeForRow(int row) {
		TreePath treePath = tree.getPathForRow(row);

		if (treePath != null) {
			return treePath.getLastPathComponent();
		} else

			return null;
	}

	/**
	 *  Returns the value at the given row and column.
	 *
	 * @param row The row number.
	 * @param column The column number.
	 *
	 * @return  The value at the given location.
	 */
	public Object getValueAt(int row, int column) {
		Object tempObj = nodeForRow(row);

		if (tempObj != null) {
			return treeTableModel.getValueAt(tempObj, column);
		} else {
			return null;
		}
	}

	/**
	 *  Returns whether the cell at the given location is editable.
	 *
	 * @param row The row number.
	 * @param column The column number.
	 *
	 * @return  True if the cell is editable, false otherwise.
	 */
	public boolean isCellEditable(int row, int column) {
		return treeTableModel.isCellEditable(nodeForRow(row), column);
	}

	/**
	 *  Sets the value at the given row and column..
	 *
	 * @param value The value to set to.
	 * @param row The row number.
	 * @param column The column number.
	 */
	public void setValueAt(Object value, int row, int column) {
		treeTableModel.setValueAt(value, nodeForRow(row), column);
	}

	/**
	 * Invokes fireTableDataChanged after all the pending events have been
	 * processed. SwingUtilities.invokeLater is used to handle this.
	 */
	protected void delayedFireTableDataChanged() {
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					fireTableDataChanged();
				}
			});
	}
}
