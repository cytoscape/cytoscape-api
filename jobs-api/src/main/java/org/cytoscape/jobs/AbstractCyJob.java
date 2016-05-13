package org.cytoscape.jobs;

import java.util.Map;

import org.cytoscape.jobs.CyJob;
import org.cytoscape.jobs.CyJobDataService;
import org.cytoscape.jobs.CyJobExecutionService;
import org.cytoscape.jobs.CyJobMonitor;

/**
 * This can be used as a base class for custom implementations of
 * {@link CyJob}
 *
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule jobs-api
 */
public class AbstractCyJob implements CyJob {
	protected CyJobMonitor jobMonitor;
	protected int pollInterval = -1;
	protected final CyJobDataService dataService;
	protected final CyJobExecutionService execService;
	protected final String jobName;
	protected String path;
	protected String jobId;

	public AbstractCyJob(String name, String basePath, 
	                     CyJobExecutionService executionService, 
								    	 CyJobDataService dataService, CyJobMonitor jobMonitor) {
		jobName = name;
		path = basePath;
		execService = executionService;
		this.dataService = dataService;
		this.jobMonitor = jobMonitor;
	}

	@Override
	public CyJobDataService getJobDataService() { return dataService; }

	@Override
	public CyJobExecutionService getJobExecutionService() { return execService; }

	@Override
	public String getJobName() { return jobName; }

	@Override
	public String getPath() { return path; }

	@Override
	public CyJobMonitor getJobMonitor() { return jobMonitor; }

	@Override
	public void setJobMonitor(CyJobMonitor monitor) { this.jobMonitor = monitor; }

	@Override
	public int getPollInterval() { return pollInterval; }

	@Override
	public void setPollInterval(int pollInterval) { this.pollInterval = pollInterval; }

	@Override
	public String getJobId() { return jobId; }
}
