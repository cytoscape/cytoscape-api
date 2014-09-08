package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.util.Set;

/**
 * This is a special {@link VisualPropertyValue} that allows a {@link org.cytoscape.view.model.VisualProperty} value
 * to be specified as depending on one or more {@link CyColumn}s.
 * That way, if the columns or column values change, Cytoscape can also update the {@link org.cytoscape.view.model.View}s
 * that have the corresponding visual property values.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface MappableVisualPropertyValue extends VisualPropertyValue {

	/**
	 * @return A set of {@link CyColumnIdentifier} objects that contain the information about all the
	 * {@link org.cytoscape.model.CyColumn}s this visual property value depends on.
	 */
	Set<CyColumnIdentifier> getMappedColumnNames();
}
