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
	 * A binary predicate that tests whether the first parameter is equal to
	 * the second.
	 */
	IS,
	
	/**
	 * A binary predicate that tests whether the first parameter is not equal
	 * to the second.
	 */
	IS_NOT,
	
	/**
	 * A binary predicate that tests whether the first parameter is greater than
	 * the second.
	 */
	GREATER_THAN,
	
	/**
	 * A binary predicate that tests whether the first parameter is greater than
	 * or equal to the second.
	 */
	GREATER_THAN_OR_EQUAL,
	
	/**
	 * A binary predicate that tests whether the first parameter is less than
	 * the second.
	 */
	LESS_THAN,
	
	/**
	 * A binary predicate that tests whether the first parameter is less than
	 * or equal to the second.
	 */
	LESS_THAN_OR_EQUAL,
	
	/**
	 * A ternary predicate that tests whether the first parameter is greater than
	 * or equal to the second parameter, and less than or equal to the third
	 * parameter.
	 */
	BETWEEN,
	
	/**
	 * A binary predicate that tests whether the first parameter is contained within
	 * the second parameter.
	 */
	CONTAINS,
	
	/**
	 * A binary predicate that tests whether the first parameter does not contain
	 * the second parameter.
	 */
	DOES_NOT_CONTAIN,
	
	/**
	 * A binary predicate that tests whether the first parameter matches a regular
	 * expression (second parameter).
	 */
	REGEX
}
