package org.cytoscape.ci.model;

import java.util.List;

/**
 * 
 * @author David Otasek (dotasek.dev@gmail.com)
 *
 * @param <T>
 */
public class CIResponse<T> {
	public T data;
	public List<CIError> errors;
}
