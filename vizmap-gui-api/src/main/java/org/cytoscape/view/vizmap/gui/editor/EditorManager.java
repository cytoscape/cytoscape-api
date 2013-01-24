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

import java.awt.Component;
import java.beans.PropertyEditor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.VisualProperty;


/**
 * Manages all editor objects for the VizMap GUI.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface EditorManager {
	
	/**
	 * Editor window state
	 */
	public static final String EDITOR_WINDOW_OPENED = "EDITOR_WINDOW_OPENED";

	/**
	 * Tell vizMapper main which editor is disabled/enabled.
	 */
	public static final String EDITOR_WINDOW_CLOSED = "EDITOR_WINDOW_CLOSED";

	/**
	 * Add value editor OSGi services.
	 * 
	 * @param va New value editor to be added.
	 * @param properties OSGi service metadata.
	 */
	@SuppressWarnings("unchecked")
	public void addValueEditor(ValueEditor<?> va, Map properties);


	/**
	 * Remove an editor from manager (through OSGi).
	 * 
	 * @param va editor to be removed.
	 * @param properties OSGi metadata
	 */
	@SuppressWarnings("unchecked")
	public void removeValueEditor(ValueEditor<?> va, Map properties);


	/**
	 * Display value editor to get a new value.
	 * 
	 * @param parentComponent parent GUI component
	 * @param type Visual Property type to be edited
	 * @param initialVal default value for the editor.
	 * 
	 * @return New value fot the given Visual Property.
	 * 
	 * @throws Exception
	 */
	<V> V showVisualPropertyValueEditor(Component parentComponent, VisualProperty<V> type, V initialVal)
			throws Exception;

	
	/**
	 * Returns the continuous editor for the specified visual property.
	 * 
	 * @param vp The visual property.
	 * 
	 * @return the continuous editor for the specified visual property.
	 */
	PropertyEditor getContinuousEditor(final VisualProperty<?> vp);
	
	
	/**
	 * Returns the {@link VisualPropertyEditor} for the given {@link VisualProperty}.
	 * 
	 * @param <V> the generic type of the VisualProperty.
	 * @param vp the {@link VisualProperty} to get the VisualPropertyEditor of.
	 * @return the {@link VisualPropertyEditor} for the given {@link VisualProperty}.
	 */
	<V> VisualPropertyEditor<V> getVisualPropertyEditor(VisualProperty<V> vp);

	/**
	 * Returns editors for individual cells in discrete mapping editor.
	 * 
	 * @return all available cell editors.
	 */
	List<PropertyEditor> getCellEditors();
	
	/**
	 * Returns set of selector for available attributes (table columns).
	 * 
	 * @return all attribute selectors.
 	 */
	Collection<PropertyEditor> getAttributeSelectors();
	
	/**
	 * Returns selector for Mapping Type.  For now, users can select Discrete, Continuous, 
	 * and Passthrough from this object.
	 * 
	 * @return mapping type selector.
	 */
	PropertyEditor getMappingFunctionSelector();


	/**
	 * Get {@link javax.swing.JComboBox} type editor 
	 * 
	 * @param editorName name (ID) of editor
	 * 
	 * @return combobox editor associated with the name.
	 */
	PropertyEditor getDefaultComboBoxEditor(String editorName);
	
	/**
	 * Attribute selector for the given table entry type.
	 * 
	 * @param targetObjectType node, edge, or network.
	 * 
	 * @return selector
	 */
	public PropertyEditor getDataTableComboBoxEditor(final Class<? extends CyIdentifiable> targetObjectType);
	
	
	/**
	 * Returns value editor for the given data type.
	 * 
	 * @param dataType type of data.  They are Color, number, Shape, etc.
	 * 
	 * @return Value editor
	 */
	public <V> ValueEditor<V> getValueEditor(Class<V> dataType);
}
