
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

package org.cytoscape.view.vizmap.gui.editor;

import java.awt.Component;
import java.beans.PropertyEditor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyTableEntry;
import org.cytoscape.view.model.VisualProperty;


/**
 * Manages all editor objects for ViaMap GUI.
 * 
 *
 * @CyAPI.Api.Interface
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
	public <V> V showVisualPropertyValueEditor(Component parentComponent, VisualProperty<V> type, V initialVal)
			throws Exception;

	
	/**
	 * Display continuous value editor.
	 *
	 * <p>
	 * Continuous editor always update mapping in real-time, so there is no
	 * return value.
	 * </p>
	 * 
	 * @param parentComponent parent GUI component
	 * @param type Visual Property to be edited
	 * @throws Exception 
	 */
	public <V> void showContinuousEditor(Component parentComponent, VisualProperty<V> type) throws Exception;
	
	
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
	 * Get {@link JComboBox} type editor 
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
	public PropertyEditor getDataTableComboBoxEditor(final Class<? extends CyTableEntry> targetObjectType);
	
	
	/**
	 * Returns value editor for the given data type.
	 * 
	 * @param dataType type of data.  They are Color, number, Shape, etc.
	 * 
	 * @return Value editor
	 */
	public <V> ValueEditor<V> getValueEditor(Class<V> dataType);
}
