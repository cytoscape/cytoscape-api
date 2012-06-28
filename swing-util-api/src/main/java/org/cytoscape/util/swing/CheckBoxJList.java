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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * <p>
 * This class is based on CheckBoxJList: from SWING HACKS
 * ISBN: 0-596-00907-0
 *     By Joshua Marinacci, Chris Adamson
 * </p>
 *
 * <p>
 *     Customized by Keiichiro Ono
 * </p>
 */
public class CheckBoxJList extends JList implements ListSelectionListener {
	
	private final static long serialVersionUID = 120233987581935L;
	
	private static final Color SELECTED_COLOR = new Color(0, 100, 250, 250);
	private static final Color NORMAL_COLOR = new Color(100, 100, 100, 170);
	
	private static final Color listBackground;
	private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 12);
	private static final Font SELECTED_FONT = new Font("SansSerif", Font.BOLD, 12);

	/**
	 * The name of the property change that indicates that the list 
	 * has been updated.
	 */
	public static final String LIST_UPDATED = "LIST_UPDATED";

	static {
		UIDefaults uid = UIManager.getLookAndFeel().getDefaults();
		listBackground = uid.getColor("List.background");
	}

	private final Set<Integer> selectionCache;

	/**
	 * Creates a new CheckBoxJList object.
	 */
	public CheckBoxJList() {
		super();
		selectionCache = new HashSet<Integer>();
		setCellRenderer(new CheckBoxListCellRenderer());
		addListSelectionListener(this);
	}

	/**
	 * Sets the specified items as selected.
	 * @param selected the items to be selected.
	 */
	public void setSelectedItems(final List<String> selected) {
		final ListSelectionListener[] listeners = this.getListSelectionListeners();
		for(ListSelectionListener l :listeners)
			removeListSelectionListener(l);
		
		getSelectionModel().clearSelection();
		selectionCache.clear();
		
		final int size = this.getModel().getSize();
		for(int i=0; i<size; i++) {
			if(selected.contains(getModel().getElementAt(i))) {
				getSelectionModel().addSelectionInterval(i, i);
				selectionCache.add(i);
			}
		}
		
		for(ListSelectionListener l :listeners)
			addListSelectionListener(l);
	}

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		
		if (!lse.getValueIsAdjusting()) {
			removeListSelectionListener(this);

			// remember everything selected as a result of this action
			final HashSet<Integer> newSelections = new HashSet<Integer>();
			final int size = getModel().getSize();
			final ListSelectionModel select = getSelectionModel();

			for (int i = 0; i < size; i++) {
				if (select.isSelectedIndex(i))
					newSelections.add(i);
			}

			// turn on everything that was previously selected
			for (Integer index : selectionCache) {
				select.addSelectionInterval(index, index);
			}

			// add or remove the delta
			for (Integer index : newSelections) {
				if (selectionCache.contains(index))
					select.removeSelectionInterval(index, index);
				else
					select.addSelectionInterval(index, index);
			}

			// save selections for next time
			selectionCache.clear();

			for (int i = 0; i < size; i++) {
				if (select.isSelectedIndex(i))
					selectionCache.add(i);
			}

			addListSelectionListener(this);
			firePropertyChange(LIST_UPDATED, null, null);
		}
	}

	private static final class CheckBoxListCellRenderer extends JComponent implements ListCellRenderer {
		
		private final static long serialVersionUID = 120233987573888L;
		
		private final DefaultListCellRenderer defaultComp;
		private final JCheckBox checkbox;
		private final BorderLayout layout = new BorderLayout();

		CheckBoxListCellRenderer() {
			setLayout(layout);
			defaultComp = new DefaultListCellRenderer();
			checkbox = new JCheckBox();
			add(checkbox, BorderLayout.WEST);
			add(defaultComp, BorderLayout.CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			defaultComp.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			checkbox.setSelected(isSelected);

			if (isSelected) {
				checkbox.setFont(SELECTED_FONT);
				defaultComp.setFont(SELECTED_FONT);
				checkbox.setForeground(SELECTED_COLOR);
				defaultComp.setForeground(SELECTED_COLOR);
			} else {
				checkbox.setFont(NORMAL_FONT);
				defaultComp.setFont(NORMAL_FONT);
				checkbox.setForeground(NORMAL_COLOR);
				defaultComp.setForeground(NORMAL_COLOR);
			}

			final Component[] comps = getComponents();
			final int length = comps.length;
			for (int i = 0; i < length; i++)
				comps[i].setBackground(listBackground);

			return this;
		}
	}
}
