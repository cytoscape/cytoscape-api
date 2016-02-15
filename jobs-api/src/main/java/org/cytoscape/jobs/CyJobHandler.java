package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.cytoscape.work.TaskMonitor;

/**
 * Apps are expected to implement this interface to receive notifications
 * about job completions.  To be informed about the status of a particular
 * job, CyJobHandlers should be registered as services so that jobs can be
 * reassociated with their handlers on session restore.  Note that classes
 * <b>should</b> implement the {@link #loadData(CyJob, TaskMonitor) loadData} 
 * method.  This method will
 * be called by the {@link CyJobManager} when a job transitions to status
 * of {@link CyJobStatus.Status#FINISHED FINISHED} and the user has indicated that they
 * are ready to load the data.  This will be done as part of a 
 * {@link org.cytoscape.work.Task}
 * to avoid potential synchronization issues. 
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJobHandler {
	/**
	 * This method is called whenever the status of a job changes.
	 *
	 * @param job the {@link CyJob} who's status has changed
	 * @param status the new job {@link CyJobStatus}
	 */
	default public void handleJob(CyJob job, CyJobStatus status) { };

	/**
	 * This method is called when when the job status transitions
	 * to {@link CyJobStatus.Status#FINISHED FINISHED} and the user has indicated
	 * that they are ready to load the data from the task.  Typically, this
	 * will result in the {@link CyJobExecutionService#fetchResults(CyJob, CyJobData) fetchResults} 
	 * method
	 * being called, but if the data package is large, implementers might
	 * want to call {@link CyJobExecutionService#fetchResults(CyJob, CyJobData) fetchResults} in a separate
	 * thread spawned from {@link #handleJob(CyJob, CyJobStatus) handleJob}, then when the user indicates
	 * they are ready to process the data, the call to loadData can merge the
	 * resulting data into Cytoscape.
	 *
	 * @param job the finished {@link CyJob} to fetch the data for
	 * @param taskMonitor the {@link TaskMonitor} from the calling {@link org.cytoscape.work.Task}.
	 */
	public void loadData(CyJob job, TaskMonitor taskMonitor);
}
