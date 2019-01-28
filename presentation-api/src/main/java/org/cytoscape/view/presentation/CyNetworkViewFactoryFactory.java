package org.cytoscape.view.presentation;

import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;

/**
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyNetworkViewFactoryFactory {
	
	CyNetworkViewFactory createNetworkViewFactory(BasicVisualLexicon lexicon, String rendererId);
	
}
