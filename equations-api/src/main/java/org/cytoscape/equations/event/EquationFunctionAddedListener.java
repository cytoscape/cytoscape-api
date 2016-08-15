package org.cytoscape.equations.event;

import org.cytoscape.event.CyListener;

public interface EquationFunctionAddedListener extends CyListener {
	public void handleEvent(EquationFunctionAddedEvent e);
}
