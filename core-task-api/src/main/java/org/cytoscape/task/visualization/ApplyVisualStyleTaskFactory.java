package org.cytoscape.task.visualization;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for applying a visual style.
 * @CyAPI.Api.Interface
 */
public interface ApplyVisualStyleTaskFactory extends NetworkViewTaskFactory{
	
	/**
	 * Creates a task iterator for applying a visual style on a network view.
	 * @param networkView The network view to apply the visual style on.
	 * @param style The visual styles to apply.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyNetworkView networkView, final VisualStyle style);
}
