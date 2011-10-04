package org.cytoscape.work;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

/**
 * A class for wrapping <code>ValuedTask</code>s so they can be executed
 * by a <code>TaskManager</code>.
 * This class is analogous to <code>java.util.concurrency.FutureTask</code>.
 * 
 * @author Pasteur
 * @param <V> The Generic type of this ValuedTaskExecutor.
 */
public final class ValuedTaskExecutor<V> implements Task {

	/**
	 * Describes the state the <code>ValuedTask</code> is in.
	 */
	private enum State {
		/**
		 * The <code>ValuedTask</code> has been created
		 * and is ready to be executed, but the
		 * <code>run</code> method has not yet been called.
		 *
		 * This is the default state of the
		 * <code>ValuedTaskExecutor</code> when it is created.
		 */
		READY,

		/**
		 * The <code>ValuedTask</code>'s
		 * <code>run</code> method is currently
		 * being executed.
		 */
		RUNNING,

		/**
		 * The <code>ValuedTask</code> has finished execution,
		 * where the <code>run</code> method has finished and
		 * returned a result.
		 */
		COMPLETED,

		/**
		 * The <code>ValuedTask</code>'s <code>run</code> method
		 * did not complete because the user cancelled the
		 * <code>ValuedTask</code>.
		 */
		CANCELLED,

		/**
		 * The <code>ValuedTask</code>'s <code>run</code> method
		 * did not complete because it threw an exception.
		 */
		EXCEPTION_THROWN;
	}

	private final ValuedTask<V> valuedTask;

	private V result = null;
	private State state = State.READY;
	private Exception exception = null;

	public ValuedTaskExecutor(ValuedTask<V> valuedTask) {
		if ( valuedTask == null )
			throw new NullPointerException("The task specified is null");
		this.valuedTask = valuedTask;
	}

	/**
	 * This method will be called by the <code>TaskManager</code> and
	 * should not be called by the programmer.
	 */
	public void run(TaskMonitor taskMonitor) throws Exception {
		state = State.RUNNING;
		try {
			result = valuedTask.run(taskMonitor);
			if (state == State.RUNNING)
				state = State.COMPLETED;
		} catch (Exception exception) {
			this.exception = exception;
			state = State.EXCEPTION_THROWN;
			throw exception;
		} finally {
			synchronized(this) {
				this.notifyAll();
			}
		}
	}

	/**
	 * This method might be called by the <code>TaskManager</code> and
	 * should not be called by the programmer.
	 */
	public void cancel() {
		state = State.CANCELLED;
		valuedTask.cancel();
	}

	/**
	 * Retrieves the result produced by the <code>ValuedTask</code> if it
	 * has finished execution, otherwise it waits until it
	 * finishes execution.
	 *
	 * This method will block until the <code>ValuedTask</code> has
	 * finished--that is, its state is no longer
	 * <code>READY</code> or <code>RUNNING</code>.
	 *
	 * @return The result of the <code>ValuedTask</code>.
	 *
	 * @throws InterruptedException if the current thread was interrupted
	 * while waiting for the result
	 * @throws ExecutionException if the <code>ValueTask</code> threw an
	 * exception
	 * @throws CancellationException if the user cancelled the
	 * <code>ValueTask</code>
	 */
	public V get() throws InterruptedException, ExecutionException, CancellationException {
		if (state == State.READY || state == State.RUNNING) {
			synchronized(this) {
				this.wait();
			}
		}

		if (state == State.CANCELLED)
			throw new CancellationException();
		else if (state == State.EXCEPTION_THROWN)
			throw new ExecutionException(exception);

		return result;
	}

	/**
	 * Retrieves the result produced by the <code>ValuedTask</code> if it
	 * has finished execution, otherwise it waits a specified amount of
	 * time to finish execution.
	 *
	 * This method will block until the <code>ValuedTask</code> has
	 * finished--that is, its state is no longer
	 * <code>READY</code> or <code>RUNNING</code>--or the specified
	 * wait has timed out.
	 *
	 * @return The result of the <code>ValuedTask</code>.
	 *
	 * @throws InterruptedException if the current thread was interrupted
	 * while waiting
	 * @throws ExecutionException if the <code>ValueTask</code> threw an
	 * exception
	 * @throws CancellationException if the user canceled the
	 * <code>ValueTask</code>
	 * @throws TimeoutException if the wait period specified timed out
	 */
	public V get(long timeout, TimeUnit unit) 
		throws InterruptedException, ExecutionException, CancellationException, TimeoutException {

		if (state == State.READY || state == State.RUNNING) {
			synchronized(this) {
				unit.timedWait(this, timeout);
			}
		}

		if (state == State.CANCELLED)
			throw new CancellationException();
		else if (state == State.EXCEPTION_THROWN)
			throw new ExecutionException(exception);

		return result;
	}
}
