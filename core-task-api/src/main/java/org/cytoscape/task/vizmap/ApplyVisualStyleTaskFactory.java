package org.cytoscape.task.vizmap;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for applying a visual style.
 * @author rozagh
 *
 */
public interface ApplyVisualStyleTaskFactory extends NetworkViewTaskFactory{
	
	/**
	 * Creates a task iterator for applying a visual style on a network view.
	 * The created task will run synchronously in the current thread and will
	 * not create a task monitor.
	 * @param networkView The network view to apply the visual style on.
	 * @param style The visual styles to apply.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyNetworkView networkView, VisualStyle style);
}
