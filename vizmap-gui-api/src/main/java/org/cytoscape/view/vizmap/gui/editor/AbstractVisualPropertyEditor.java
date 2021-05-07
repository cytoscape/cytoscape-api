package org.cytoscape.view.vizmap.gui.editor;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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
 * @CyAPI.InModule vizmap-gui-api
 */
public abstract class AbstractVisualPropertyEditor<T> implements VisualPropertyEditor<T> {

	/**
	 * The type of the property editor.
	 */
	private final Class<T> type;

	/**
	 * The property editor.
	 */
	protected final PropertyEditor propertyEditor;

	/**
	 * The cell renderer for discrete mappings.
	 */
	protected TableCellRenderer discreteTableCellRenderer;

	
	private final ContinuousEditorType continuousEditorType;

	private final ContinuousMappingCellRendererFactory cellRendererFactory;

	/**
	 * Creates a new AbstractVisualPropertyEditor object.
	 * @param type The type of this property editor.
	 * @param propertyEditor the {@link PropertyEditor} to construct this with.
	 * @param continuousEditorType the {@link ContinuousEditorType} to construct this with.
	 */
	public AbstractVisualPropertyEditor(final Class<T> type, final PropertyEditor propertyEditor, ContinuousEditorType continuousEditorType, ContinuousMappingCellRendererFactory cellRendererFactory) {
		this.type = type;
		this.propertyEditor = propertyEditor;
		this.continuousEditorType = continuousEditorType;
		this.cellRendererFactory = cellRendererFactory;
	}

	@Override 
	public Class<T> getType() {
		return this.type;
	}


	@Override 
	public PropertyEditor getPropertyEditor() {
		return propertyEditor;
	}

	@Override 
	public TableCellRenderer getDiscreteTableCellRenderer() {
		return discreteTableCellRenderer;
	}
	
	@Override
	public TableCellRenderer getContinuousTableCellRenderer(final ContinuousMappingEditor<? extends Number, T> continuousMappingEditor) {
		return cellRendererFactory.createTableCellRenderer(continuousMappingEditor);
	}


	@Override
	public ContinuousEditorType getContinuousEditorType() {
		return continuousEditorType;
	}

	@Override 
	public Icon getDefaultIcon(int width, int height) {
		// By default, this class does not return actual icon.  This should be implemented by child class.
		return null;
	}
}
