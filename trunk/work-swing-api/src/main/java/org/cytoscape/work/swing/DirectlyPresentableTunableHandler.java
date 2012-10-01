package org.cytoscape.work.swing;

import java.awt.Window;

public interface DirectlyPresentableTunableHandler {

	/**
	 * This method allows us to bypass the normal tunable support when there is
	 * only one tunable in a Task.
	 */
	boolean setTunableDirectly(Window possibleParent);
	
	/**
	 * If this method returns true, the boolean will be presented
	 * to set directly from GUI without being a part of a parent panel.
	 * This method can be controlled by the param field available in
	 * tunables. For instance, in Boolean tunable if parameter
	 * "ForceSetDirectly"="true" is defined, the tunable will be
	 * set directly.  
	 * @return
	 */
	boolean isForcedToSetDirectly();
	
}
