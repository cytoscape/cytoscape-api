package org.cytoscape.work.swing;


import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cytoscape.work.AbstractTunableHandler;
import org.cytoscape.work.Tunable;


/** Base class for the various Swing implementations of <code>TunableHandler</code>. */
public abstract class AbstractGUITunableHandler
	extends AbstractTunableHandler implements GUITunableHandler, ActionListener, ChangeListener, ListSelectionListener
{
	/**
	 *  If true, the associated GUI element should be layed out next to others in the same group,
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
	 * The list of dependencies between the <code>GUITunableHandlers</code>
	 */
	private List<GUITunableHandler> dependencies;

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
                        System.err.println("*** In AbstractGUITunableHandler: \"alignments\" was specified but is neither \"horizontal\" nor \"vertical\"!");

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

		dependencies = new LinkedList<GUITunableHandler>();
		panel = new JPanel();
	}

	/**
	 *  Action listener event handler.
	 *
	 *  @param ae specifics of the event (ignored!)
	 */
	public void actionPerformed(ActionEvent ae) {
		notifyDependents();
	}

	/**
	 * Notification of a state change of a <code>GUITunableHandler</code>
	 *
	 * @param e  the details of the state change
	 */
	public void stateChanged(ChangeEvent e) {
		notifyDependents();
	}

	/**
	 * Notify a change during the selection of an item in the <code>ListSelection</code> objects
	 *
	 * @param le  the specifics of the list selection change
	 */
	public void valueChanged(ListSelectionEvent le) {
		boolean ok = le.getValueIsAdjusting();
		if (!ok)
			notifyDependents();
	}

	/**
	 *  Notifies all dependents that this object has changed.
	 */
	public void notifyDependents() {
		String state = getState();
		String name = getName();
		for (GUITunableHandler gh : dependencies)
			gh.checkDependency(name, state);
	}

	/**
	 *  Adds the argument as a new dependency to this <code>GUITunableHandler</code>.
	 *  @param gh <code>Handler</code> on which this one will depend on
	 */
	public void addDependent(GUITunableHandler gh) {
		if (!dependencies.contains(gh))
			dependencies.add(gh);
	}

	/** {@inheritDoc} */
	public String getDependency() {
		return dependencyName;
	}

	/** {@inheritDoc} */
	public void handleDependents() {
		if (panel.isEnabled())
			handle();
	}

	/** {@inheritDoc} */
	final public void checkDependency(final String depName, final String depState) {
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

	/** Updates the state of the associated <code>Tunable</code>. */
	public abstract void handle();

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
			e.printStackTrace();
			return "";
		}
	}
}
