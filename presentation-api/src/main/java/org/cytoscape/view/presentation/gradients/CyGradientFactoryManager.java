package org.cytoscape.view.presentation.gradients;

import java.util.Collection;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

public interface CyGradientFactoryManager {

	Collection<CyGradientFactory<? extends CustomGraphicLayer>> getAllCyGradientFactories();
	
	CyGradientFactory<? extends CustomGraphicLayer> getCyGradientFactory(String factoryId);
	
	CyGradientFactory<? extends CustomGraphicLayer> getCyGradientFactory(Class<? extends CyGradient<? extends CustomGraphicLayer>> cls);
	
	CyGradientEditorFactory<? extends CustomGraphicLayer> getCyGradientEditorFactory(Class<? extends CyGradient<? extends CustomGraphicLayer>> cls);
}
