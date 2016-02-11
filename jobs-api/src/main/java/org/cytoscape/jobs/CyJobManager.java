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

import java.util.List;

/**
 * CyJobManager is responsible for managing all currently running CyJobs.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJobManager {
	/**
	 * Return the list of {@link CyJob}s currently managed by the CyJobManager.
	 *
	 * @return the list of CyJobs
	 */
	public List<CyJob> getJobs();

	/**
	 * Cancel the specified job
	 *
	 * @param job the job to be canceled
	 * @return the job status of the canceled job
	 */
	public CyJobStatus cancelJob(CyJob job);

	/**
	 * Add a job to be managed by the job manager.  If the jobHandler
	 * is specified, then that jobHandler will be called if the job
	 * completes or fails
	 *
	 * @param job the {@link CyJob} to add to the list of managed jobs
	 * @param jobHandler the {@link CyJobHandler} to be called if this
	 * job completes or fails.  If this is null, the name of the job 
	 * handler defined in the job is used (through OSGi services)
	 * @param pollInterval the number of seconds between poll attempts.  If
	 * 0 or -1 the pollInterval defined in the job is used.
	 */
	public void addJob(CyJob job, CyJobHandler jobHandler, int pollInterval);

	/**
	 * Remove a job from being managed by the job manager.
	 *
	 * @param job the {@link CyJob} to remove from the list of managed jobs
	 */
	public void removeJob(CyJob job);

	/**
	 * Associate a handler with a job.  This method is used primarily by apps to
	 * reassociate with jobs after session loading.
	 *
	 * @param job the job to associate to
	 * @param jobHandler the job handler that should be called when the job completes
	 * @param pollInterval the polling interval.  If this is -1, the polling interval
	 * from the CyJob will be used.
	 */
	public void associateHandler(CyJob job, CyJobHandler jobHandler, int pollInterval);

	/**
	 * Associate a handler with a job.  This method is used primarily by CyJobExecutionServices
	 * to reassociate with jobs after session loading.
	 *
	 * @param job the job to associate to
	 * @param jobHandlerName the name of the job handler that should be called when the job completes
	 * @param pollInterval the polling interval.  If this is -1, the polling interval
	 * from the CyJob will be used.
	 */
	public void associateHandler(CyJob job, String jobHandlerName, int pollInterval);
}
