package org.cytoscape.view.model;

/**
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyNetworkViewFactoryFactory {
	
	/**
	 * Create a default config object for the given VisualLexicon.
	 * If lexicon is null then an empty config object is returned.
	 */
	CyNetworkViewConfig createConfig(VisualLexicon lexicon);
	
	/**
	 * Create and return an empty confing object.
	 */
	default CyNetworkViewConfig createConfig() {
		return createConfig(null);
	}
	
	CyNetworkViewFactory createNetworkViewFactory(VisualLexicon lexicon, String rendererId, CyNetworkViewConfig config);
	
	default CyNetworkViewFactory createNetworkViewFactory(VisualLexicon lexicon, String rendererId) {
		return createNetworkViewFactory(lexicon, rendererId, null);
	}
}
