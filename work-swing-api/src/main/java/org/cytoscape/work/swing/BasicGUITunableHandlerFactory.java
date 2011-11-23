package org.cytoscape.work.swing;

import org.cytoscape.work.BasicTunableHandlerFactory;


/**
 * A specialization for GUITunableHandlers.
 * @param <T> 
 * @CyAPI.Final.Class
 */
public final class BasicGUITunableHandlerFactory<T extends GUITunableHandler> extends BasicTunableHandlerFactory<T> implements GUITunableHandlerFactory<T> {

	/**
	 * Constructs this BasicGUITunableHandlerFactory.
	 * @param specificHandlerType The class of the specific handler to be constructed
	 * to handle the matching classes. For instance FloatHandler.class might be specified
	 * to handle values with a Float type.
	 * @param classesToMatch One or more class types that will be handled by this handler.
	 * For example the FloatHandler might handle both Float.class and float.class.
	 */
	public BasicGUITunableHandlerFactory(Class<T> specificHandlerType, Class<?>... classesToMatch ) {
		super(specificHandlerType, classesToMatch);
	}

}
