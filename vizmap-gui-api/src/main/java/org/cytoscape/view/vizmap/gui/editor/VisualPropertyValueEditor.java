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

import java.awt.Component;

import org.cytoscape.view.model.VisualProperty;

/**
 * Provides a GUI Editor for a data type.
 * 
 * To support new data type, this should be implemented.
 *
 * This interface is an exact copy of {@link ValueEditor}, but with
 * the additional parameter for {@code showEditor} that informs the
 * {@code VisualPropertyValueEditor} instance of the visual property
 * that is being edited. This is useful for editors that need to know
 * the boundaries of the visual property.
 *
 * @param <V> target data type for this editor.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface VisualPropertyValueEditor<V> {
	
	/**
	 * Display the editor and get a new value.
	 * @param parent the parent Component of this editor.
	 * @param initialValue the initial value for the value editor dialogue.
     * @param vizPropBeingEdited The visual property whose value is being edited--useful for determining the visual property's boundaries.
	 * 
	 * @return the value generated from the value editor dialogue or null.
	 */
	<S extends V> V showEditor(Component parent, S initialValue, VisualProperty<S> vizPropBeingEdited);
	
	
//	<S extends V> V showEditor(Component parent, S initialValue, VisualProperty<S> vizPropBeingEdited, RenderingEngine<?> renderingEngine);
//	default <S extends V> V showEditor(Component parent, S initialValue, VisualProperty<S> vizPropBeingEdited, RenderingEngine<?> renderingEngine) {
//		return showEditor(parent, initialValue, vizPropBeingEdited);
//	}

	
	/**
	 * Get type of the value to be edited.
	 * 
	 * @return the type of value returned by the value editor.
	 */
	Class<V> getValueType();

}
