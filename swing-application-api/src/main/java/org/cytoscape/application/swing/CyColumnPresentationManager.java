package org.cytoscape.application.swing;

import java.util.function.Consumer;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.cytoscape.model.CyColumn;
import org.cytoscape.util.swing.IconManager;

/**
 * This class provides access to registered CyColumnPresentation service objects.
 * 
 * @see org.cytoscape.model.CyColumn
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CyColumnPresentationManager {

	/**
	 * Returns a CyColumnPresentation for the given namespace. If null is given then the
	 * default presentation used for Cytoscape columns is returned. If a CyColumnPresentation
	 * has not been registered for the given namespace then a dummy CyColumnPresentation
	 * is returned. Does not return null.
	 */
	CyColumnPresentation getColumnPresentation(String namespace);
	
	/**
	 * Sets the text and icon of the given JLabel so that it represents
	 * the given column in an esthetically pleasing way.
	 * @throws NullPointerException if column or label are null
	 */
	default void setLabel(String columnName, JLabel label) {
		setLabel(columnName, label::setIcon, label::setText);
	}
	
	/**
	 * Calls the given Consumer callbacks with an icon and text string that represents
	 * the given column in an esthetically pleasing way.
	 * @throws NullPointerException if column is null
	 */
	default void setLabel(String columnName, Consumer<Icon> setIcon, Consumer<String> setText) {
		String[] parts = CyColumn.splitColumnName(columnName);
		String namespace = parts[0];
		String name = parts[1];
		
		// icon
		CyColumnPresentation pres = getColumnPresentation(namespace);
		Icon icon = pres.getNamespaceIcon();
		if (icon != null && setIcon != null) {
			setIcon.accept(IconManager.resizeIcon(icon,16));
		}
		
		// text
		if(setText != null) {
			setText.accept(name);
		}
	}
	
}
