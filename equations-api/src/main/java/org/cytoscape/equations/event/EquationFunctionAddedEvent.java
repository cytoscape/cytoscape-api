package org.cytoscape.equations.event;

import org.cytoscape.equations.Function;
import org.cytoscape.event.AbstractCyEvent;

public class EquationFunctionAddedEvent extends AbstractCyEvent<Function> {

	public EquationFunctionAddedEvent(Function source) {
		super(source, EquationFunctionAddedListener.class);
	}

}
