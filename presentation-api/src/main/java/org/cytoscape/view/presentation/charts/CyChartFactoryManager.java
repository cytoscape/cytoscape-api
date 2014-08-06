package org.cytoscape.view.presentation.charts;

import java.util.Collection;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

public interface CyChartFactoryManager {

	Collection<CyChartFactory<? extends CustomGraphicLayer>> getAllCyChartFactories();
	
	CyChartFactory<? extends CustomGraphicLayer> getCyChartFactory(String factoryId);
	
	CyChartFactory<? extends CustomGraphicLayer> getCyChartFactory(Class<? extends CyChart<? extends CustomGraphicLayer>> cls);
	
	CyChartEditorFactory<? extends CustomGraphicLayer> getCyChartEditorFactory(Class<? extends CyChart<? extends CustomGraphicLayer>> cls);
}
