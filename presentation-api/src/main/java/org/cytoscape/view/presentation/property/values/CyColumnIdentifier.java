package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

/**
 * Interface used to carry and save information that represents a {@link org.cytoscape.model.CyColumn}
 * (usually from a network {@link org.cytoscape.model.CyTable}).
 * It is particularly useful for cases where a column info needs to be saved or used, but the CyColumn itself
 * may not exist yet, or at all.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyColumnIdentifier {

	String getColumnName();
	
//	String getNamespace(); // TODO
}
