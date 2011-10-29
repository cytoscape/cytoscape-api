
package org.cytoscape.application.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event fired immediately before Cytoscape will be shutdown. This
 * event provides methods for listeners processing this event to
 * abort the shutdown. This event should only be fired synchronously
 * to allow all listeners time to clean up.
 * @CyAPI.Final.Class
 */
public final class CytoscapeShutdownEvent extends AbstractCyEvent<Object> {

	private String reason;

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 */
	public CytoscapeShutdownEvent(final Object source) {
		super(source, CytoscapeShutdownListener.class);
		reason = null;
	}

	/**
	 * A callback to the firing class that allows a listener to
	 * abort the shutdown.  This can cause conflicts if abused.
	 * @param why A user comprehensible message describing why the shutdown
	 * was aborted.
	 */
	public void abortShutdown(final String why) {
		if ( why == null || why.equals("") )
			return;

		reason = why;
	}

	/**
	 * Returns the reason that the application should not be shut down.
	 * @return The reason that the application should not be shut down.
	 */
	public String whyNot() {
		return reason;
	}

	/**
	 * Returns true if no reason is provided to abort the shutdown and false
	 * if anyone processing this event wants to prevent shutdown.
	 * @return true if no reason is provided to abort the shutdown and false
	 * if anyone processing this event wants to prevent shutdown.
	 */
	public boolean actuallyShutdown() {
		return (reason == null || reason.length() <= 0);
	}
}
