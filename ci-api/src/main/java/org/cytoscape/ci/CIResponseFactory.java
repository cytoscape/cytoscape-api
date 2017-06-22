package org.cytoscape.ci;

import org.cytoscape.ci.model.CIResponse;

public interface CIResponseFactory {
	public <K> CIResponse<K> getCIResponse(K data);
	public <J extends CIResponse<K>, K> J getCIResponse(K data, Class<J> responseClass) throws InstantiationException, IllegalAccessException;
}
