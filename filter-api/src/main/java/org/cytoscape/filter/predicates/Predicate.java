package org.cytoscape.filter.predicates;

public enum Predicate {
	// Common
	IS,
	IS_NOT,
	
	// Number-specific
	GREATER_THAN,
	GREATER_THAN_OR_EQUAL,
	LESS_THAN,
	LESS_THAN_OR_EQUAL,
	BETWEEN,
	
	// String-specific
	CONTAINS,
	DOES_NOT_CONTAIN,
	REGEX
}
