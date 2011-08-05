package org.cytoscape.work.swing;


import javax.swing.JPanel;

import org.cytoscape.work.TunableHandler;


/**
 * An extension of <code>TunableHandler</code> with added functionality to support 
 * the construction of a Swing-based UI.
 */
public interface GUITunableHandler extends TunableHandler {

	/**
	 * to get the panel that contains the GUI representation
	 * (<code>JTextField, JFileChooser, JLabel, JList ...</code>)
	 *
	 * @return the panel containing GUI
	 */
	JPanel getJPanel();

	/**
	 * To get the current value of a <code>Handler</code>
	 * (or path for a <code>FileHandler</code>, or selected item(s)
	 * for <code>ListMultipleSelection ListSingleSelection</code>, ...)
	 *
	 * @return string representing the state
	 */
	String getState();

	/**
	 *  Notify dependents that this object has changed, i.e. an event occured.
	 */
	void notifyDependents();

	/**
	 * Adds a dependency to this <code>GUITunableHandler</code> on another one.
	 *
	 * @param gh the <code>GUITunableHandler</code> it will depend on
	 */
	void addDependent(GUITunableHandler gh);

	/**
	 * To check the dependencies of this <code>GUITunableHandler</code> with the others.
	 *
	 * <p><pre>
	 * Check the dependencies :
	 *
	 *  - if there isn't any dependency, this handler's JPanel container is enabled
	 *  - if there is, enable or not the JPanel, depending on the name 
	 *    (<code>depName</code>) and the state(<code>depState</code>)
	 *    of the dependencies of this <code>GUITunableHandler</code>
	 *  </pre></p>
	 *
	 * @param depName  if this handler has a dependency, it must match this in order 
	 * for the associated <code>JPanel</code> to be enabled
	 * @param depState if this handler has a dependency, this must match the condition 
	 * in order for the associated <code>JPanel</code> to be enabled
	 */
	void checkDependency(String depName, String depState);

	/**
	 * Retrieves the settings for the <code>Tunables</code> object that have been modified 
	 * if their JPanel is enabled.
	 */
	void handleDependents();

	/**
	 * Provides the name of the dependency of this <code>GUITunableHandler</code>.
	 * @return the name of the dependency, if any
	 */
	String getDependency();
}
