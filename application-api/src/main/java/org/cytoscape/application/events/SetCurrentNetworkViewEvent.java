package org.cytoscape.application.events;


import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;


/**
 * An event indicating that a network view has been set to current.
 */
public final class SetCurrentNetworkViewEvent extends AbstractCyEvent<CyApplicationManager> {


	private final CyNetworkView view;

	/**
	 * Returns the network view associated with this event. The view returned may be null!
	 * @return the network view associated with this event.
	 */
	final public CyNetworkView getNetworkView() {
		return view;
	}


	/**
	 * Constructor.
	 * @param source The application manager firing this event.
	 * @param view The network view that has been set as current.
	 */
	public SetCurrentNetworkViewEvent(final CyApplicationManager source, final CyNetworkView view) {
		super(source, SetCurrentNetworkViewListener.class);
		this.view = view;
	}
}
