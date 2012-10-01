package org.cytoscape.model;

/**
 * The SavePolicy of an object specifies whether or not it will be saved.
 */
public enum SavePolicy {
	/** The object should not be saved. */
	DO_NOT_SAVE,  
	/** The object should be saved in the session file. */
	SESSION_FILE,
}