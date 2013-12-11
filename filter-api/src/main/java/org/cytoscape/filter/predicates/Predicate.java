package org.cytoscape.filter.predicates;

/**
 * Various predicates that can be used to test conditions in
 * {@code Transformers}.  Implementations are free to support any subset of
 * the predicates listed here.
 * 
 * @CyAPI.Enum.Class
 * @CyAPI.InModule filter-api
 */
public enum Predicate {
	/**
	 * A binary predicate that tests whether one parameter is equal to
	 * another.
	 */
	IS,
	
	/**
	 * A binary predicate that tests whether one parameter is not equal
	 * to another.
	 */
	IS_NOT,
	
	/**
	 * A binary predicate that tests whether one parameter is greater than
	 * another.
	 */
	GREATER_THAN,
	
	/**
	 * A binary predicate that tests whether one parameter is greater than
	 * or equal to another.
	 */
	GREATER_THAN_OR_EQUAL,
	
	/**
	 * A binary predicate that tests whether one parameter is less than
	 * another.
	 */
	LESS_THAN,
	
	/**
	 * A binary predicate that tests whether one parameter is less than
	 * or equal to another.
	 */
	LESS_THAN_OR_EQUAL,
	
	/**
	 * A ternary predicate that tests whether one parameter is greater than
	 * or equal to a second parameter, and less than or equal to a third
	 * parameter.
	 */
	BETWEEN,
	
	/**
	 * A binary predicate that tests whether one parameter is contained within
	 * another parameter.
	 */
	CONTAINS,
	
	/**
	 * A binary predicate that tests whether one parameter does not contain
	 * another parameter.
	 */
	DOES_NOT_CONTAIN,
	
	/**
	 * A binary predicate that tests whether one parameter matches a regular
	 * expression.
	 */
	REGEX
}
