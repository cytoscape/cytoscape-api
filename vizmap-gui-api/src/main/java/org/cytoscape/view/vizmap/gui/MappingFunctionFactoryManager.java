package org.cytoscape.view.vizmap.gui;

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

import java.util.Collection;

import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;

/**
 * Manages factories to create actual mappings (discrete/passthrough/continuous).
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface MappingFunctionFactoryManager {
	
	/**
	 * Returns all available factories.
	 * 
	 * @return all mapping function factories.
	 */
	Collection<VisualMappingFunctionFactory> getFactories();
	
	/**
	 * Factory for the specific mapping type.
	 * 
	 * @param mappingType
	 * @return mapping factory for the given mapping type.
	 */
	VisualMappingFunctionFactory getFactory(Class<?> mappingType);
}
