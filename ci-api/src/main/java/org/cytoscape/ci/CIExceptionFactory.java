package org.cytoscape.ci;

import org.cytoscape.ci.model.CIError;

/**
 * 
 * @author David Otasek (dotasek.dev@gmail.com)
 *
 */
public interface CIExceptionFactory {
	public RuntimeException getCIException(int httpStatus, CIError[] errors);
	public <K> RuntimeException getCIException(int httpStatus, K data, CIError[] errors);
}
