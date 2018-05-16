package org.cytoscape.application.swing;

import java.awt.Component;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

import org.cytoscape.model.CyColumn;

@SuppressWarnings("serial")
public class CyColumnComboBox extends JComboBox<CyColumn> {
	
	private final CyColumnPresentationManager columnPresentationManager;
	private final Predicate<CyColumn> enabledPredicate;
	
	public CyColumnComboBox(CyColumnPresentationManager columnPresentationManager, Collection<CyColumn> columns) {
		this(columnPresentationManager, columns, c -> true);
	}
	
	public CyColumnComboBox(CyColumnPresentationManager columnPresentationManager, Collection<CyColumn> columns, Predicate<CyColumn> enabledPredicate) {
		this.columnPresentationManager = Objects.requireNonNull(columnPresentationManager);
		this.enabledPredicate = Objects.requireNonNull(enabledPredicate);
		setRenderer(new NamespaceRenderer());
		columns.forEach(this::addItem);
	}
	
	
	/**
	 * Override getSelectedItem() and use covariant return type to change return type to CyColumn.
	 */
	@Override
	public CyColumn getSelectedItem() {
		return (CyColumn) super.getSelectedItem();
	}
	
	private class NamespaceRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if(value == null) {
				setText("-- None --");
			} else {
				CyColumn column = (CyColumn) value;
				columnPresentationManager.setLabel(column.getName(), this);
				setEnabled(enabledPredicate.test(column));
			}
			return component;
		}
	}
	
	

}
