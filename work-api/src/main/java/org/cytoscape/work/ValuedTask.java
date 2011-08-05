package org.cytoscape.work;

/**
 * Describes a unit of work that produces a result asynchronously.
 * This interface is identical to <code>Task</code>, except it allows
 * <code>run</code> to return a result. This interface is analogous to
 * <code>java.util.concurrency.Future</code>.
 *
 * Because a <code>ValuedTask</code> cannot be executed by a
 * <code>TaskManager</code>, an instance of this interface is typically wrapped
 * by an instance of <code>ValuedTaskExecutor</code> in order to be
 * executed by a <code>TaskManager</code>.
 *
 * @author Pasteur
 */
public interface ValuedTask<V> {
	/**
	 * This method contains the action of the <code>ValuedTask</code>.
	 *
	 * This method should not be called by the programmer,
	 * since it will be called by the <code>TaskManager</code>.
	 *
	 * @return a useful result to be retrieved by another thread
	 * after the execution of this <code>ValuedTask</code> has completed.
	 *
	 * @param taskMonitor This is provided by <code>TaskManager</code>
	 * to allow the <code>ValuedTask</code> to modify its user interface.
	 *
	 * @throws Exception The <code>ValuedTask</code> is at liberty to
	 * throw an exception. The exception is
	 * caught by <code>TaskManager</code> and the information contained
	 * by the exception is displayed in the interface.
	 */
	V run(TaskMonitor taskMonitor) throws Exception;

	/**
	 * This method is called when the user chooses to cancel the
	 * <code>Task</code>.
	 *
	 * This method should not be called by the programmer,
	 * since it will be called by the <code>TaskManager</code>.
	 *
	 * <p>This method should inform the <code>run</code> method that it must
	 * terminate execution cleanly and do any necessary cleanup
	 * work required.</p>
	 *
	 * <p><i>WARNING:</i> this method is called by a different
	 * thread than the thread executing <code>run</code>.
	 * The programmer <i>must</i> be aware of
	 * concurrency issues.</p>
	 */
	void cancel();
}
