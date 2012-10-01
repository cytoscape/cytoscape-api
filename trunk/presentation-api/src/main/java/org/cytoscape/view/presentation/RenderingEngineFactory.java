package org.cytoscape.view.presentation;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;

/**
 * A factory class to create visualization for a given view model. One
 * visualization (presentation) have one rendering engine
 * 
 * @param <T>
 *            Compatible data model for this factory. For example, if this
 *            parameter is set to {@linkplain org.cytoscape.model.CyNetwork}, the factory creates rendering
 *            engine for {@link org.cytoscape.model.CyNetwork} objects.
 * @CyAPI.Api.Interface
 */
public interface RenderingEngineFactory<T> {


	/**
	 * A view model can have multiple presentations. This enable developers
	 * to render multiple View Models in the same display. For example, if
	 * View<CyNetwork> and View<Decoration> are passed to this, both of them
	 * will be rendered in a window, using same rendering engine.
	 * 
	 * @param visualizationContainer
	 *            Window component which contains the rendered view. In most
	 *            cases, {@linkplain java.awt.Window} components in Swing will be used.
	 * @param viewModel
	 *            view-model to be rendered by the RenderingEngine.
	 * 
	 * @return Rendering Engine for visualization on the visualizationContainer.
	 */
	RenderingEngine<T> createRenderingEngine(final Object visualizationContainer, final View<T> viewModel);
	
	
	/**
	 * Returns supported VisualLexicon supported by this rendering engine implementation.
	 * 
	 * @return lexicon for this implementation.
	 */
	VisualLexicon getVisualLexicon();

}
