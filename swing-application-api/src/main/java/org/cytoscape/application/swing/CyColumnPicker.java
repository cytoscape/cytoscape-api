package org.cytoscape.application.swing;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static org.cytoscape.util.swing.LookAndFeelUtil.isAquaLAF;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Icon;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.cytoscape.model.CyColumn;
import org.cytoscape.util.swing.IconManager;
import org.cytoscape.util.swing.LookAndFeelUtil;

@SuppressWarnings("serial")
public class CyColumnPicker extends JPanel {

	public static final String SHARED_COL_ICON_TEXT = IconManager.ICON_SITEMAP;
	
	private static final int SELECTED_COL_IDX = 0;
	private static final int NAME_COL_IDX = 1;
	private static final int TYPE_COL_IDX = 2;
	private static final int SHARED_COL_IDX = 3;
	
	private 	static final String[] headerNames = {"", "Column Name", "Type", ""};
	
	private static final Border CELL_BORDER = new EmptyBorder(0, 0, 0, 0);
	private static final String CYTOSCAPE_CARD = "::"; // not a valid namespace
	
	
	private final IconManager iconManager;
	private final CyColumnPresentationManager columnPresentationManager;
	
	private final SortedMap<String,List<CyColumn>> namespaces;
	private final Set<String> selectedColumnNames;
	
	
	public CyColumnPicker(IconManager iconManager, CyColumnPresentationManager columnPresentationManager) {
		this.iconManager = Objects.requireNonNull(iconManager);
		this.columnPresentationManager = Objects.requireNonNull(columnPresentationManager);
		
		Collator collator = Collator.getInstance(Locale.getDefault());
		this.namespaces = new TreeMap<>(Comparator.nullsFirst(collator::compare));
		this.selectedColumnNames = new HashSet<>();
	}
	
	
	public void update(Collection<CyColumn> columns, Collection<String> selectedColumnNames) {
		this.namespaces.clear();
		this.selectedColumnNames.clear();
		for(CyColumn column : columns) {
			namespaces.computeIfAbsent(column.getNamespace(), k -> new ArrayList<>()).add(column);
		}
		if(selectedColumnNames != null) {
			this.selectedColumnNames.addAll(selectedColumnNames);
		}
		
		init();
	}
	
	public Set<String> getSelectedColumnNames() {
		return new HashSet<>(selectedColumnNames);
	}
	
	private void init() {
		// TODO For now just remove and recreate everything, can make this smarter later.
		removeAll();
		createContents();
	}
	
	private void createContents() {
		JPanel cardPanel = createCardPanel();
		if(namespaces.size() == 1) {
			setLayout(new BorderLayout());
			add(cardPanel, BorderLayout.CENTER);
		} else {
			JPanel namespaceListPanel = createNamespaceListPanel(cardPanel);
			namespaceListPanel.setPreferredSize(new Dimension(180, namespaceListPanel.getPreferredSize().height));
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, namespaceListPanel, cardPanel);
			splitPane.setBorder(BorderFactory.createEmptyBorder());
			setLayout(new BorderLayout());
			add(splitPane, BorderLayout.CENTER);
		}
	}
	
	private JPanel createCardPanel() {
		JPanel cardPanel = new JPanel(new CardLayout());
		for(String namespace : namespaces.keySet()) {
			List<CyColumn> columns = namespaces.get(namespace);
			MutipleSelectColumnTable columnTable = new MutipleSelectColumnTable(columns);
			cardPanel.add(namespace == null ? CYTOSCAPE_CARD : namespace, columnTable);
		}
		return cardPanel;
	}

	
	private JPanel createNamespaceListPanel(JPanel cardPanel) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		namespaces.keySet().forEach(listModel::addElement);
		
		JList<String> namespaceList = new JList<>(listModel);
		namespaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		namespaceList.setCellRenderer(new NamespaceListRenderer());
		namespaceList.setSelectedIndex(0);
		
		JScrollPane scrollPane = createScrollPane(namespaceList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		
		namespaceList.addListSelectionListener(e -> {
			String namespace = namespaceList.getSelectedValue();
			CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
			cardLayout.show(cardPanel, namespace == null ? CYTOSCAPE_CARD : namespace);
		});
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}
	
	private static JScrollPane createScrollPane(Component view) {
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setPreferredSize(new Dimension(300, 180));
		tableScrollPane.setViewportView(view);
		final Color bg = UIManager.getColor("Table.background");
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
				for (CyColumn c : columns)
					this.columns.add(c);
			}
			
			updateTable();
			updateSelectionButtons();
		}
		
		private void init() {
			setBackground(getTableScrollPane().getBackground());
			
			LookAndFeelUtil.equalizeSize(getSelectAllButton(), getSelectNoneButton());
			
			final GroupLayout layout = new GroupLayout(this);
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
			final Object[][] data = new Object[columns.size()][headerNames.length];
			int i = 0;
			
			for (CyColumn c : columns) {
				data[i][SELECTED_COL_IDX] = selectedColumnNames.contains(c.getName());
				data[i][NAME_COL_IDX] = c.getName();
				data[i][TYPE_COL_IDX] = c;
				data[i][SHARED_COL_IDX] = c.getVirtualColumnInfo().isVirtual();
				i++;
			}
			
			final DefaultTableModel model = new DefaultTableModel(data, headerNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			getTable().setModel(model);
			
			TableColumnModel columnModel = getTable().getColumnModel();
			columnModel.getColumn(SELECTED_COL_IDX).setMaxWidth(22);
			columnModel.getColumn(TYPE_COL_IDX).setMaxWidth(44);
			columnModel.getColumn(SHARED_COL_IDX).setMaxWidth(22);
			columnModel.getColumn(SELECTED_COL_IDX).setResizable(false);
			columnModel.getColumn(TYPE_COL_IDX).setResizable(false);
			columnModel.getColumn(SHARED_COL_IDX).setResizable(false);
		}
		
		private void updateSelectionButtons() {
			final int rowCount = table.getRowCount();
			boolean hasUnselected = false;
			boolean hasSelected = false;
			
			for (int i = 0; i < rowCount; i++) {
				final boolean selected = (boolean) table.getModel().getValueAt(i, SELECTED_COL_IDX);
				
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
				final DefaultSelectorTableCellRenderer defRenderer = new DefaultSelectorTableCellRenderer();
				final CheckBoxTableCellRenderer checkBoxRenderer = new CheckBoxTableCellRenderer();
				
				table = new JTable(new DefaultTableModel(headerNames, 0)) {
					@Override
					public TableCellRenderer getCellRenderer(int row, int column) {
						if (column == SELECTED_COL_IDX) return checkBoxRenderer;
						return defRenderer;
					}
				};
				table.setTableHeader(null);
				table.setShowGrid(false);
				
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							// Workaround for preventing a click on the check-box in a selected row
							// from changing the selection when multiple table rows are already selected
							if (table.getSelectedRowCount() > 0)
								previousSelectedRows = Arrays.stream(table.getSelectedRows()).boxed()
										.collect(Collectors.toList());
						}
					}
				});
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						final boolean isMac = LookAndFeelUtil.isMac();
						
						// COMMAND button down on MacOS (or CONTROL button down on another OS) or SHIFT?
						if ((isMac && e.isMetaDown()) || (!isMac && e.isControlDown()) || e.isShiftDown())
							return; // Ignore!
						
					    final int col = table.columnAtPoint(e.getPoint());
					    
						if (col == SELECTED_COL_IDX) {
							final int row = table.rowAtPoint(e.getPoint());
							
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
				tableScrollPane.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(0, 2, 0, 2),
						BorderFactory.createMatteBorder(0, 0, 1, 0, UIManager.getColor("Separator.foreground"))
				));
			}

			return tableScrollPane;
		}
		
		private JButton getSelectAllButton() {
			if (selectAllButton == null) {
				selectAllButton = new JButton("Select All");
				selectAllButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						setSelectedToAllRows(true);
					}
				});
				
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
				selectNoneButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						setSelectedToAllRows(false);
					}
				});
				
				if (isAquaLAF()) {
					selectNoneButton.putClientProperty("JButton.buttonType", "gradient");
					selectNoneButton.putClientProperty("JComponent.sizeVariant", "small");
				}
			}
			
			return selectNoneButton;
		}
		
		private void setSelectedToAllRows(final boolean selected) {
			final int rowCount = getTable().getRowCount();
			
			for (int i = 0; i < rowCount; i++) {
				getTable().setValueAt(selected, i, SELECTED_COL_IDX);
				final String name = (String) getTable().getValueAt(i, NAME_COL_IDX);
				
				if (selected)
					selectedColumnNames.add(name);
				else
					selectedColumnNames.remove(name);
			}
			
			getTable().repaint();
			updateSelectionButtons();
		}
		
		private void toggleSelection(final int row) {
			final boolean selected = (boolean) getTable().getValueAt(row, SELECTED_COL_IDX);
			final int[] selectedRows = getTable().getSelectedRows();
			
			if (selectedRows != null) {
				for (int i : selectedRows) {
					final String name = (String) getTable().getValueAt(i, NAME_COL_IDX);
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
			String namespace = (String) value;
			CyColumnPresentation columnPresentation = columnPresentationManager.getColumnPresentation(namespace);
			if(value == null)
				value = "Cytoscape";
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Icon icon = columnPresentation.getNamespaceIcon();
			if(icon != null) {
				icon = IconManager.resizeIcon(icon, 16);
			}
			setIcon(icon);
			return component;
		}
	}
	
	
	private class DefaultSelectorTableCellRenderer extends DefaultTableCellRenderer {
		
		final Font typeFont;
		final Font defFont;
		
		DefaultSelectorTableCellRenderer() {
			typeFont = new Font("Serif", Font.BOLD, 11); // This font is used as an icon--Don't change it!
			defFont = getFont().deriveFont(LookAndFeelUtil.getSmallFontSize());
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			setForeground(table.getForeground());
			
			final Color bg = UIManager.getColor("Table.background");
			setBackground(isSelected ? UIManager.getColor("Table.selectionBackground") : bg);
			setBorder(CELL_BORDER);
			
			if (value instanceof Boolean) {
				setFont(iconManager.getIconFont(12));
				setHorizontalAlignment(JLabel.CENTER);
				
				if (column == SHARED_COL_IDX) {
					setText((boolean)value ? SHARED_COL_ICON_TEXT : "");
					setToolTipText((boolean)value ? "Network Collection Column" : null);
				} else {
					setText((boolean)value ? IconManager.ICON_CHECK_SQUARE : IconManager.ICON_SQUARE_O);
				}
			} else {
				 if (column == TYPE_COL_IDX && value instanceof CyColumn) {
					 CyColumn c = (CyColumn) value;
					 AttributeDataType adt = AttributeDataType.getAttributeDataType(c.getType(), c.getListElementType());
					
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
					if(column == NAME_COL_IDX && value instanceof String) {
						String name = (String)value;
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
			chk.putClientProperty("JComponent.sizeVariant", "mini"); // Aqua LAF only
			panel = new JPanel(new BorderLayout());
			panel.add(chk, BorderLayout.WEST);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			final Color bg = UIManager.getColor("Table.background");
			chk.setSelected((boolean)value);
			chk.setToolTipText((boolean)value ? "Show" : "Hide");
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
		
	    private AttributeDataType(final Class<?> type, final Class<?> listType, final String text, final String description) {
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
	    
	    public static AttributeDataType getAttributeDataType(final Class<?> type, final Class<?> listType) {
		    	for (AttributeDataType adt : AttributeDataType.values()) {
		    		if (adt.getType() == type) {
		    			if (listType == null || listType == adt.getListType())
		    				return adt;
		    		}
		    	}
		    	return TYPE_STRING;
	    }
	}
}
