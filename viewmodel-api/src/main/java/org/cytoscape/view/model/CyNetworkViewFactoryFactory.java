package org.cytoscape.view.model;

/**
 * <p>
 * A singleton factory that is used to create {@link CyNetworkViewFactory} objects. 
 * </p>
 * <p>
 * Apps that contribute renderers (via NetworkViewRenderer) may provide their own
 * implementation of {@link CyNetworkViewFactory}, or they may acquire a reusable 
 * instance of {@link CyNetworkViewFactory} from this service. The {@link CyNetworkViewFactory}
 * provided by this service may be configured using a provided {@link VisualLexicon} and optionally
 * via a {@link CyNetworkViewConfig} object.
 * </p>
 * <p>
 * The CyNetworkViewFactoryFactory is available as an OSGi service.
 * </p>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 * 
 * @since 3.8
 */
public interface CyNetworkViewFactoryFactory {
	
	/**
	 * <p>
	 * Create a default configuration object for the given VisualLexicon.
	 * If lexicon is null then an empty configuration object is returned.
	 * </p>
	 * <p>
	 * If VisualLexicon is an instance of BasicVisualLexicon then the returned
	 * configuration object will be pre-configured to track node and edge selection.
	 * Selected nodes and edges can be retrieved via {@link CyNetworkViewSnapshot#getTrackedNodes(Object)}
	 * and {@link CyNetworkViewSnapshot#getTrackedEdges(Object)} using the {@link CyNetworkViewConfig#SELECTED_NODES} and
	 * {@link CyNetworkViewConfig#SELECTED_EDGES} keys respectively.
	 * </p>
	 */
	CyNetworkViewConfig createConfig(VisualLexicon lexicon);
	
	
	/**
	 * Creates a CyNetworkViewFactory.
	 * @param config may be null, an empty config will be used if null
	 */
	CyNetworkViewFactory createNetworkViewFactory(VisualLexicon lexicon, String rendererID, CyNetworkViewConfig config);
	
	
	/**
	 * Creates a CyNetworkViewFactory with a default CyNetworkViewConfig that supports standard 2D rendering.
	 */
	default CyNetworkViewFactory createNetworkViewFactory(VisualLexicon lexicon, String rendererID) {
		return createNetworkViewFactory(lexicon, rendererID, createConfig(lexicon));
	}
}
