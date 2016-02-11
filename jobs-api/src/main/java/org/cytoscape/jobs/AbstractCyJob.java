package org.cytoscape.jobs;

import java.util.Map;

import org.cytoscape.jobs.CyJob;
import org.cytoscape.jobs.CyJobDataService;
import org.cytoscape.jobs.CyJobExecutionService;
import org.cytoscape.jobs.CyJobHandler;

/**
 * This can be used as a base class for custom implementations of
 * {@link CyJob}
 *
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule jobs-api
 */
public class AbstractCyJob implements CyJob {
	protected CyJobHandler jobHandler;
	protected int pollInterval = -1;
	protected final CyJobDataService dataService;
	protected final CyJobExecutionService execService;
	protected final String jobName;
	protected final String path;
	protected String jobId;

	public AbstractCyJob(String name, String basePath, 
	                     CyJobExecutionService executionService, 
								    	 CyJobDataService dataService, CyJobHandler jobHandler) {
		jobName = name;
		path = basePath;
		execService = executionService;
		this.dataService = dataService;
		this.jobHandler = jobHandler;
	}

	public CyJobDataService getJobDataService() { return dataService; }
	public CyJobExecutionService getJobExecutionService() { return execService; }
	public String getJobName() { return jobName; }
	public String getPath() { return path; }

	public CyJobHandler getJobHandler() { return jobHandler; }
	public void setJobHandler(CyJobHandler handler) { this.jobHandler = handler; }

	public int getPollInterval() { return pollInterval; }
	public void setPollInterval(int pollInterval) { this.pollInterval = pollInterval; }

	public String getJobId() { return jobId; }
	public void setJobId(String id) { jobId = id; }
}
