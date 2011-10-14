package org.cytoscape.io.read;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.Task;

/** #ASKMIKE
 * An extension of the Task interface that returns an array of
 * {@link org.cytoscape.view.model.CyNetworkView} objects as well as optional
 * {@link org.cytoscape.view.vizmap.VisualStyle} objects that are read as part
 * of the Task. Instances of this interface are created by
 * InputStreamTaskFactory objects registered as OSGi services, which are in turn
 * processed by associated reader manager objects that distinguish
 * InputStreamTaskFactories based on the DataCategory associated with the
 * {@link org.cytoscape.io.CyFileFilter}.
 */
public interface CyNetworkReader extends Task {

	CyNetwork[] getCyNetworks();

	CyNetworkView buildCyNetworkView(final CyNetwork network);

}
