
/*
 File: MenuEnableSupport.java

 Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.application.swing;

import javax.swing.Action;
import javax.swing.JMenuItem;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DynamicSubmenuListener;

/**
 * A class that allows the enabled state of an Action of JMenuItem to be managed in 
 * a consistent way. 
 * 
 * @CyAPI.Final.Class
 */
public final class TaskFactoryEnableSupport extends AbstractEnableSupport {

	private final TaskFactory tfp;

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the submenu
	 * listener should be enabled.
	 */
	public TaskFactoryEnableSupport(DynamicSubmenuListener submenuListener, TaskFactory tfp) {
		super(submenuListener);
		this.tfp = tfp;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the action
	 * should be enabled.
	 */
	public TaskFactoryEnableSupport(Action action, TaskFactory tfp) {
		super(action);
		this.tfp = tfp;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 * @param tfp The task factory enabler that indicates whether or not the menu item
	 * should be enabled.
	 */
	public TaskFactoryEnableSupport(JMenuItem menuItem, TaskFactory tfp) {
		super(menuItem);
		this.tfp = tfp;
	}

	/**
	 * Updates the enable state for the specified action/menuListener/menuItem
	 * for the specified enableFor description and the state of the system.
	 */
	public void updateEnableState() {
		setEnabled( tfp.isReady() );
	}
}
