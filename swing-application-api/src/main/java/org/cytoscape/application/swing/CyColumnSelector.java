package org.cytoscape.application.swing;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static org.cytoscape.util.swing.LookAndFeelUtil.equalizeSize;
import static org.cytoscape.util.swing.LookAndFeelUtil.getSmallFontSize;
import static org.cytoscape.util.swing.LookAndFeelUtil.isAquaLAF;
import static org.cytoscape.util.swing.LookAndFeelUtil.isMac;
import static org.cytoscape.util.swing.LookAndFeelUtil.makeSmall;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.cytoscape.model.CyColumn;
import org.cytoscape.util.swing.IconManager;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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
 * An Swing control that allows the user to select a set of CyColumn objects.
 *
 * @CyAPI.InModule swing-application-api
 */
@SuppressWarnings("serial")
public class CyColumnSelector extends JPanel {

	private static final int SELECTED_COL_IDX = 0;
	private static final int NAME_COL_IDX = 1;
	private static final int TYPE_COL_IDX = 2;
	private static final int SHARED_COL_IDX = 3;
	
	private static final String[] headerNames = {"", "Column Name", "Type", ""};
	
	private static final Border CELL_BORDER = BorderFactory.createEmptyBorder();
	private static final String CYTOSCAPE_CARD = "::"; // not a valid namespace
	
	
	private final IconManager iconManager;
	private final CyColumnPresentationManager columnPresentationManager;
	
	private final SortedMap<String,List<CyColumn>> namespaces;
	private final Set<String> selectedColumnNames;
	
	/**
	 * Creates a CyColumnSelector.
	 * @param iconManager IconManager OSGi service, may not be null.
	 * @param columnPresentationManager CyColumnPresentationManager OSGi service, may not be null.
	 * @throws NullPointerException If any paramter is null.
	 */
	public CyColumnSelector(IconManager iconManager, CyColumnPresentationManager columnPresentationManager) {
		this.iconManager = Objects.requireNonNull(iconManager);
		this.columnPresentationManager = Objects.requireNonNull(columnPresentationManager);
		
		var collator = Collator.getInstance(Locale.getDefault());
		this.namespaces = new TreeMap<>(Comparator.nullsFirst(collator::compare));
		this.selectedColumnNames = new HashSet<>();
	}
	
	/**
	 * Updates the control to display the given CyColumn objects. 
	 * @param columns The CyColumn objects to display for selection.
	 * @param selectedColumnNames Names of columns that should be pre-selected.
	 */
	public void update(Collection<CyColumn> columns, Collection<String> selectedColumnNames) {
		this.namespaces.clear();
		this.selectedColumnNames.clear();
		
		for (var column : columns) {
			namespaces.computeIfAbsent(column.getNamespace(), k -> new ArrayList<>()).add(column);
		}
		
		if (selectedColumnNames != null)
			this.selectedColumnNames.addAll(selectedColumnNames);

		var nameComparator = Comparator.comparing(CyColumn::getNameOnly, Collator.getInstance(Locale.getDefault()));
		
		for (var namespaceColumns : namespaces.values()) {
			namespaceColumns.sort(nameComparator);
		}

		init();
	}
	
	/**
	 * Returns the names of the CyColumns that have been selected.
	 */
	public Set<String> getSelectedColumnNames() {
		return new HashSet<>(selectedColumnNames);
	}
	
	private void init() {
		// TODO For now just remove and recreate everything, can make this smarter later.
		removeAll();
		createContents();
	}
	
	private void createContents() {
		var cardPanel = createCardPanel();
		
		if (namespaces.size() == 1) {
			setLayout(new BorderLayout());
			add(cardPanel, BorderLayout.CENTER);
		} else {
			var namespaceListPanel = createNamespaceListPanel(cardPanel);
			namespaceListPanel.setPreferredSize(new Dimension(180, namespaceListPanel.getPreferredSize().height));
			var splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, namespaceListPanel, cardPanel);
			splitPane.setBorder(BorderFactory.createEmptyBorder());
			setLayout(new BorderLayout());
			add(splitPane, BorderLayout.CENTER);
		}
	}
	
	private JPanel createCardPanel() {
		var cardPanel = new JPanel(new CardLayout());

		for (var namespace : namespaces.keySet()) {
			var columns = namespaces.get(namespace);
			var columnTable = new MutipleSelectColumnTable(columns);
			cardPanel.add(namespace == null ? CYTOSCAPE_CARD : namespace, columnTable);
		}
		
		return cardPanel;
	}
	
	private JPanel createNamespaceListPanel(JPanel cardPanel) {
		var listModel = new DefaultListModel<String>();
		namespaces.keySet().forEach(listModel::addElement);
		
		var namespaceList = new JList<>(listModel);
		namespaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		namespaceList.setCellRenderer(new NamespaceListRenderer());
		namespaceList.setSelectedIndex(0);
		
		var scrollPane = createScrollPane(namespaceList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		namespaceList.addListSelectionListener(evt -> {
			var namespace = namespaceList.getSelectedValue();
			var cardLayout = (CardLayout) cardPanel.getLayout();
			cardLayout.show(cardPanel, namespace == null ? CYTOSCAPE_CARD : namespace);
		});
		
		var panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private static JScrollPane createScrollPane(Component view) {
		var tableScrollPane = new JScrollPane();
		tableScrollPane.setPreferredSize(new Dimension(300, 180));
		tableScrollPane.setViewportView(view);
		
		var bg = UIManager.getColor("Table.background");
		tableScrollPane.setBackground(bg);
		tableScrollPane.getViewport().setBackground(bg);
		
		return tableScrollPane;
	}
	
	private class MutipleSelectColumnTable extends JPanel {
		
		private final List<CyColumn> columns = new ArrayList<>();
		
		private JTable table;
		private JScrollPane tableScrollPane;
		private JButton selectAllButton;
		private JButton selectNoneButton;
		
		private List<Integer> previousSelectedRows;
		
		public MutipleSelectColumnTable(List<CyColumn> columns) {
			init();
			update(columns);
		}
		
		public void update(Collection<CyColumn> columns) {
			this.columns.clear();
			
			if (columns != null) {
				for (var c : columns)
					this.columns.add(c);
			}
			
			updateTable();
			updateSelectionButtons();
		}
		
		private void init() {
			setBackground(getTableScrollPane().getBackground());
			
			makeSmall(getSelectAllButton(), getSelectNoneButton());
			equalizeSize(getSelectAllButton(), getSelectNoneButton());
			
			var layout = new GroupLayout(this);
			setLayout(layout);
			layout.setAutoCreateContainerGaps(false);
			layout.setAutoCreateGaps(false);
			
			layout.setHorizontalGroup(layout.createParallelGroup(CENTER, true)
					.addComponent(getTableScrollPane(), DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(getSelectAllButton(), PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getSelectNoneButton(), PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
							.addContainerGap()
					)
			);
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addComponent(getTableScrollPane(), DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(CENTER, true)
							.addComponent(getSelectAllButton(), PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
							.addComponent(getSelectNoneButton(), PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
					)
			);
		}
		
		private void updateTable() {
			var data = new Object[columns.size()][headerNames.length];
			int i = 0;
			
			for (var c : columns) {
				data[i][SELECTED_COL_IDX] = selectedColumnNames.contains(c.getName());
				data[i][NAME_COL_IDX] = c.getName();
				data[i][TYPE_COL_IDX] = c;
				data[i][SHARED_COL_IDX] = c.getVirtualColumnInfo().isVirtual();
				i++;
			}
			
			var model = new DefaultTableModel(data, headerNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			getTable().setModel(model);
			
			var columnModel = getTable().getColumnModel();
			columnModel.getColumn(SELECTED_COL_IDX).setMaxWidth(22);
			columnModel.getColumn(TYPE_COL_IDX).setMaxWidth(44);
			columnModel.getColumn(SHARED_COL_IDX).setMaxWidth(22);
			columnModel.getColumn(SELECTED_COL_IDX).setResizable(false);
			columnModel.getColumn(TYPE_COL_IDX).setResizable(false);
			columnModel.getColumn(SHARED_COL_IDX).setResizable(false);
		}
		
		private void updateSelectionButtons() {
			int rowCount = table.getRowCount();
			boolean hasUnselected = false;
			boolean hasSelected = false;
			
			for (int i = 0; i < rowCount; i++) {
				boolean selected = (boolean) table.getModel().getValueAt(i, SELECTED_COL_IDX);
				
				if (!hasUnselected)
					hasUnselected = !selected;
				if (!hasSelected)
					hasSelected = selected;
				if (hasUnselected && hasSelected)
					break;
			}
			
			getSelectAllButton().setEnabled(hasUnselected);
			getSelectNoneButton().setEnabled(hasSelected);
		}
		
		private JTable getTable() {
			if (table == null) {
				var defRenderer = new DefaultSelectorTableCellRenderer();
				var checkBoxRenderer = new CheckBoxTableCellRenderer();
				
				table = new JTable(new DefaultTableModel(headerNames, 0)) {
					@Override
					public TableCellRenderer getCellRenderer(int row, int column) {
						if (column == SELECTED_COL_IDX) return checkBoxRenderer;
						return defRenderer;
					}
				};
				table.setTableHeader(null);
				table.setShowGrid(false);
				
				table.getSelectionModel().addListSelectionListener(evt -> {
					if (!evt.getValueIsAdjusting()) {
						// Workaround for preventing a click on the check-box in a selected row
						// from changing the selection when multiple table rows are already selected
						if (table.getSelectedRowCount() > 0)
							previousSelectedRows = Arrays.stream(table.getSelectedRows()).boxed()
									.collect(Collectors.toList());
					}
				});
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						boolean isMac = isMac();
						
						// COMMAND button down on MacOS (or CONTROL button down on another OS) or SHIFT?
						if ((isMac && e.isMetaDown()) || (!isMac && e.isControlDown()) || e.isShiftDown())
							return; // Ignore!
						
					    int col = table.columnAtPoint(e.getPoint());
					    
						if (col == SELECTED_COL_IDX) {
							int row = table.rowAtPoint(e.getPoint());
							
							// Restore previous multiple-row selection first
						    if (previousSelectedRows != null && previousSelectedRows.contains(row)) {
						    	for (int i : previousSelectedRows)
						    		table.addRowSelectionInterval(i, i);
						    }
							
							toggleSelection(row);
							updateSelectionButtons();
						}
					}
				});
			}
			
			return table;
		}
		
		private JScrollPane getTableScrollPane() {
			if (tableScrollPane == null) {
				tableScrollPane = createScrollPane(getTable());
				tableScrollPane.setBorder(
						BorderFactory.createMatteBorder(0, 0, 1, 0, UIManager.getColor("Separator.foreground")));
			}

			return tableScrollPane;
		}
		
		private JButton getSelectAllButton() {
			if (selectAllButton == null) {
				selectAllButton = new JButton("Select All");
				selectAllButton.addActionListener(evt -> setSelectedToAllRows(true));
				
				if (isAquaLAF()) {
					selectAllButton.putClientProperty("JButton.buttonType", "gradient");
					selectAllButton.putClientProperty("JComponent.sizeVariant", "small");
				}
			}
			
			return selectAllButton;
		}
		
		private JButton getSelectNoneButton() {
			if (selectNoneButton == null) {
				selectNoneButton = new JButton("Select None");
				selectNoneButton.addActionListener(evt -> setSelectedToAllRows(false));
				
				if (isAquaLAF()) {
					selectNoneButton.putClientProperty("JButton.buttonType", "gradient");
					selectNoneButton.putClientProperty("JComponent.sizeVariant", "small");
				}
			}
			
			return selectNoneButton;
		}
		
		private void setSelectedToAllRows(boolean selected) {
			int rowCount = getTable().getRowCount();
			
			for (int i = 0; i < rowCount; i++) {
				getTable().setValueAt(selected, i, SELECTED_COL_IDX);
				var name = (String) getTable().getValueAt(i, NAME_COL_IDX);
				
				if (selected)
					selectedColumnNames.add(name);
				else
					selectedColumnNames.remove(name);
			}
			
			getTable().repaint();
			updateSelectionButtons();
		}
		
		private void toggleSelection(int row) {
			boolean selected = (boolean) getTable().getValueAt(row, SELECTED_COL_IDX);
			int[] selectedRows = getTable().getSelectedRows();
			
			if (selectedRows != null) {
				for (int i : selectedRows) {
					var name = (String) getTable().getValueAt(i, NAME_COL_IDX);
					getTable().setValueAt(!selected, i, SELECTED_COL_IDX);
					
					if (selected)
						selectedColumnNames.remove(name);
					else
						selectedColumnNames.add(name);
				}
				
				getTable().repaint();
			}
		}
	}
	
	private class NamespaceListRenderer extends DefaultListCellRenderer {
		
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			var namespace = (String) value;
			var columnPresentation = columnPresentationManager.getColumnPresentation(namespace);
			
			if (value == null)
				value = "Cytoscape";
			
			var component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			var icon = columnPresentation.getNamespaceIcon();
			
			if (icon != null)
				icon = IconManager.resizeIcon(icon, 16);
			
			setIcon(icon);
			
			return component;
		}
	}
	
	private class DefaultSelectorTableCellRenderer extends DefaultTableCellRenderer {

		final Font typeFont;
		final Font defFont;
		
		DefaultSelectorTableCellRenderer() {
			typeFont = new Font("Serif", Font.BOLD, 11); // This font is used as an icon--Don't change it!
			defFont = getFont().deriveFont(getSmallFontSize());
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			setForeground(table.getForeground());

			var bg = UIManager.getColor("Table.background");
			setBackground(isSelected ? UIManager.getColor("Table.selectionBackground") : bg);
			setBorder(CELL_BORDER);

			if (value instanceof Boolean) {
				setFont(iconManager.getIconFont(12));
				setHorizontalAlignment(JLabel.CENTER);

				if (column == SHARED_COL_IDX) {
					setText((boolean) value ? IconManager.ICON_SITEMAP : "");
					setToolTipText((boolean) value ? "Network Collection Column" : null);
				} else {
					setText((boolean) value ? IconManager.ICON_CHECK_SQUARE : IconManager.ICON_SQUARE_O);
				}
			} else {
				if (column == TYPE_COL_IDX && value instanceof CyColumn) {
					var c = (CyColumn) value;
					var adt = AttributeDataType.getAttributeDataType(c.getType(), c.getListElementType());

					if (adt != null) {
						setFont(typeFont);
						setHorizontalAlignment(JLabel.CENTER);
						setText(adt.getText());
						setToolTipText(adt.getDescription());
					}
				} else {
					setFont(defFont);
					setHorizontalAlignment(JLabel.LEFT);
					setToolTipText("" + value);

					if (column == NAME_COL_IDX && value instanceof String) {
						String name = (String) value;
						String[] parts = CyColumn.splitColumnName(name);
						setText(parts[1]);
					}
				}
			}
			
			return this;
		}
	}
	
	private class CheckBoxTableCellRenderer implements TableCellRenderer {
		
		final JPanel panel;
		final JCheckBox chk;
		
		CheckBoxTableCellRenderer() {
			chk = new JCheckBox();
			
			if (isAquaLAF())
				chk.putClientProperty("JComponent.sizeVariant", "mini");
			
			panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(1, 4, 1, 4));
			
			panel.add(chk, BorderLayout.WEST);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			var bg = UIManager.getColor("Table.background");
			chk.setSelected((boolean) value);
			chk.setToolTipText((boolean) value ? "Show" : "Hide");
			chk.setBackground(isSelected ? UIManager.getColor("Table.selectionBackground") : bg);
			panel.setBackground(isSelected ? UIManager.getColor("Table.selectionBackground") : bg);
			panel.setBorder(new EmptyBorder(0, 0, 0, 0));
			
			return panel;
		}
	}
	
	private enum AttributeDataType {
		TYPE_STRING(String.class, null, "ab", "String"),
		TYPE_INTEGER(Integer.class, null, "1", "Integer"),
		TYPE_LONG(Long.class, null, "123", "Long Integer"),
		TYPE_FLOATING(Double.class, null, "1.0", "Floating Point"),
		TYPE_BOOLEAN(Boolean.class, null, "y/n", "Boolean"),
		TYPE_STRING_LIST(List.class, String.class, "[ ab ]", "List of Strings"),
		TYPE_INTEGER_LIST(List.class, Integer.class, "[ 1 ]", "List of Integers"),
		TYPE_LONG_LIST(List.class, Long.class, "[ 123 ]", "List of Long Integers"),
		TYPE_FLOATING_LIST(List.class, Double.class, "[ 1.0 ]", "List of Floating Point Numbers"),
		TYPE_BOOLEAN_LIST(List.class, Boolean.class, "[ y/n ]", "List of Booleans");

		private final Class<?> type;
		private final Class<?> listType;
		private final String text;
		private final String description;

		private AttributeDataType(Class<?> type, Class<?> listType, String text, String description) {
			this.type = type;
			this.listType = listType;
			this.text = text;
			this.description = description;
		}

		public Class<?> getType() {
			return type;
		}

		public Class<?> getListType() {
			return listType;
		}

		public boolean isList() {
			return listType != null;
		}

		public String getText() {
			return text;
		}

		public String getDescription() {
			return description;
		}

		public static AttributeDataType getAttributeDataType(Class<?> type, Class<?> listType) {
			for (var adt : AttributeDataType.values()) {
				if (adt.getType() == type) {
					if (listType == null || listType == adt.getListType())
						return adt;
				}
			}
			
			return TYPE_STRING;
		}
	}
}
