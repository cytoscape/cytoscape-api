package org.cytoscape.view.vizmap.gui;

import java.util.Collection;

import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;

public interface MappingFunctionFactoryManager {
	
	Collection<VisualMappingFunctionFactory> getFactories();
	
	VisualMappingFunctionFactory getFactory(Class<?> mappingType);
}
