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
 * The main interface for execution and management of external jobs.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
import java.io.File;
import java.util.Map;

import org.cytoscape.session.CySession;

/**
 * The {@link CyJobExecutionService} is a stateless service that can be used to provide
 * remote job services, including the execution, cancellation, status checking, and retrieval
 * of results.  Implementations of this service should also choose the appropriate
 * {@link CyJobDataService} to handle marshalling and unmarshalling of data.  It is usually
 * the case that the impelementors of the {@link CyJobExecutionService} will also provide
 * implementations of {@link CyJob}
 */
public interface CyJobExecutionService {
	/**
	 * The name of the service.  This is usually the classname and is the name that should
	 * be registered with OSGi.
	 *
	 * @return the service name
	 */
	public String getServiceName();

	/**
	 * Return the {@link CyJobDataService} that is used by service to marshall and unmarshall
	 * data for the remote service.
	 *
	 * @return the data service used
	 */
	public CyJobDataService getDataService();

	/**
	 * Create an empty (unsubmitted) {@link CyJob} 
	 *
	 * @param name the name of the job
	 * @return the empty (not yet submitted) {@link CyJob}
	 */
	public CyJob createCyJob(String name);

	/**
	 * Submit a {@link CyJob} for remote execution.  If provided, <i>basePath</i> will
	 * override the path defined by the {@link CyJob}.  The <i>configuration</i> {@link Map}
	 * provides any arguments necessary to successfully submit the job with the appropriate
	 * <i>inputData</i>.
	 *
	 * @param job the job to be submitted
	 * @param basePath if provided, this will override the path defined by the {@link CyJob}.
	 * @param configuration a {@link Map} with all of the configuration parameters necessary to
	 * submit the job
	 * @param inputData the data to be sent to the remote service.
	 * @return the {@link CyJobStatus} that results from the submission.  If everything is successful,
	 * this should return {@link CyJobStatus.Status#SUBMITTED}.  If an error occured, the return status
	 * should be {@link CyJobStatus.Status#ERROR} and {@link CyJobStatus#getMessage()} method will return the
	 * error message.
	 */
	public CyJobStatus executeJob(CyJob job, String basePath, Map<String, Object> configuration, 
	                              CyJobData inputData);

	/**
	 * Check on the status of a running job.
	 *
	 * @param job the submitted job to be checked
	 * @return the {@link CyJobStatus} of the running job.
	 */
	public CyJobStatus checkJobStatus(CyJob job);

	/**
	 * Cancel a running job.
	 *
	 * @param job the submitted job to be cancelled
	 * @return the {@link CyJobStatus} of the job after being cancelled.
	 * If everything is successful,
	 * this should return {@link CyJobStatus.Status#CANCELLED}.  If an error occured, the return status
	 * should be {@link CyJobStatus.Status#ERROR} and {@link CyJobStatus#getMessage()} 
	 * method will return the
	 * error message.
	 */
	public CyJobStatus cancelJob(CyJob job);

	/**
	 * After a {@link CyJob} status returns {@link CyJobStatus.Status#FINISHED}, this method is
	 * used to fetch the results from the job execution.
	 *
	 * @param job the completed job
	 * @param data an empty {@link CyJobData} object that will be filled with the
	 * results of the job execution.
	 * @return the status after pulling the data back.  If an error occured, the return status
	 * should be {@link CyJobStatus.Status#ERROR} and {@link CyJobStatus#getMessage()} will return the
	 * error message.
	 */
	public CyJobStatus fetchResults(CyJob job, CyJobData data);

	/**
	 * This is method is called by the {@link CyJobManager} to save sufficient information
	 * as part of the session to be able to restore and reestablish the job with the 
	 * remote service.  It should not be called directly by client apps.
	 *
	 * @param job the job to be saved in the sesion file
	 * @param sessionFile
	 */
	public void saveJobInSession(CyJob job, File sessionFile);

	/**
	 * This is method is called by the {@link CyJobManager} to restore 
	 * the job and reestablish the job with the 
	 * remote service.  It should not be called directly by client apps.
	 *
	 * @param session the session that is being restored
	 * @param sessionFile the {@link File} that was written by the 
	 * {@link #saveJobInSession(CyJob, File)} method described above.
	 * @return the restored job
	 */
	public CyJob restoreJobFromSession(CySession session, File sessionFile);
}
