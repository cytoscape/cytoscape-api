package org.cytoscape.application.swing;

import java.awt.Component;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

import org.cytoscape.model.CyColumn;

/**
 * An implementation of JComboBox that displays a list of CyColumns with their namespace icons.
 *
 * @CyAPI.InModule swing-application-api
 */
@SuppressWarnings("serial")
public class CyColumnComboBox extends JComboBox<CyColumn> {
	
	private final CyColumnPresentationManager columnPresentationManager;
	private final Predicate<CyColumn> enabledPredicate;
	
	/**
	 * Creates a CyColumnComboBox.
	 * @param columnPresentationManager CyColumnPresentationManager OSGi service.
	 * @param columns Collection of CyColumn objects to display. If this collection contains null it will be displayed as "-- None --".
	 * @param enabledPredicate A predicate used to determine which CyColumn items should be enabled or disabled.
	 * @throws NullPointerException If any parameter is null.
	 */
	public CyColumnComboBox(CyColumnPresentationManager columnPresentationManager, Collection<CyColumn> columns, Predicate<CyColumn> enabledPredicate) {
		this.columnPresentationManager = Objects.requireNonNull(columnPresentationManager);
		this.enabledPredicate = Objects.requireNonNull(enabledPredicate);
		setRenderer(new NamespaceRenderer());
		columns.forEach(this::addItem);
	}
	
	/**
	 * Creates a CyColumnComboBox with all entries enabled.
	 * @param columnPresentationManager CyColumnPresentationManager OSGi service.
	 * @param columns Collection of CyColumn objects to display. If this collection contains null it will be displayed as "-- None --".
	 * @throws NullPointerException If any parameter is null.
	 */
	public CyColumnComboBox(CyColumnPresentationManager columnPresentationManager, Collection<CyColumn> columns) {
		this(columnPresentationManager, columns, c -> true);
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
