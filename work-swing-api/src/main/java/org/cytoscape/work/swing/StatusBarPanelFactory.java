package org.cytoscape.work.swing;

import javax.swing.JPanel;

/**
 * Creates a {@code JPanel} that is shown
 * at the bottom of the Cytoscape Desktop.</br>
 * NOTE: this is <b>not</b> meant to be implemented
 * by App developers.
 */
public interface StatusBarPanelFactory {
    public JPanel createTaskStatusPanel();
}
