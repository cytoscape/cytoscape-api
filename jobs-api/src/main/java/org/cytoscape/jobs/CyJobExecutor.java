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
 * The main interface for execution of external jobs.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
import java.net.URI;

/**
 * A {@link CyJobExecutor} is the main interface to the CyJob system.  A {@link CyJobExecutor}
 * wraps all that is necessary to marshal data, execute a task, check the status of the task,
 * fetch the results, and unmarshal the data.
 */
public interface CyJobExecutor {
	/**
	 * Execute an external job using the specified URI, credentials, and data.  The URI may
	 * be a local path, a partial URI with included queries, or a full URI with included queries,
	 * depending on the external execution environment.  Similarly, the credentials are structured
	 * as an Object to accomodate a variety of approaches -- mostly commonly, this will be a
	 * username/password combination.
	 *
	 * @param path The full or partial path to the execution environment.  This might or might not
	 * include the query string portion.
	 * @param	credentials 	The credentials for secure connections.  This may be null if no authentication
	 * or authorization is necessary.
	 * @param jobData	The data to be sent or POSTed to the execution environment.  This might be used, for
	 * example, to send and existing network to be analyzed.
	 * @param	jobHandler	The method to call when the job has terminated (either successfully or after a
	 * failure or cancelation).
	 * @return the {@link CyJob} object resulting from the execution.
	 */

	// XXX How do we serialize the jobHandler information?????
	public CyJob executeJob(URI path, Object credentials, CyJobData jobData, CyJobHandler jobHandler);

	/**
	 * Return the {@link CyJobMarshaller} to be used for this external job environment.
	 *
	 * @return the appropriate job marshaller
	 */
	public CyJobMarshaller getMarshaller();

	/**
	 * Return the {@link CyJobUnmarshaller} to be used for this external job environment.
	 *
	 * @return the appropriate job unmarshaller
	 */
	public CyJobUnmarshaller getUnmarshaller();

	/**
	 * Return the {@link CyJobStatusChecker} to be used for this external job environment.
	 *
	 * @return the appropriate job status checker.  Note that this should seldom be needed
	 * if the calling app has registered itself as handling this job.
	 */
	public CyJobStatusChecker getStatusChecker();

	/**
	 * Return the {@link CyJobFether} to be used for this external job environment.
	 *
	 * @return the appropriate job fetcher.  Note that this should seldom be needed
	 * if the calling app has registered itself as handling this job.
	 */
	public CyJobFetcher getFetcher();
}
