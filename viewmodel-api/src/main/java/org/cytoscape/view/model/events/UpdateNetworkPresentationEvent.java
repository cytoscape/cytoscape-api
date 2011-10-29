package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;

/**
 * If something has been changed in the view model, presentation layer should
 * catch the event and update its visualization. This event will be used in such
 * objects, mainly rendering engines, in the presentation layer. This means by
 * firing this event, Cytoscape will invoke "redraw" method in the rendering
 * engine.
 * <p>
 * This event should
 * be created and fired by the view being updated and not anyone else,
 * instead call the {@link CyNetworkView#updateView()} method.
 * @CyAPI.Final.Class
 */
public final class UpdateNetworkPresentationEvent extends
		AbstractCyEvent<CyNetworkView> {

	/**
	 * Event for updating (redrawing) presentation.
     * <p>
     * This event should
     * be created and fired by the view being updated and not anyone else,
     * instead call the {@link CyNetworkView#updateView()} method.
	 * 
	 * @param source source network view-model.  Presentations 
	 * associated with this view-model use this event.
	 */
	public UpdateNetworkPresentationEvent(final CyNetworkView source) {
		super(source, UpdateNetworkPresentationEventListener.class);
	}
}
