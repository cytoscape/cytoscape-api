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

/**
 * A {@link CyJob} represents all of the state information necessary to 
 * marshal and unmarshal data, submit jobs, query job status, and fetch
 * results.  As such it is the main state object for the CyJob mechanism.
 * This is also the object that gets saved and restored from sessions by
 * the {@link CyJobManager}.  Typically, {@link CyJob CyJobs} are implemented
 * by the appropriate {@link CyJobExecutionService}, which may have
 * different state that needs to be stored in the object or serialized/deserialized
 * from sessions.  Typically, once an App has created a {@link CyJob} 
 * (using {@link CyJobExecutionService#getCyJob(String, String)}) it
 * shouldn't require any other references to jobs objects since it should
 * contain all necessary state.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJob {
	/**
	 * Return the name for this job
	 *
	 * @return the job name
	 */
	public String getJobName();

	/**
	 * Return the ID for this job.  The job ID is most often used
	 * to uniquely identify this job to a remote execution environment.
	 *
	 * @return the job id
	 */
	public String getJobId();

	/**
	 * Return the base path for communicating with the
	 * remote service for this job
	 *
	 * @return the path
	 */
	public String getPath();

	/**
	 * Return the service that handles status changes for this job.
	 *
	 * @return the status change monitor for this job
	 */
	public CyJobMonitor getJobMonitor();

	/**
	 * Set the service that handles status changes for this job.
	 *
	 * @param jobMonitor the status change handler for this job
	 */
	public void setJobMonitor(CyJobMonitor jobMonitor);

	/**
	 * Return the time in seconds between calls to checkJobStatus.
	 *
	 * @return the poll interval for this job
	 */
	public int getPollInterval();

	/**
	 * Set the time in seconds between calls to checkJobStatus.
	 *
	 * @param pollInterval the poll interval for this job
	 */
	public void setPollInterval(int pollInterval);

	/**
	 * Return the {@link CyJobExecutionService} that created this
	 * job.  Most of the methods for interacting with the backend
	 * service are through the {@link CyJobExecutionService}
	 *
	 * @param the CyJobExecutionService that created this job
	 */ 
	public CyJobExecutionService getJobExecutionService();

	/**
	 * Return the {@link CyJobDataService} that was used to
	 * create this job.
	 *
	 * @param the CyJobDataService that handles data for this job
	 */ 
	public CyJobDataService getJobDataService();

}
