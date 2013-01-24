package org.cytoscape.view.vizmap.gui;

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
