package org.cytoscape.task.vizmap;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.util.ListSingleSelection;

public interface VisualStyleApplier extends NetworkViewTaskFactory{
	TaskIterator createTaskIterator(final CyNetworkView networkView, final ListSingleSelection<VisualStyle> styles);


}
