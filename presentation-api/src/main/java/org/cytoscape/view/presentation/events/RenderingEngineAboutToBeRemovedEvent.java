package org.cytoscape.view.presentation.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.presentation.RenderingEngine;
import org.cytoscape.view.presentation.RenderingEngineManager;

/**
 * When presentation (rendered graphics) is destroyed, this event should be
 * fired.
 * @CyAPI.Final.Class
 */
public final class RenderingEngineAboutToBeRemovedEvent extends AbstractCyEvent<RenderingEngineManager> {

	private final RenderingEngine<?> engine;

	/**
	 * Construct an event for destroyed {@linkplain RenderingEngine}.
	 * 
	 * @param source
	 *            source of event. In theory, this can be anything, but in most
	 *            cases, it will be an GUI components.
	 * @param engine
	 *            {@linkplain RenderingEngine} associated with the deleted
	 *            presentation.
	 */
	public RenderingEngineAboutToBeRemovedEvent(final RenderingEngineManager source, final RenderingEngine<?> engine) {
		super(source, RenderingEngineAboutToBeRemovedListener.class);
		this.engine = engine;
	}

	/**
	 * Returns the {@link RenderingEngine} associated with the deleted presentation.
	 * @return the {@link RenderingEngine} associated with the deleted presentation.
	 */
	public RenderingEngine<?> getRenderingEngine() {
		return this.engine;
	}

}
