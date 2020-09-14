package org.cytoscape.equations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface EquationTokeniser {
	
	public Iterator<Token> getTokenIterator(String equationAsString);
	
	default public List<Token> getTokenList(String equationAsString) {
		List<Token> list = new ArrayList<>();
		Iterator<Token> iter = getTokenIterator(equationAsString);
		iter.forEachRemaining(list::add);
		return list;
	}

}
