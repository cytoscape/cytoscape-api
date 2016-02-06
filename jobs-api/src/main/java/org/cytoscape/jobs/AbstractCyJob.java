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

import java.util.Map;

/**
 * This class may be used as a starting point for implementations of
 * CyJob.
 *
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule jobs-api
 */
public abstract class AbstractCyJob implements CyJob {
	protected final String jobId;
	protected int pollInterval;
	protected CyJobExecutor jobExecutor;
	protected CyJobHandler handler;
	protected Map<String, Object> checkerArgs;
	protected Map<String, Object> fetcherArgs;
	protected Map<String, Object> cancelArgs;

	public AbstractCyJob(String id, int pollInterval, 
	                     CyJobExecutor jobExecutor,
	                     CyJobHandler jobHandler,
	                     Map<String, Object> cancelArgs,
	                     Map<String, Object> checkerArgs,
											 Map<String, Object> fetcherArgs) {
		this.jobId = id;
		this.pollInterval = pollInterval;
		this.handler = jobHandler;
		this.cancelArgs = cancelArgs;
		this.checkerArgs = checkerArgs;
		this.fetcherArgs = fetcherArgs;
	}
	
	@Override
	public String getJobId() { return jobId; }

	@Override
	public String getJobHandler() { return handler.getClass().getName(); }

	@Override
	public int pollInterval() { return pollInterval; }

	@Override
	public CyJobStatus getJobStatus() {
		return jobExecutor.getStatusChecker().getStatus(this, checkerArgs);
	}

	@Override
	public CyJobData getJobResults() {
		return jobExecutor.getFetcher().getData(this, fetcherArgs);
	}

	@Override
	public CyJobStatus cancelJob() {
		jobExecutor.cancelJob(this, cancelArgs);
		return getJobStatus();
	}
}
