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
 * @(#)JTreeTable.java 1.2 98/10/27
 *
 * Copyright 1997, 1998 by Sun Microsystems, Inc., 901 San Antonio Road, Palo
 * Alto, California, 94303, U.S.A. All rights reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Sun.
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This example shows how to create a simple JTreeTable component, by using a
 * JTree as a renderer (and editor) for the cells in a particular column in the
 * JTable.
 *
 * @version 1.2 10/27/98
 *
 * @author Philip Milne
 * @author Scott Violet
 * @CyAPI.Final.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class JTreeTable extends JTable {
	
	private final static long serialVersionUID = 1202339868625600L;
	private final static Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");

	private TreeTableCellRenderer tree;

	/**
	 * Creates a new JTreeTable object.
	 *
	 * @param treeTableModel The tree table model. 
	 */
	public JTreeTable(final TreeTableModel treeTableModel) {
		super();

		// Create the tree. It will be used as a renderer and editor.
		tree = new TreeTableCellRenderer(treeTableModel);

		// Install a tableModel representing the visible rows in the tree.
		super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

		// Force the JTable and JTree to share their row selection models.
		ListToTreeSelectionModelWrapper selectionWrapper = new ListToTreeSelectionModelWrapper();
		tree.setSelectionModel(selectionWrapper);
		setSelectionModel(selectionWrapper.getListSelectionModel());

		// Install the tree editor renderer and editor.
		setDefaultRenderer(TreeTableModel.class, tree);
		setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

		// No grid.
		setShowGrid(false);

		// No intercell spacing
		setIntercellSpacing(new Dimension(0, 0));

		// And update the height of the trees row to match that of
		// the table.
		if (tree.getRowHeight() < 1) {
			// Metal looks better like this.
			setRowHeight(18);
		}
	}

	/**
	 * The code in this method is copy and pasted from source code to the same
	 * method in javax.swing.JTable, except for one value change on one line. If
	 * you'd like to see the change, please read the source code below.
	 * @param event the mouse event we're reacting to
	 */
	@Override
	public String getToolTipText(MouseEvent event) {
		String tip = null;
		Point p = event.getPoint();

		// Locate the renderer under the event location
		int hitColumnIndex = columnAtPoint(p);
		int hitRowIndex = rowAtPoint(p);

		if ((hitColumnIndex != -1) && (hitRowIndex != -1)) {
			TableCellRenderer renderer = getCellRenderer(hitRowIndex, hitColumnIndex);
			Component component = prepareRenderer(renderer, hitRowIndex, hitColumnIndex);

			// Now have to see if the component is a JComponent before
			// getting the tip
			if (component instanceof JComponent) {
				// Convert the event to the renderer's coordinate system
				Rectangle cellRect = getCellRect(hitRowIndex, hitColumnIndex, false);
				// HERE IS THE MODIFICATION FROM javax.swing.JTable:
				// p.translate(-cellRect.x, -cellRect.y);
				p.translate(-cellRect.x, 0);

				// END OF MODIFICATION
				MouseEvent newEvent = new MouseEvent(component, event.getID(), event.getWhen(),
				                                     event.getModifiers(), p.x, p.y,
				                                     event.getClickCount(), event.isPopupTrigger());

				tip = ((JComponent) component).getToolTipText(newEvent);
			}
		}

		// No tip from the renderer get our own tip
		if (tip == null)
			tip = getToolTipText();

		return tip;
	}

	/**
	 * Overridden to message super and forward the method to the tree. Since the
	 * tree is not actually in the component hierarchy it will never receive this
	 * unless we forward it in this manner.
	 */
	@Override
	public void updateUI() {
		super.updateUI();

		if (tree != null)
			tree.updateUI();

		// Use the tree's default foreground and background colors in the table.
		LookAndFeel.installColorsAndFont(this, "Tree.background", "Tree.foreground", "Tree.font");
	}

	/*
	 * Workaround for BasicTableUI anomaly. Make sure the UI never tries to
	 * paint the editor. The UI currently uses different techniques to paint the
	 * renderers and editors and overriding setBounds() below is not the right
	 * thing to do for an editor. Returning -1 for the editing row in this case,
	 * ensures the editor is never painted.
	 */

	/**
	 * Returns the row being edited. 
	 * @return the row being edited. 
	 */
	@Override
	public int getEditingRow() {
		return (getColumnClass(editingColumn) == TreeTableModel.class) ? (-1) : editingRow;
	}

	/**
	 * Overridden to pass the new rowHeight to the tree.
	 * @param rowHeight the new height of the row
	 */
	public void setRowHeight(int rowHeight) {
		super.setRowHeight(rowHeight);

		if ((tree != null) && (tree.getRowHeight() != rowHeight)) {
			tree.setRowHeight(getRowHeight());
		}
	}

	/**
	 * Returns the tree that is being shared between the model.
	 * @return the {@link JTree} that is being shared between the model.
	 */
	public JTree getTree() {
		return tree;
	}

	/**
	 * A TreeCellRenderer that displays a JTree.
	 * @CyAPI.Final.Class 
	 */
	private class TreeTableCellRenderer extends JTree implements TableCellRenderer {
		
		private final static long serialVersionUID = 1202339868600141L;
		
		/** Last table/tree row asked to renderer. */
		protected int visibleRow;

		/**
		 * Constructs this TreeTabelCellRenderer.
		 * @param model The tree table model to be rendered. 
		 */
		public TreeTableCellRenderer(final TreeModel model) {
			super(model);
		}

		/**
		 * updateUI is overridden to set the colors of the Tree's renderer to match that of the table.
		 */
		@Override
		public void updateUI() {
			super.updateUI();

			// Make the tree's cell renderer use the table's cell selection
			// colors.
			TreeCellRenderer tcr = getCellRenderer();

			if (tcr instanceof DefaultTreeCellRenderer) {
				DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer) tcr);
				dtcr.setTextSelectionColor(UIManager.getColor("Table.focusCellForeground"));
				dtcr.setBackgroundSelectionColor(UIManager.getColor("Table.focusCellBackground"));
				dtcr.setBackground(UIManager.getColor("Table.background"));
				dtcr.setForeground(UIManager.getColor("Table.foreground"));
			}
		}

		/**
		 * Sets the row height of the tree, and forwards the row height to the table.
		 */
		@Override
		public void setRowHeight(int rowHeight) {
			if (rowHeight > 0) {
				super.setRowHeight(rowHeight);

				if ((JTreeTable.this != null) && (JTreeTable.this.getRowHeight() != rowHeight)) {
					JTreeTable.this.setRowHeight(getRowHeight());
				}
			}
		}

		/**
		 * This is overridden to set the height to match that of the JTable.
		 */
		@Override
		public void setBounds(int x, int y, int w, int h) {
			super.setBounds(x, 0, w, JTreeTable.this.getHeight());
		}

		/**
		 * Subclassed to translate the graphics such that the last visible row
		 * will be drawn at 0,0.
		 */
		@Override
		public void paint(Graphics g) {
			if (g == null)
				return;

			g.translate(0, -visibleRow * getRowHeight());

			try {
				super.paint(g);
			} catch (Throwable e) {
				logger.debug("random swing exception/error: ",e);
			}
		}

		/**
		 * TreeCellRenderer method. Overridden to update the visible row.
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
		                                               boolean isSelected, boolean hasFocus,
		                                               int row, int column) {
			if (isSelected) {
				setBackground(UIManager.getColor("Table.focusCellBackground"));
				setForeground(UIManager.getColor("Table.focusCellForeground"));
			} else {
				setBackground(UIManager.getColor("Table.background"));
				setForeground(UIManager.getColor("Table.foreground"));
			}
			
			visibleRow = row;

			return this;
		}
	}

	/**
	 * TreeTableCellEditor implementation. Component returned is the JTree.
	 */
	public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,
		                                             boolean isSelected, int r, int c) {
			return tree;
		}

		/**
		 * Overridden to return false, and if the event is a mouse event it is
		 * forwarded to the tree.
		 * <p>
		 * The behavior for this is debatable, and should really be offered as a
		 * property. By returning false, all keyboard actions are implemented in
		 * terms of the table. By returning true, the tree would get a chance to
		 * do something with the keyboard events. For the most part this is ok.
		 * But for certain keys, such as left/right, the tree will
		 * expand/collapse where as the table focus should really move to a
		 * different column. Page up/down should also be implemented in terms of
		 * the table. By returning false this also has the added benefit that
		 * clicking outside of the bounds of the tree node, but still in the
		 * tree column will select the row, whereas if this returned true that
		 * wouldn't be the case.
		 * <p>
		 * By returning false we are also enforcing the policy that the tree
		 * will never be editable (at least by a key sequence).
		 */
		@Override
		public boolean isCellEditable(EventObject e) {
			if (e instanceof MouseEvent) {
				for (int counter = getColumnCount() - 1; counter >= 0; counter--) {
					if (getColumnClass(counter) == TreeTableModel.class) {
						MouseEvent me = (MouseEvent) e;
						MouseEvent newME = new MouseEvent(tree, me.getID(), me.getWhen(),
						                                  me.getModifiers(),
						                                  me.getX()
						                                  - getCellRect(0, counter, true).x,
						                                  me.getY(), me.getClickCount(),
						                                  me.isPopupTrigger());
						tree.dispatchEvent(newME);

						break;
					}
				}
			}

			return false;
		}

		@Override
		public void cancelCellEditing() {}
	}

	/**
	 * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel to
	 * listen for changes in the ListSelectionModel it maintains. Once a change
	 * in the ListSelectionModel happens, the paths are updated in the
	 * DefaultTreeSelectionModel.
	 */
	class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel {
		
		private final static long serialVersionUID = 1202339868611480L;
		
		/** Set to true when we are updating the ListSelectionModel. */
		protected boolean updatingListSelectionModel;

		public ListToTreeSelectionModelWrapper() {
			super();
			getListSelectionModel().addListSelectionListener(createListSelectionListener());
		}

		/**
		 * Returns the list selection model. ListToTreeSelectionModelWrapper
		 * listens for changes to this model and updates the selected paths
		 * accordingly.
		 */
		ListSelectionModel getListSelectionModel() {
			return listSelectionModel;
		}

		/**
		 * This is overridden to set <code>updatingListSelectionModel</code>
		 * and message super. This is the only place DefaultTreeSelectionModel
		 * alters the ListSelectionModel.
		 */
		@Override
		public void resetRowSelection() {
			if (!updatingListSelectionModel) {
				updatingListSelectionModel = true;

				try {
					super.resetRowSelection();
				} finally {
					updatingListSelectionModel = false;
				}
			}

			// Notice how we don't message super if
			// updatingListSelectionModel is true. If
			// updatingListSelectionModel is true, it implies the
			// ListSelectionModel has already been updated and the
			// paths are the only thing that needs to be updated.
		}

		/** 
		 * Creates and returns an instance of {@link ListSelectionHandler}.
		 * @return the newly created instance of ListSelectionHandler.
		 */
		protected ListSelectionListener createListSelectionListener() {
			return new ListSelectionHandler();
		}

		/**
		 * If <code>updatingListSelectionModel</code> is false, this will
		 * reset the selected paths from the selected rows in the list selection
		 * model.
		 */
		protected void updateSelectedPathsFromSelectedRows() {
			if (!updatingListSelectionModel) {
				updatingListSelectionModel = true;

				try {
					// This is way expensive, ListSelectionModel needs an
					// enumerator for iterating.
					int min = listSelectionModel.getMinSelectionIndex();
					int max = listSelectionModel.getMaxSelectionIndex();

					clearSelection();

					if ((min != -1) && (max != -1)) {
						for (int counter = min; counter <= max; counter++) {
							if (listSelectionModel.isSelectedIndex(counter)) {
								TreePath selPath = tree.getPathForRow(counter);

								if (selPath != null) {
									addSelectionPath(selPath);
								}
							}
						}
					}
				} finally {
					updatingListSelectionModel = false;
				}
			}
		}

		/**
		 * Class responsible for calling updateSelectedPathsFromSelectedRows
		 * when the selection of the list changes.
		 */
		class ListSelectionHandler implements ListSelectionListener {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateSelectedPathsFromSelectedRows();
			}
		}
	}
}
