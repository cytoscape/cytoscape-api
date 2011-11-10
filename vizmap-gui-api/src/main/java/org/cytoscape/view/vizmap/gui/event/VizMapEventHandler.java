package org.cytoscape.view.vizmap.gui.event;

import java.beans.PropertyChangeEvent;

/**
 * #ASKMIKE class/method comment
 * @CyAPI.Spi.Interface
 */
public interface VizMapEventHandler {

	public void processEvent(PropertyChangeEvent e);

}