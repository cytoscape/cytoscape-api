package org.cytoscape.application;

/**
 * This contains the name of the user log.
 *
 * Issuing info, warning, and error (severe) messages to the user log
 * will appear in the status bar and task history window.
 * The user log is only for issuing non-blocking messages
 * to the user outside the scope of a task. If you need
 * to issue non-blocking messages inside a task, you can
 * use {@link org.cytoscape.work.TaskMonitor} instead.
 *
 * <p>
 * <strong>Note:</strong> the user log <em>must</em> be used
 * only to issue informational messages to the user. Please
 * do <em>not</em> use the user log to issue debugging messages
 * that are only relevent to developers.
 * </p>
 *
 * <p>
 * Example using {@link java.util.logging.Logger} log system:
 * <pre>
 * {@code
 * import org.cytoscape.applcation.CyUserLog;
 * import java.util.logging.Logger;
 * import java.util.logging.LogManager;
 * ...
 * final Logger logger = LogManager.getLogManager().getLogger(CyUserLog.NAME);
 * logger.info("Filing TPS report with new cover sheet...");
 * logger.warning("Did you see the memo about this?");
 * logger.severe("I'm going to need you to go ahead and come in on Sunday too.");
 * }
 * </pre>
 * </p>
 */
public interface CyUserLog {
  /**
   * Use this name to obtain the user log.
   */
  public static final String NAME = "org.cytoscape.application.userlog";
}