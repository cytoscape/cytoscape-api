
package org.cytoscape.work.swing;


import java.awt.Window;

import javax.swing.JDialog;

import org.cytoscape.work.TaskManager;


/**
 * A specialization of TaskManager that creates a {@link JDialog} configuration
 * object and expects the dialog parent to be a {@link Window}.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface DialogTaskManager extends TaskManager<JDialog,Window> {
}
