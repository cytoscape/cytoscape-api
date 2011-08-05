package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;


public class BasicGUITunableHandlerFactory extends BasicTunableHandlerFactory<GUITunableHandler> implements GUITunableHandlerFactory {

	public BasicGUITunableHandlerFactory(Class<GUITunableHandler> c, Class<?>... classes ) {
		super(c, classes);
	}

}
