package org.cytoscape.work;

/**
 * Indicates the status of a task iterator when it has finished for {@code TaskObserver}s.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule work-api
 */
public final class FinishStatus {
  public static enum Type {
      /**
       * Indicates that the task iterator completed each of its tasks successfully.
       */
      SUCCEEDED,

      /**
       * Indicates that the task iterator did not complete because one of its tasks was cancelled by the user.
       */
      CANCELLED,

      /**
       * Indicates that the task iterator did not complete because one of its tasks failed by throwing an exception.
       */
      FAILED
  }

  final Type type;
  final Task task;
  final Exception exception;

  /**
   * Construct a new {@code FinishStatus} object that indicates success.
   */
  public static FinishStatus newSucceeded() {
    return new FinishStatus(Type.SUCCEEDED, null, null);
  }

  /**
   * Construct a new {@code FinishStatus} object that indicates cancellation.
   * @param cancelledTask The task that was cancelled by the user.
   */
  public static FinishStatus newCancelled(final Task cancelledTask) {
    return new FinishStatus(Type.CANCELLED, cancelledTask, null);
  }

  /**
   * Construct a new {@code FinishStatus} object that indicates failure.
   * @param failedTask The task that failed.
   * @param exception The exception that the task threw.
   */
  public static FinishStatus newFailed(final Task failedTask, final Exception exception) {
    return new FinishStatus(Type.FAILED, failedTask, exception);
  }

  protected FinishStatus(final Type type, final Task task, final Exception exception) {
    this.type = type;
    this.task = task;
    this.exception = exception;
  }

  /**
   * Specifies how the task iterator completed.
   */
  public Type getType() {
    return type;
  }

  /**
   * Returns the task that was cancelled or that had failed, or returns null if the task iterator succeeded.
   */
  public Task getTask() {
    return task;
  }

  /**
   * Returns the exception thrown by the task that failed, or null if the task iterator succeeded or was cancelled.
   */
  public Exception getException() {
    return exception;
  }
}
