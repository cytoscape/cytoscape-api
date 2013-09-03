package org.cytoscape.view.vizmap.gui.editor;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.beans.PropertyEditor;

import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;

import org.cytoscape.view.model.VisualProperty;

/**
 * Facade of all editor-related objects for a Visual Property.
 * 
 * If an app developer adds a custom visual property, they should implement this
 * in the presentation layer.
 * 
 * @param <T> Type of object managed in the Visual Prop.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface VisualPropertyEditor2<T> {

	/**
	 * Returns the type of object managed in the Visual property.
	 *
	 * @return the type of object managed in the Visual property.
	 */
	Class<T> getType();


	/**
	 * Returns type of Continuous Editor.
	 * 
	 * @return type of {@link ContinuousMappingEditor}
	 */
	ContinuousEditorType getContinuousEditorType();

	/**
	 * Returns {@link PropertyEditor} object for this data type.
	 * @return {@link PropertyEditor} object for this data type.
	 */
	<T> PropertyEditor getPropertyEditor(VisualProperty<T> vizProp);


	/**
	 * A custom cell renderer for Discrete table cells. 
	 * 
	 * @return a TableCellRenderer Discrete table cells. 
	 */
	TableCellRenderer getDiscreteTableCellRenderer();
	
	/**
	 * A custom cell renderer for Continuous table cells. 
	 * 
	 * @return a TableCellRenderer Continuous table cells. 
	 */
	TableCellRenderer getContinuousTableCellRenderer(ContinuousMappingEditor<? extends Number, T> continuousMappingEditor);

	/**
	 * This is for default view editor.
	 * 
	 * @param width The width of the desired Icon.
	 * @param height The height of the desired Icon.
	 * 
	 * @return An icon of the specified width and height. 
	 */
	Icon getDefaultIcon(int width, int height);
}
