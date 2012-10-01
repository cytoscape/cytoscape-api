package org.cytoscape.work.swing;


import javax.swing.JPanel;

import org.cytoscape.work.TaskManager;


/**
 * A specialization of a TaskManager that creates a JPanel configuration
 * object and expects its execution context to be another JPanel.  This
 * TaskManager can be used for embedding JPanels within other GUIs.
 * @CyAPI.Api.Interface
 */
public interface PanelTaskManager extends TaskManager<JPanel,JPanel> {
	/**
	 * Validates any pending changes to the tunables in the given context
	 * object.  Any valid pending changes are written back to the context
	 * object.  Returns true if all tunable values in the context object are
	 * valid.
	 * @param tunableContext the object whose tunables should be validated.
	 * @return true if all tunable values in the context object are valid.
	 */
	boolean validateAndApplyTunables(Object tunableContext);
}
