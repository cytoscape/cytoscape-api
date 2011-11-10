package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;

/**
 * #ASKMIKE class/construct comment, declare final?
 * @param <T> 
 * @CyAPI.Final.Class
 */
public class BasicSubmenuTunableHandlerFactory<T extends SubmenuTunableHandler> extends BasicTunableHandlerFactory<T> implements SubmenuTunableHandlerFactory<T> {

	/**
	 * Constructs this BasicSubmenuTunableHandlerFactory.
	 * @param c
	 * @param classes
	 */
	public BasicSubmenuTunableHandlerFactory(Class<T> c, Class<?>... classes ) {
		super(c, classes);
	}

}
