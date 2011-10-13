
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

import javax.swing.table.TableCellRenderer;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableEntry;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;


/**
 * Manages currently available editors
 *
 * @author kono
 *
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
	 * Listener for editor displayer service.
	 *
	 * @param va
	 *            DOCUMENT ME!
	 * @param properties
	 *            DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public void addValueEditor(ValueEditor<?> va, Map properties);

	/**
	 * Listener for OSGi service.
	 *
	 * @param va
	 *            DOCUMENT ME!
	 * @param properties
	 *            DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public void removeValueEditor(ValueEditor<?> va, Map properties);
	
	/**
	 * Display discrete value editor for this visual property.
	 * @param <V> DOCUMENT ME!
	 * @param parentComponent DOCUMENT ME!
	 * @param type DOCUMENT ME!
	 * @param initialVal DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public <V> V showVisualPropertyValueEditor(Component parentComponent, VisualProperty<V> type, V initialVal)
	    throws Exception;

	/**
	 * Display continuous value editor.
	 *
	 * <p>
	 * Continuous editor always update mapping automatically, so there is no
	 * return value.
	 * </p>
	 * @param <V> DOCUMENT ME!
	 * @param parentComponent DOCUMENT ME!
	 * @param type DOCUMENT ME!
	 *
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public <V> void showContinuousEditor(Component parentComponent, VisualProperty<V> type)
	    throws Exception;
	
	/**
	 * Returns the {@link VisualPropertyEditor} for the given {@link VisualProperty}.
	 * @param <V> the generic type of the VisualProperty.
	 * @param vp the {@link VisualProperty} to get the VisualPropertyEditor of.
	 * @return the {@link VisualPropertyEditor} for the given {@link VisualProperty}.
	 */
	public <V> VisualPropertyEditor<V> getVisualPropertyEditor(VisualProperty<V> vp);

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public List<PropertyEditor> getCellEditors();
	
	Collection<PropertyEditor> getAttributeSelectors();
	
	PropertyEditor getMappingFunctionSelector();


	/**
	 *  DOCUMENT ME!
	 *
	 * @param editorName DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public PropertyEditor getDefaultComboBoxEditor(String editorName);
	
	/**
	 *  DOCUMENT ME!
	 * @param targetObjectType DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 */
	public PropertyEditor getDataTableComboBoxEditor(final Class<? extends CyTableEntry> targetObjectType);
	
	public <V> ValueEditor<V> getValueEditor(Class<V> dataType);
}
