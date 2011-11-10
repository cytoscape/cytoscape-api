package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;


/**
 * #ASKMIKE class/construct comment, declare final?
 * @param <T> 
 * @CyAPI.Final.Class
 */
public class BasicGUITunableHandlerFactory<T extends GUITunableHandler> extends BasicTunableHandlerFactory<T> implements GUITunableHandlerFactory<T> {

	/**
	 * Constructs this BasicGUITunableHandlerFactory.
	 * @param c
	 * @param classes
	 */
	public BasicGUITunableHandlerFactory(Class<T> c, Class<?>... classes ) {
		super(c, classes);
	}

}
