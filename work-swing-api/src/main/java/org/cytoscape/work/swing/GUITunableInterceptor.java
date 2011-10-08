package org.cytoscape.work.swing;


import java.awt.Window;
import javax.swing.JPanel;

import org.cytoscape.work.TunableInterceptor;
import org.cytoscape.work.TunableHandler;


/**
 * Provides methods to intercept the Objects annotated as <code>@Tunable</code>, 
 * use, and display them. Intended to be used as an OSGi service.
 * @param <TH> <code>TunableHandler</code>s that will be detected. 
 * It will contain the information provided
 * by the <code>@Tunable</code> annotations and the Object itself.
 * @author Pasteur
 */
public interface GUITunableInterceptor<TH extends TunableHandler> extends TunableInterceptor<TH> {
	
	/**
	 * Returns the <code>JPanel</code> that will be constructed from annotations 
	 * on <code>objs</code>.
	 * @param obs  objects which are instances classes with <code>Tunables</code> that need 
	 * to be displayed to a user.
	 * @return a panel that contains a visual representation of the tunables and is 
	 * appropriate for embedding in a dialog or another panel
	 */
	JPanel getUI(Object... obs);

	/**
	 * Use to add the Tunable's JPanels to an external JPanel that has been defined 
	 * in another class.
	 * @param parent An Object that has to be an instance of a <code>JPanel</code>.
	 * @throws IllegalArgumentException If the Object o is not a JPanel, it can not be 
	 * set as the parent for the others : they will be displayed to the user in a 
	 * <code>JOptionPanel</code>.
	 */
	void setTunablePanel(final JPanel parent);
	
	/**
	 * Used to set the top level parent (window) of Tunable dialog
	 * @param win An UI component, usually it is the desktop window of Cytoscape
	 */
	public void setParent(Window win);
}
