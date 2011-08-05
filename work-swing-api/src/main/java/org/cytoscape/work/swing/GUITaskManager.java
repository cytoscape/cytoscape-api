package org.cytoscape.work.swing;


import java.awt.Window;

import javax.swing.JPanel;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskManager;


/**
 * A Swing specific extension of {@link TaskManager} that allows a
 * JPanel to be used to present the {@link org.cytoscape.work.Tunable}s.
 */
public interface GUITaskManager extends TaskManager {
	
	/**
	 * Set parent component of task monitor GUI.
	 * 
	 * @param parent component for the monitor GUI.
	 */
	void setParent(final Window parent);
	
	/**
	 *  Sets the container panel on the TunableInterceptor that it manages.
	 *  @param panel the new parent panel for the tunables panel
	 */
	void setTunablePanel(final JPanel panel);

	/**
	 * Returns the configuration panel for the specified task factory.
	 * @param taskFactory a non-null task factory
	 * @return the panel generated from the tunables annotating "taskFactory" 
	 * or null if "taskFactory" has no tunables
	 */
	JPanel getConfigurationPanel(final TaskFactory taskFactory);
}
