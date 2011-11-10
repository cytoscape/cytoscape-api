
package org.cytoscape.work.swing;


import java.awt.Window;

import javax.swing.JDialog;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskManager;


/**
 * A specialization of TaskManager that creates a {@link JDialog} configuration
 * object and expects the dialog parent to be a {@link Window}.
 */
public interface DialogTaskManager extends TaskManager<JDialog,Window> {

	/**
	 * An additional execute method that allows {@link org.cytoscape.work.Tunable} annotated
	 * fields and methods to be ignored in the TaskFactory object.
	 * @param tf The TaskFactory to be executed.
	 * @param ignoreTaskFactoryTunables If true any tunables found in the
	 * TaskFactory will be ignored and if false the tunables will be used.
	 */
	void execute(TaskFactory tf, boolean ignoreTaskFactoryTunables);
}
