package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.UIManager;

import org.cytoscape.work.AbstractTunableHandler;
import org.cytoscape.work.Tunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base class for the various Swing implementations of <code>TunableHandler</code>. 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-swing-api
 */
public abstract class AbstractGUITunableHandler
	extends AbstractTunableHandler implements GUITunableHandler 
{

	private static final Logger logger = LoggerFactory.getLogger(AbstractGUITunableHandler.class);

	/**
 	 * The default label font.  We have it here for consistency.
 	 */
	protected static final Font LABEL_FONT = UIManager.getFont("Label.font");


	/**
	 *  If true, the associated GUI element should be laid out next to others in the same group,
	 *  if false, it should be vertically stacked relative to the others.
	 */
	protected boolean horizontal;

	/** <code>JPanel</code> that will contain the GUI representation of the <code>TunableHandler</code>. */
	protected JPanel panel;

	/**
	 * <pre>
	 * If this <code>Tunable</code> has a dependency on another <code>Tunable</code>,
	 * it represents the name of this dependency (i.e name of the other <code>Tunable</code>
	 * </pre>
	 */
	private String dependencyName;

	/**
	 *  <pre>
	 *  Represents the value of the dependency.
	 *  Could be : "true, false, an item of a <code>ListSelection</code>, a value ..."
	 *  </pre>
	 *  Either mustMatch is non-empty and mustNotMatch, or vice versa, or both are null if there is no dependency.
	 */
	private String mustMatch;
	private String mustNotMatch;

	/**
	 * The list of GUITunableHandlers that depend on THIS GUITunableHandler.
	 */
	private List<GUITunableHandler> dependents;

	/**
	 * The list of GUITunableHandlers that are listening for changes on THIS GUITunableHandler.
	 */
	private List<GUITunableHandler> listeners;


	/** Standard base class constructor for <code>TunableHandler</code>s that deal with
	 *  <code>Tunable</code>s that annotate a field.
	 *
	 *  @param field    An instance of <code>Field</code> that represents a field annotated with <code>@Tunable</code>
	 *  @param instance An object instance that contains a field corresponding to the <i>field</i> parameter
	 *  @param tunable  The <code>Tunable</code> that annotates <i>field</i>
	 */
	protected AbstractGUITunableHandler(final Field field, final Object instance, final Tunable tunable) {
		super(field, instance, tunable);
		init();
	}

	/** Standard base class constructor for <code>TunableHandler</code>s that deal with
	 *  <code>Tunable</code>s that use getter and setter methods.
	 *
	 *  @param getter   The getter method of the tunable object represented by the <i>instance</i> parameter.
	 *  @param setter   The setter method complimentary to the getter.
	 *  @param instance An instance of an object with a getter method that has been determined to be annotated with <code>@Tunable</code>.
	 *  @param tunable  The <code>Tunable</code> that annotates the <i>getter</i>.
	 */
	protected AbstractGUITunableHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
		super(getter, setter, instance, tunable);
		init();
	}

	private void init() {
		final String alignment = getParams().getProperty("alignments", "vertical");
                horizontal = false;
                if (alignment.equalsIgnoreCase("horizontal"))
                        horizontal = true;
                else if (!alignment.equalsIgnoreCase("vertical"))
                        logger.warn("\"alignments\" was specified but is neither \"horizontal\" nor \"vertical\".");

		String s = dependsOn();
		if (!s.isEmpty()) {
	        	if (!s.contains("!=")) {
	        		dependencyName = s.substring(0, s.indexOf("="));
	        		mustMatch = s.substring(s.indexOf("=") + 1);
	        		mustNotMatch = "";
	        	} else {
	        		dependencyName = s.substring(0, s.indexOf("!"));
	        		mustNotMatch = s.substring(s.indexOf("=") + 1);
	        		mustMatch = "";
	        	}
	        }

		dependents = new LinkedList<GUITunableHandler>();
		listeners = new LinkedList<GUITunableHandler>();
		panel = new JPanel();
	}


	public void setValue(final Object newValue) throws IllegalAccessException, InvocationTargetException{
		super.setValue(newValue);
		notifyDependents();
		notifyChangeListeners();
		
	}
	
	/**
	 *  Notifies all dependents that this object has changed.
	 */
	public void notifyDependents() {
		String state = getState();
		String name = getName();
		for (GUITunableHandler gh : dependents)
			gh.checkDependency(name, state);
	}

	/**
	 *  Notifies all dependents that this object has changed.
	 */
	public void notifyChangeListeners() {
		String state = getState();
		String name = getName();

		for (GUITunableHandler gh : listeners)
			gh.changeOccurred(name, state);
		
	}

	/**
	 *  Adds the argument as a new dependency to this <code>GUITunableHandler</code>.

	 *  @param gh <code>Handler</code> on which this one will depend on
	 */
	public void addChangeListener(GUITunableHandler gh) {
		if (!listeners.contains(gh))
			listeners.add(gh);
	}

	/**
	 *  Adds the argument as a new dependency to this <code>GUITunableHandler</code>.
	 *  @param gh <code>Handler</code> on which this one will depend on
	 */
	public void addDependent(GUITunableHandler gh) {
		if (!dependents.contains(gh))
			dependents.add(gh);
	}

	/** {@inheritDoc} */
	public String getDependency() {
		return dependencyName;
	}

	/** {@inheritDoc} */
	public String[] getChangeSources() {
		return listenForChange();
	}

	/** {@inheritDoc} */
	public final void changeOccurred(final String name, final String state) {
			update();
	}

	/** {@inheritDoc} */
	public final void checkDependency(final String depName, final String depState) {
		// if we don't depend on anything, then we should be enabled
		if (dependencyName == null || mustMatch == null) {
			setEnabledContainer(true, panel);
			return;
		}

		// if the dependency name matches ...
		if (dependencyName.equals(depName)) {
			// ... and the state matches, then enable
			if (!mustMatch.isEmpty()) {
				if (mustMatch.equals(depState))
					setEnabledContainer(true, panel);
				else // ... and the state doesn't match, then disable
					setEnabledContainer(false, panel);
			} else {
				if (!mustNotMatch.equals(depState))
					setEnabledContainer(true, panel);
				else // ... and the state doesn't match, then disable
					setEnabledContainer(false, panel);
			}
		}

		return;
	}

	/**
	 * Enables or disables a container and all its children.
	 *
	 * @param enable     whether to enable the container or not
	 * @param container  the container that will be enabled or not
	 */
	private void setEnabledContainer(final boolean enable, final Container container) {
		container.setEnabled(enable);
		for (final Component child : container.getComponents()) {
			if (child instanceof Container)
				setEnabledContainer(enable, (Container)child);
			else
				child.setEnabled(enable);
		}
	}

	/**
	 * Returns the panel associated with this <code>GUITunableHandler</code>.
	 * @return the <code>JPanel</code> container of this <code>GUITunableHandler</code>
	 */
	public JPanel getJPanel() {
		return panel;
	}

	/** {@inheritDoc} */
	public abstract void handle();

	/** 
	 * The default implementation is a no-op. You should override this method if 
	 * you want your tunable to update.
	 */
	public void update() { }

	/** Returns a string representation of the value of the <code>Tunable</code> associated with
	 *  this <code>GUITunableHandler</code>.
	 *
	 *  @return the current value of the associated <code>Tunable</code> represented as a string
	 */
	public String getState() {
		try {
			final Object value = getValue();
			return value == null ? "" : value.toString();
		} catch (final Exception e) {
			logger.warn("Could not get state.", e);
			return "";
		}
	}
}
