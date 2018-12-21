package org.cytoscape.view.model;

/**
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkViewFactoryFactory {
	
	CyNetworkViewFactory createNetworkViewFactory(VisualLexicon lexicon, String rendererId);
	
}
