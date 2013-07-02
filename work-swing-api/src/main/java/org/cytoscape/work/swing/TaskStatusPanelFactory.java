package org.cytoscape.work.swing;

import javax.swing.JPanel;

/**
 * Creates a {@code JPanel} that summarizes
 * currently executing tasks.
 * This panel is typically shown at the bottom
 * of the Cytoscape Desktop.
 */
public interface TaskStatusPanelFactory {
    public JPanel createTaskStatusPanel();
}
