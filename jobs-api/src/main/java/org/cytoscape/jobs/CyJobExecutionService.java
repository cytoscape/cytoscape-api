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
	public String getServiceName();

	public CyJobDataService getDataService();

	public CyJob getCyJob(String name, String basePath);

	public CyJobStatus executeJob(CyJob job, String basePath, Map<String, Object> configuration, CyJobData inputData);

	public CyJobStatus checkJobStatus(CyJob job);

	public CyJobStatus cancelJob(CyJob job);

	public CyJobStatus fetchResults(CyJob job, CyJobData data);

	public void saveJobInSession(CyJob job, File sessionFile);
	public CyJob restoreJobFromSession(CySession session, File sessionFile);
}
