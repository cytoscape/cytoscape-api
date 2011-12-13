package org.cytoscape.biopax;

import org.biopax.paxtools.model.Model;
import org.cytoscape.work.TaskMonitor;

/**
 * This API is provisional and is subject to change at any time.
 * 
 * @CyAPI.Api.Interface
 */
public interface BioPaxMapperFactory {

	/**
	 * Creates a new instance of the BioPAX to Cytoscape network/view mapper.
	 * 
	 * @param biopaxModel
	 * @param taskMonitor
	 * @return
	 */
	BioPaxMapper createBioPaxMapper(Model biopaxModel, TaskMonitor taskMonitor);

}
