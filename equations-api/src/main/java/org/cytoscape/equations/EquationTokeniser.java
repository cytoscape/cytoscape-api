package org.cytoscape.equations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * Tokeniser for a string representing an equation. Each token object represents the 
 * meaning of a non-overlapping substring of the equation string.
 * 
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule equations-api
 * 
 * @since 3.9
 */
public interface EquationTokeniser {
	
	/**
	 * Returns an iterator of Token objects.
	 * 
	 */
	public Iterator<Token> getTokenIterator(String equationAsString);
	
	/**
	 * Returns a list of Token objects.
	 */
	default public List<Token> getTokenList(String equationAsString) {
		List<Token> list = new ArrayList<>();
		Iterator<Token> iter = getTokenIterator(equationAsString);
		iter.forEachRemaining(list::add);
		return list;
	}

}
