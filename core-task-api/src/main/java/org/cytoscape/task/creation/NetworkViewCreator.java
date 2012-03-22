
package org.cytoscape.task.creation;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.TaskIterator;

public interface NetworkViewCreator {
	
	TaskIterator createView(final CyNetwork net);

}
