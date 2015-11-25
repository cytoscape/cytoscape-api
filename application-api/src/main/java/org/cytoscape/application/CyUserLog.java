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
 * Example using the Log4J system:
 * <pre>
 * {@code
 * import org.cytoscape.applcation.CyUserLog;
 * import org.apache.log4j.Logger;
 * ...
 * final Logger logger = Logger.getLogger(CyUserLog.NAME);
 * logger.info("Filing TPS report with new cover sheet...");
 * logger.warn("Did you see the memo about this?");
 * logger.error("I'm going to need you to go ahead and come in on Sunday too.");
 * }
 * </pre>
 * </p>
 * <p>
 * Note that you will need to add the <em>Pax Logging</em> dependency to your pom file to use Log4J:
 * <pre>
 * {@code
 * <dependency>
 *   <groupId>org.ops4j.pax.logging</groupId>
 *   <artifactId>pax-logging-api</artifactId>
 *   <version>1.5.2</version>
 *   <scope>provided</scope>
 * </dependency>
 * }
 * </pre>
 * (This dependency doesn't explicitly reference Log4J but does contain necessary packages for you to use
 * Log4J.)
 * </p>
 */
public interface CyUserLog {
  /**
   * Use this name to obtain the user log.
   */
  public static final String NAME = "org.cytoscape.application.userlog";
}
