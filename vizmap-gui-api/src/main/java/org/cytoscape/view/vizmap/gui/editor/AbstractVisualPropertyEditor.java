
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

import java.awt.Window;
import java.beans.PropertyEditor;

import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;



/**
 * Abstract implementation of Visual Property Editor.
 * All editors should extend this class.
 * 
 * @param <T> The generic type of this AbstractVisualPropertyEditor.
 * 
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualPropertyEditor<T> implements VisualPropertyEditor<T> {
	
	protected final Class<T> type;
	protected final PropertyEditor propertyEditor;
	protected PropertyEditor continuousEditor;
	protected Window vpValueEditor;
	
	protected TableCellRenderer discreteTableCellRenderer;
	protected TableCellRenderer continuousTableCellRenderer;

	/**
	 * Creates a new AbstractVisualPropertyEditor object.
	 * @param type The type of this AbstractVisualPropertyEditor object.
	 * @param propertyEditor the {@link PropertyEditor} to construct this with.
	 *
	 */
	public AbstractVisualPropertyEditor(final Class<T> type, final PropertyEditor propertyEditor) {
		this.type = type;
		this.propertyEditor = propertyEditor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Class<T> getType() {
		return this.type;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public PropertyEditor getPropertyEditor() {
		return propertyEditor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public T showVisualPropertyValueEditor() {
		if(vpValueEditor == null) {
			// Search value editor repository 
		}
		vpValueEditor.setVisible(true);
		
		//TODO: need new interface for value editor
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public TableCellRenderer getDiscreteTableCellRenderer() {
		return discreteTableCellRenderer;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override public TableCellRenderer getContinuousTableCellRenderer() {
		return continuousTableCellRenderer;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public PropertyEditor getContinuousMappingEditor() throws IllegalArgumentException {
		return continuousEditor;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override public Icon getDefaultIcon(int width, int height) {
		// By default, it does not return actual icon.  This should be implemented child classes.
		return null;
	}
}
