package org.cytoscape.group.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.group.CyGroup;
import org.cytoscape.group.CyGroupManager;


/**
 *  Base class for all derived concrete event classes classes in this package that require a CyNetwork.
 */
class AbstractGroupManagerEvent extends AbstractCyEvent<CyGroupManager> {
	private final CyGroup group;

	AbstractGroupManagerEvent(final CyGroupManager source, final Class<?> listenerClass, final CyGroup group) {
		super(source, listenerClass);

		if (group == null)
			throw new NullPointerException("the \"group\" parameter must never be null.");
		this.group = group;
	}

	public final CyGroup getGroup() {
		return group;
	}
}
