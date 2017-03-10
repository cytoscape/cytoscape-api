package org.cytoscape.equations.event;

import java.util.Collection;

import org.cytoscape.equations.EquationParser;
import org.cytoscape.equations.Function;
import org.cytoscape.event.AbstractCyPayloadEvent;

public class EquationFunctionRemovedEvent extends AbstractCyPayloadEvent<EquationParser, Function> {

	public EquationFunctionRemovedEvent(EquationParser source, Collection<Function> functions) {
		super(source, EquationFunctionRemovedListener.class, functions);
	}

}
