package org.cytoscape.work.swing;

import org.cytoscape.work.TunableHandlerFactory;

/**
 * A specialization of TunableHandlerFactory for SubmenuTunableHandler. 
 * @param <T>
 * @CyAPI.Spi.Interface
 */
public interface SubmenuTunableHandlerFactory<T extends SubmenuTunableHandler> extends TunableHandlerFactory<T> {

}
