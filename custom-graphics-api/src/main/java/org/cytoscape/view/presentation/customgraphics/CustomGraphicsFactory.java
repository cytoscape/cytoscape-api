package org.cytoscape.view.presentation.customgraphics;

import java.util.List;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * This interface provides a factory for creating custom graphics
 * providers for a particular node view in a particular network.
 * All custom graphics providers are expected to implement the
 * {@link org.cytoscape.view.presentation.customgraphics.CustomGraphics}
 * interface.  Other interfaces may be implemented also.  The usual
 * way to register a {@link CustomGraphicsFactory} is to provide an implementation
 * of this interface and register it as part of the CyActivator for your App.
 * {@link org.cytoscape.view.presentation.RenderingEngine}s will read the
 * {@link java.util.Properties} object provided as part of the registration
 * to determine default position, zoom action, etc.  See 
 * {@link org.cytoscape.view.presentation.customgraphics.CustomGraphics} for
 * the default keys.
 */
public interface CustomGraphicsFactory {
	/**
	 * Create a new custom graphics.  A
	 * {@link org.cytoscape.view.presentation.RenderingEngine} will provide
	 * an ordered list of types that it supports in the "requestedTypes" list,
	 * with the first in the list being the highest quality rendering that this
	 * {@link org.cytoscape.view.presentation.RenderingEngine} can provide.
	 * The {@link org.cytoscape.view.presentation.RenderingEngine} will determine
	 * what this particular custom graphics class supports.
	 *
	 * @param netView the {@link org.cytoscape.view.model.CyNetworkView} this applies to.  
	 *                It can not be null.
	 * @param nodeView the {@link org.cytoscape.model.CyNode} {@link org.cytoscape.view.model.View}
	 *                 this applies to.
	 * @param requestedTypes the types of CustomGraphics this 
	 *                       {@link org.cytoscape.view.presentation.RenderingEngine} supports.
	 * @return a {@link org.cytoscape.view.presentation.CustomGraphics} object 
	 *         for this network and node view.
	 */
	public CustomGraphics createCustomGraphics(CyNetworkView netView,
	                                           View<CyNode> nodeView,
	                                           List<Class<?>> requestedTypes);
}
