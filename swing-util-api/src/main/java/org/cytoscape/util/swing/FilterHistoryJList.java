/*
 Copyright (c) 2006, 2007, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.util.swing;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * JList with incremntal search & search history.<br>
 *
 * <p>
 * From <i>Swing Hacks</i> by Joshua Marinacci and Chris Adamson.
 * </p>
 * Customized by Keiichiro Ono
 *
 */
public class FilterHistoryJList extends JList {
	private final static long serialVersionUID = 1202339875121578L;
	private FilterField filterField;
	private int DEFAULT_FIELD_WIDTH = 20;

	/**
	 * Creates a new FilterHistoryJList object.
	 */
	public FilterHistoryJList() {
		super();
		setModel(new FilterModel());
		filterField = new FilterField(DEFAULT_FIELD_WIDTH);
		filterField.textField.requestFocus();
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param m DOCUMENT ME!
	 */
	public void setModel(ListModel m) {
		if (!(m instanceof FilterModel))
			throw new IllegalArgumentException();

		super.setModel(m);
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param o DOCUMENT ME!
	 */
	public void addItem(Object o) {
		((FilterModel) getModel()).addElement(o);
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public FilterField getFilterField() {
		return filterField;
	}

	// inner class to provide filtered model
	class FilterModel extends AbstractListModel {
	private final static long serialVersionUID = 120233987592111L;
		List<Object> items;
		List<Object> filterItems;

		public FilterModel() {
			super();
			items = new ArrayList<Object>();
			filterItems = new ArrayList<Object>();
		}

		public Object getElementAt(int index) {
			if (index < filterItems.size())
				return filterItems.get(index);
			else

				return null;
		}

		public int getSize() {
			return filterItems.size();
		}

		public void addElement(Object o) {
			items.add(o);
			refilter();
		}

		private void refilter() {
			filterItems.clear();

			String term = getFilterField().textField.getText();

			for (int i = 0; i < items.size(); i++)
				if (items.get(i).toString().indexOf(term, 0) != -1)
					filterItems.add(items.get(i));

			fireContentsChanged(this, 0, getSize());
		}
	}

	// inner class provides filter-by-keystroke field
	class FilterField extends JComponent implements DocumentListener, ActionListener {
		private final static long serialVersionUID = 1202339875100770L;
		LinkedList<String> prevSearches;
		JTextField textField;
		JButton prevSearchButton;
		JPopupMenu prevSearchMenu;

		public FilterField(int width) {
			super();
			setLayout(new BorderLayout());
			textField = new JTextField(width);
			textField.getDocument().addDocumentListener(this);
			textField.addActionListener(this);
			prevSearchButton = new JButton(new ImageIcon(this.getClass().getResource("/images/ximian/stock_search.png")));
			prevSearchButton.setBorder(null);
			prevSearchButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) {
						popMenu(me.getX(), me.getY());
					}
				});
			add(prevSearchButton, BorderLayout.WEST);
			add(textField, BorderLayout.CENTER);
			prevSearches = new LinkedList<String>();
		}

		public void popMenu(int x, int y) {
			prevSearchMenu = new JPopupMenu();

			Iterator it = prevSearches.iterator();

			while (it.hasNext())
				prevSearchMenu.add(new PrevSearchAction(it.next().toString()));

			prevSearchMenu.show(prevSearchButton, x, y);
		}

		public void actionPerformed(ActionEvent e) {
			// called on return/enter, adds term to prevSearches
			if (e.getSource() == textField) {
				prevSearches.addFirst(textField.getText());

				if (prevSearches.size() > 10)
					prevSearches.removeLast();
			}
		}

		public void changedUpdate(DocumentEvent e) {
			((FilterModel) getModel()).refilter();
		}

		public void insertUpdate(DocumentEvent e) {
			((FilterModel) getModel()).refilter();
		}

		public void removeUpdate(DocumentEvent e) {
			((FilterModel) getModel()).refilter();
		}
	}

	class PrevSearchAction extends AbstractAction {
	private final static long serialVersionUID = 1202339875113029L;
		String term;

		public PrevSearchAction(String s) {
			term = s;
			putValue(Action.NAME, term);
		}

		public String toString() {
			return term;
		}

		public void actionPerformed(ActionEvent e) {
			getFilterField().textField.setText(term);

			// don't need this - setText fires a DocumentEvent
			// that FilterField handles
			// ((FilterModel)getModel()).refilter();
		}
	}
}
