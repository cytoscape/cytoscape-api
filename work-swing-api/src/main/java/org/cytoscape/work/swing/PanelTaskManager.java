

package org.cytoscape.work.swing;


import java.awt.Window;

import javax.swing.JPanel;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskManager;


/**
 * A specialization of a TaskManager that creates a JPanel configuration
 * object and expects its execution context to be another JPanel.  This
 * TaskManager can be used for embedding JPanels within other GUIs.
 */
public interface PanelTaskManager extends TaskManager<JPanel,JPanel> {
}
