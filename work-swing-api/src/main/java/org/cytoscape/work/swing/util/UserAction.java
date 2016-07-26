package org.cytoscape.work.swing.util;

/*
 * #%L
 * Cytoscape Work API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Support for a user initiated action
 *
 * @CyAPI.Final.Class 
 * @CyAPI.InModule work-swing-api
 */
public final class UserAction {

	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	private ActionListener actionListener = null;
	private boolean enabled = false;
	
	/**
	 * Creates a new UserAction object.
	 * 
	 * <p><pre>
	 * <b>example</b> :
	 * 
	 * <code>UserAction act = new UserAction(actionListener)</code>
	 * </pre></p>
	 *
	 * @param actionListener the ActionListener that will be invoked when the user 
	 * selects this action
	 */
	public UserAction(final ActionListener actionListener) {
		this.actionListener = actionListener;
		if (actionListener != null) enabled = true;
	}

	public ActionListener getActionListener() { return actionListener; }
	public void setActionListener(final ActionListener actionListener) {
		this.actionListener = actionListener;
		if (this.actionListener == null) enabled = false;
	}

	public boolean getEnabled() { return enabled; }
	public void setEnabled(boolean enabled) { this.enabled = enabled; }

}
