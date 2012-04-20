
package org.cytoscape.application.swing.events;

import org.cytoscape.event.AbstractCyEvent;
import java.util.Properties;

/**
 * An event indicating that system preferences have been updated. 
 * @CyAPI.Final.Class
 */
public final class PreferencesUpdatedEvent extends AbstractCyEvent<Object> {

	private final Properties oldProps;
	private final Properties newProps;

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 * @param oldProps The old properties. 
	 * @param newProps The new properties. 
	 */
	public PreferencesUpdatedEvent(final Object source, final Properties oldProps, final Properties newProps) {
		super(source, PreferencesUpdatedListener.class);
		this.oldProps = oldProps;
		this.newProps = newProps;
	}

	/**
	 * The old properties before they were modified.
	 * @return the old properties before they were modified.
	 */
	public Properties getOldProperties() {
		return oldProps;
	}

	/**
	 * The new properties after being modified.
	 * @return the new properties after being modified.
	 */
	public Properties getNewProperties() {
		return newProps;
	}
}
