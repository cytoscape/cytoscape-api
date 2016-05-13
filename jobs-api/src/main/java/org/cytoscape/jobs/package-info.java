
/**
This package provides a mechanism for executing remote jobs from within Cytoscape.
The goal is to provide reusable mechanisms that may be utilized by Cytoscape
apps to marshal data, submit a remote job, check on the status of the submitted
job, fetch the results and unmarshal the data.

<p>There are three main components of an implementation of a remote job:
<ul>
<li>An implementation of {@link org.cytoscape.jobs.CyJobExecutionService}, which provides communication
with the remote service.  The {@link org.cytoscape.jobs.CyJobExecutionService} is responsible for
creating (and implementing) the {@link org.cytoscape.jobs.CyJob} object, which is the object that maintains
the state of the remote job and gets serialized and deserialized in sessions.  Implementations
of {@link org.cytoscape.jobs.CyJobExecutionService} should store enough information in their {@link org.cytoscape.jobs.CyJob}
object to restore the state of the job across sessions.  Note that 
{@link org.cytoscape.jobs.CyJobExecutionService CyJobExecutionServices}
are, in fact, services.  That is, there is expected to be one service running for all
jobs and all uses.  As such, implementations of {@link org.cytoscape.jobs.CyJobExecutionService} should not
store any state.</li>
<li>An implementation of {@link org.cytoscape.jobs.CyJobDataService}, which provides the marshalling and
unmarshalling of the data.  The {@link org.cytoscape.jobs.CyJobDataService} is responsible for
creating and implementing the {@link org.cytoscape.jobs.CyJobData} object, which contains all of the
data to be sent to the remote service.  Typically, a {@link org.cytoscape.jobs.CyJobExecutionService}
will use a particular {@link org.cytoscape.jobs.CyJobDataService} that works with it's remote service.
A given {@link org.cytoscape.jobs.CyJobDataService} might be used by multiple 
{@link org.cytoscape.jobs.CyJobExecutionService CyJobExecutionServices}.
As noted above, implementations of {@link org.cytoscape.jobs.CyJobDataService} are also services and should
not store any state.  If the implementation provides methods to serialize 
{@link org.cytoscape.model.CyNetwork CyNetworks},
{@link org.cytoscape.model.CyTable CyTables} or 
{@link org.cytoscape.view.model.CyNetworkView CyNetworkViews}, it may need to save the SUIDs of those
objects to be able to restore them after session restore (SUIDs are not preserved
across sessions).  To assist with this, a utility class {@link org.cytoscape.jobs.SUIDUtil} is provided.</li>
<li>An app, that will choose the appropriate {@link org.cytoscape.jobs.CyJobExecutionService}, implement a
{@link org.cytoscape.jobs.CyJobHandler} to listen for status changes in jobs and when the job is complete
load the data and merge it into Cytoscape (if appropriate).</li>
</ul>
</p>
The general flow of execution is shown in the image below, but is also described below:
<ol>
<li>An app will use OSGi to find the {@link org.cytoscape.jobs.CyJobExecutionService} it is interested
in utilizing.</li>
<li>The app will create an empty {@link org.cytoscape.jobs.CyJob} by calling 
{@link org.cytoscape.jobs.CyJobExecutionService#getCyJob(String, String) getCyJob} method.</li>
<li>The app should register a method that implements {@link org.cytoscape.jobs.CyJobHandler} to be
notified of changes in the job status</li>
<li>The app should then retreive the {@link org.cytoscape.jobs.CyJobDataService} from the
{@link org.cytoscape.jobs.CyJobExecutionService} and add any data necessary to submit the job.</li>
<li>The app will then call 
{@link org.cytoscape.jobs.CyJobExecutionService#executeJob(CyJob, String, Map<String,Object>, CyJobData) executeJob},
where:
<ul>
<li>the {@link org.cytoscape.jobs.CyJob} is the empty job that was created in step #2</li>
<li>the {@link String}, if not <b>null</b> will override the <i>basePath</i> 
in the {@link org.cytoscape.jobs.CyJob} for establishing a connection to the remote servic</li>e
<li>the {@link java.util.Map} contains any configuration information required to execute the
job.  This might include authentication credentials or algorithm options.</li>
<li>and the {@link org.cytoscape.jobs.CyJobData} is the data to be serialized and sent as input
for the remote job</li>
</ul>
to actually submit the job to the remote service.
<li>If the {@link org.cytoscape.jobs.CyJobStatus} indicates that the submission was successful, the
job should be added to the {@link org.cytoscape.jobs.CyJobManager} 
({@see org.cytoscape.jobs.CyJobManager#addJob(org.cytoscape.jobs.CyJob, org.cytoscape.jobs.CyJobHandler, int)}).</li>
<li>The {@link org.cytoscape.jobs.CyJobManager} will call 
{@link org.cytoscape.jobs.CyJobExecutionService#checkJobStatus(org.cytoscape.jobs.CyJob)}
and report the results to the App's {@link org.cytoscape.jobs.CyJobHandler}</li>
<li>After the job finishes, the user (not the App) is informed and when the user indicates that
the results should be retrieved and processed, a Cytoscape {@link org.cytoscape.work.Task} is executed
by the {@link org.cytoscape.work.TaskManager}.  The {@link org.cytoscape.work.Task} will call 
{@link org.cytoscape.jobs.CyJobHandler#loadData(org.cytoscape.jobs.CyJob, org.cytoscape.work.TaskMonitor)}
method to actually load the data.  This is done within the context of a Cytoscape {@link org.cytoscape.work.Task}
so that the standard mechanisms in Cytoscape for reporting progress to the user and avoid threading issues are
utilized.
</li>
</ol>
<p>While the above describes the normal workflow, there are a couple of things that might happen that
will deviate from this workflow:
<ol>
<li>The job might return an error of some sort ({@see org.cytoscape.jobs.CyJobStatus.Status}).  The
{@link org.cytoscape.jobs.CyJobManager} notes the error and the user is informed in GUI.</li>
<li>The user might save the session.  This requires the state information in the {@link org.cytoscape.jobs.CyJob}
to be saved in the session by calling the 
{@link org.cytoscape.jobs.CyJobExecutionService#saveJobInSession(org.cytoscape.jobs.CyJob, java.io.File)} 
method.</li>
<li>When the session is restored, the {@link org.cytoscape.jobs.CyJobManager} will call
{@link org.cytoscape.jobs.CyJobExecutionService#restoreJobFromSession(org.cytoscape.session.CySession, java.io.File)} 
method to restore the session, reestablish the {@link org.cytoscape.jobs.CyJobHandler} (assuming that it's registered),
and resuming the polling of the job.
</li>
</ol>
*/
package org.cytoscape.jobs;
