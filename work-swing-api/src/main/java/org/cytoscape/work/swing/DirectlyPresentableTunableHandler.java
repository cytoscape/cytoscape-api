package org.cytoscape.work.swing;

import java.awt.Window;

public interface DirectlyPresentableTunableHandler {

	/**
	 * This method allows us to bypass the normal tunable support when there is
	 * only one tunable in a Task.
	 */
	boolean setTunableDirectly(Window possibleParent);
	
}
