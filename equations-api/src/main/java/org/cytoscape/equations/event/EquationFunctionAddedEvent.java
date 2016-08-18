package org.cytoscape.equations.event;

import java.util.Collection;

import org.cytoscape.equations.EquationParser;
import org.cytoscape.equations.Function;
import org.cytoscape.event.AbstractCyPayloadEvent;

public class EquationFunctionAddedEvent extends AbstractCyPayloadEvent<EquationParser, Function> {

	public EquationFunctionAddedEvent(EquationParser source, Collection<Function> functions) {
		super(source, EquationFunctionAddedListener.class, functions);
	}

}
