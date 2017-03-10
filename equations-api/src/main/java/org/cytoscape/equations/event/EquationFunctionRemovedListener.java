package org.cytoscape.equations.event;

import org.cytoscape.event.CyListener;

public interface EquationFunctionRemovedListener extends CyListener {
	public void handleEvent(EquationFunctionRemovedEvent e);
}
