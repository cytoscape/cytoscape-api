package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import javax.swing.JPanel;

import org.cytoscape.work.TunableHandler;


/**
 * An extension of <code>TunableHandler</code> with added functionality to support 
 * the construction of a Swing-based UI. 
 * <br>
 * Any implementation of this interface should extend {@link AbstractGUITunableHandler}
 * to avoid the difficult handling of dependencies!
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-swing-api
 */
public interface GUITunableHandler extends TunableHandler {

	/**
	 * To get the panel that contains the GUI representation
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
	 * Adds a <code>GUITunableHandler</code> that depends on THIS tunable handler. 
	 *
	 * @param gh the <code>GUITunableHandler</code> that depends on THIS tunable handler. 
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
	 * Returns the name of a different <code>GUITunableHandler</code> that this tunable handler depends on.
	 * @return the name of a different <code>GUITunableHandler</code> that this tunable handler depends on.
	 */
	String getDependency();

	/**
	 *  Notify listeners that this object has changed, i.e. an event occured.
	 */
	void notifyChangeListeners(); 

	/**
	 * Adds a <code>GUITunableHandler</code> that listens to THIS tunable handler. 
	 *
	 * @param gh the <code>GUITunableHandler</code> that listens to THIS tunable handler. 
	 */
	void addChangeListener(GUITunableHandler gh); 

	/**
	 * Returns the name of different <code>GUITunableHandler</code>s that this tunable handler listens to.
	 * @return the name of different <code>GUITunableHandler</code>s that this tunable handler listens to.
	 */
	String[] getChangeSources();

	/**
	 * This is called when a <code>GUITunableHandler</code> that this tunable handler listens to makes 
	 * a change. This method will call the abstract update() method if the change described by the name and state
	 * parameters has not already been recorded by this tunable handler.
	 * @param name  The name of the <code>GUITunableHandler</code> that has changed. 
	 * @param state The state the <code>GUITunableHandler</code> has changed to. 
	 */
	void changeOccurred(final String name, final String state); 

	/**
	 * This method should be implemented to update the user interface in such a way that other tunable
	 * handlers we listen to have changed, this tunable handler can display the appropriate inforamtion.
	 */
	void update();
}
