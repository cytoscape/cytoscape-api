package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;


public class BasicGUITunableHandlerFactory<T extends GUITunableHandler> extends BasicTunableHandlerFactory<T> implements GUITunableHandlerFactory<T> {

	public BasicGUITunableHandlerFactory(Class<T> c, Class<?>... classes ) {
		super(c, classes);
	}

}
