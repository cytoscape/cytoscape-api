package org.cytoscape.application.events;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.RenderingEngine;

public final class SetCurrentRenderingEngineEvent extends
		AbstractCyEvent<CyApplicationManager>{
	
	private final RenderingEngine<CyNetwork> engine;

	/**
	 * Constructor.
	 * 
	 * @param source
	 *            The application manager firing this event.
	 * @param engine
	 *            The rendering engine (presentation) that has been set as current.
	 */
	public SetCurrentRenderingEngineEvent(final CyApplicationManager source, final RenderingEngine<CyNetwork> engine) {
		super(source, SetCurrentRenderingEngineListener.class);
		this.engine = engine;
	}
	
	public RenderingEngine<CyNetwork> getRenderingEngine() {
		return this.engine;
	}
}