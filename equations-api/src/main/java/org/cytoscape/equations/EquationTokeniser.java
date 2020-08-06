package org.cytoscape.equations;

import java.util.Iterator;

public interface EquationTokeniser {
	
	public Iterator<Token> getTokenIterator(String equationAsString);

}
