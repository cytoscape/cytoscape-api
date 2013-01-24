package org.cytoscape.view.presentation.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.presentation.RenderingEngine;
import org.cytoscape.view.presentation.RenderingEngineManager;

/**
 * When new presentation is created, its factory always creates a {@linkplain RenderingEngine}).
 * The engine should be added to {@link RenderingEngineManager} and it fires this event.
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class RenderingEngineAddedEvent extends
		AbstractCyEvent<RenderingEngineManager> {

	private final RenderingEngine<?> engine;

	/**
	 * Create an event for newly created presentation.
	 * 
	 * @param source manager holding reference to this new rendering engine
	 * @param engine New {@linkplain RenderingEngine} just created.
	 */
	public RenderingEngineAddedEvent(final RenderingEngineManager source,
			final RenderingEngine<?> engine) {
		super(source, RenderingEngineAddedListener.class);
		this.engine = engine;

	}

	/**
	 * Get new rendering engine.
	 * 
	 * @return new rendering engine.
	 */
	public RenderingEngine<?> getRenderingEngine() {
		return this.engine;
	}

}
