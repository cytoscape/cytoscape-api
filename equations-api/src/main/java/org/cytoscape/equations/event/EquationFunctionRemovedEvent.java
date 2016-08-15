package org.cytoscape.equations.event;

import org.cytoscape.equations.Function;
import org.cytoscape.event.AbstractCyEvent;

public class EquationFunctionRemovedEvent extends AbstractCyEvent<Function> {

	public EquationFunctionRemovedEvent(Function source) {
		super(source, EquationFunctionRemovedListener.class);
	}

}
