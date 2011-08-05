package org.cytoscape.work;

/**
 * This interface specifies a unit of work to be executed
 * asynchronously in its own
 * <code>Thread</code> along with a user interface to
 * display its progress, provide a means for the user to
 * cancel the <code>Task</code>, and show information
 * about any <code>Exception</code>s thrown during its
 * execution.
 *
 * <code>Task</code>s are executed via a TaskIterator which will be passed into <code>TaskManager</code>'s
 * <code>execute</code> method.
 *
 * <p>Some hints for writing a <code>Task</code>:</p>
 * <p><ul><li><b>Exceptions:</b> 
 * <ul>
 * <li>When an exception is thrown, the <code>Task</code>
 * should not catch it and set a status message or the progress,
 * even to provide explanatory messages for the user.
 * A <code>TaskManager</code> can disregard status message
 * and progress updates once an exception is thrown.
 * Any helpful user messages regarding the exception should
 * be contained solely in the exception.</li>
 *
 * <li>If a <code>Task</code> throws
 * a low level exception, it should catch it and throw
 * an exception with a high level description. For example:
 * <p><pre><code>
 * try
 * {
 *   ...
 * }
 * catch (IOException exception) // Low level exception
 * {
 *   // Throw a high level exception that gives a high level explanation
 *   // that makes sense for a non-technical user.
 *   throw new Exception("Oops! Looks like you specified an invalid file.", exception)
 * }
 * </code></pre></p>
 * Any helpful messages for the user should be contained in
 * an exception.</li>
 *
 * <li>When a <code>Task</code> encounters an error
 * that is not in the form of an exception, like an invalid
 * variable or incorrectly formatted parameter,
 * the <code>Task</code> should not
 * set the status message giving an explanation of the
 * error and then exit. Instead, it should throw an exception.
 * <p>The wrong way:</p>
 * <p><pre><code>
 * public void run(TaskMonitor taskMonitor)
 * {
 *   if (myParameter == null)
 *   {
 *     taskMonitor.setStatusMessage("Whoa, looks like you didn't specified the parameter!");
 *     return;
 *   }
 * }
 * </code></pre></p>
 * <p>The right way:</p>
 * <p><pre><code>
 * public void run(TaskMonitor taskMonitor) throws Exception
 * {
 *   if (myParameter == null)
 *     throw new Exception("Whoa, looks like you didn't specified the parameter!");
 * }
 * </code></pre></p>
 * This is done because it is possible for the <code>TaskManager</code> to close
 * the <code>Task</code>'s user interface when the <code>Task</code> returns
 * before the user can read the message. Throwing an exception ensures 
 * the user will see the message.</li>
 * </ul>
 * <li><b>Status Messages:</b>
 * <ul>
 * <li>
 * The <code>Task</code>, when specifying its status message,
 * should describe what it will <i>do</i>, not what it has <i>done</i>.
 * Specifically, if the <code>Task</code> has several constituent parts,
 * it should set its status message at the beginning of a part, not at the
 * end. For example, assume a <code>Task</code> has two parts, A and B:
 * <p><pre><code>
 * public void run(TaskMonitor taskMonitor)
 * {
 *   taskMonitor.setStatusMessage("Starting part A...");
 *   ... // do part A
 *   taskMonitor.setStatusMessage("Part A is done!");
 *
 *   taskMonitor.setStatusMessage("Starting part B...");
 *   ... // do part B
 *   taskMonitor.setStatusMessage("Part B is done!");
 * }
 * </code></pre></p>
 * Setting the status message after part A is unnecessary
 * because the status message is immediately changed when
 * part B starts. Setting the status message after part B
 * is unnecessary because the <code>Task</code> ends immediately
 * after part B finishes. Therefore, <code>Task</code>s should
 * set the status message at the beginning of a part.
 * </li>
 *
 * <li>
 * Information regarding the result of the <code>Task</code>'s
 * should not be specified in the status message. For example:
 * <p><pre><code>
 * public void run(TaskMonitor taskMonitor)
 * {
 *   int result = ... // some complicated computation
 *   taskMonitor.setStatusMessage("The result of the computation is " + result);
 *   // Give the user a chance to read the message:
 *   try
 *   {
 *     Thread.wait(1000);
 *   }
 *   catch (InterruptedException exception) { }
 * }
 * </code></pre></p>
 * This is because the purpose of the status message is to inform the
 * user what the <code>Task</code> is currently <i>doing</i>, not what it
 * has <i>done</i>. If the <code>Task</code> wishes to provide any information
 * regarding what it has done, it must do so through alternate means.
 * </li>
 *
 * <li>The <code>Task</code> should not set the status message or progress
 * immediately before the <code>Task</code> finishes. This is because the
 * <code>TaskManager</code> may close the <code>Task</code>'s user interface
 * before the user has a chance to read it. For example:
 * <p><pre><code>
 * public void run(TaskMonitor taskMonitor) throws Exception
 * {
 *   ... // Some complicated calculation
 *   
 *   // This is unnecessary:
 *   taskMonitor.setStatusMessage("We're all done!");
 *   taskMonitor.setProgress(1.0);
 * }
 * </code></pre></p>
 * </li>
 *
 * <li>
 * To specify an indefinite state in the progress bar, the <code>Task</code>
 * should set its progress by using the <code>setProgress</code>
 * method of <code>TaskMonitor</code> to <code>0.0</code>. Because the
 * initial progress is <code>0.0</code>, it is not necessary to do this
 * at the beginning of the <code>Task</code>.
 * </li>
 * </ul></ul></p>
 *
 * @author Pasteur
 */
public interface Task {
	/**
	 * This method is called when the <code>Task</code> begins execution.
	 *
	 * This method should not be called by the programmer, as it will be called by the <code>TaskManager</code>.
	 *
	 * @param taskMonitor This is provided by <code>TaskManager</code>
	 * to allow the <code>Task</code> to modify its user interface.
	 *
	 * @throws Exception The <code>Task</code> is at liberty to
	 * throw an exception in <code>run</code>. The exception is
	 * caught by <code>TaskManager</code> and is displayed in the interface.
	 * If a <code>Task</code> does not throw an exception,
	 * the <code>Task</code> implementation does <i>not</i>
	 * need to specify the <code>throws Exception</code> clause 
	 * for the <code>run</code> method. Moreover, exceptions
	 * should be <i>the</i> way the <code>Task</code> communicates
	 * the occurance of a fatal error, like a low-level exception or an invalid parameter,
	 * to the <code>TaskManager</code>.
	 */
	void run(TaskMonitor taskMonitor) throws Exception;

	/**
	 * This method is called by the <code>TaskManager</code> when the user chooses to cancel the
	 * <code>Task</code>.
	 *
	 * <p>This method should not be called by the programmer, as it might be called by the <code>TaskManager</code>.</p>
	 *
	 * <p>This method should inform the <code>Task</code> that it must
	 * terminate execution cleanly and do any necessary cleanup
	 * work required.</p>
	 *
	 * <p><i>WARNING:</i> this method is called by a different
	 * thread than the thread executing <code>run</code>.
	 * The programmer <i>must</i> be aware of
	 * concurrency issues!</p>
	 */
	void cancel();
}
