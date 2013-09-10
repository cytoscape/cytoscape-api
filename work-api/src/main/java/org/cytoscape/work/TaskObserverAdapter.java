package org.cytoscape.work;

/**
 * A convenience class for implementations of {@code TaskObserver}.
 * The methods in this class do not do anything. This is useful if
 * you're interested in implementing a single method in {@code TaskObserver}.
 *
 * @CyAPI.InModule work-api
 */
public abstract class TaskObserverAdapter implements TaskObserver {
	public void taskFinished(ObservableTask task) {}
	public void allFinished() {}
    public void cancelled(Task cancelledTask) {}
    public void failed(Task failedTask, Exception e) {}
}
