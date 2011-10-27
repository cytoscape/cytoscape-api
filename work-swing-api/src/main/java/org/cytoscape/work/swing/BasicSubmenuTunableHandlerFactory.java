package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;


public class BasicSubmenuTunableHandlerFactory<T extends SubmenuTunableHandler> extends BasicTunableHandlerFactory<T> implements SubmenuTunableHandlerFactory<T> {

	public BasicSubmenuTunableHandlerFactory(Class<T> c, Class<?>... classes ) {
		super(c, classes);
	}

}
