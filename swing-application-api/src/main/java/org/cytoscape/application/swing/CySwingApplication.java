package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;


/**
 * This interface provides basic access to the Swing objects that
 * constitute this application.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CySwingApplication {

	/** 
	 * Returns the JMenu for the specified name and null if no 
	 * menu exists for the name.
	 * @param menuName the name of the JMenu.
	 * @return The JMenu for the specified name and null if no 
	 * menu exists for the name.
	 */
	public JMenu getJMenu(String menuName);

	/**
	 * Returns the JMenuBar object for the application. 
	 * @return The JMenuBar object for the application. 
	 */
	public JMenuBar getJMenuBar();

	/**
	 * Returns the JToolBar object for the application. 
	 * @return The JToolBar object for the application. 
	 */
	public JToolBar getJToolBar();

	/**
	 * Add a CyAction to application.
	 * @param action The CyAction to be added to the application.
	 */
	void addAction(CyAction action);

	/**
	 * Remove the specified CyAction from the application.
	 * @param action The CyAction to be removed from the application.
	 */
	void removeAction(CyAction action);

	/**
	 * Returns the appropriate CytoPanel for the specified compass direction.
	 * @param compassDirection  one of the enum values of CytoPanelName
	 * @return the CytoPanel corresponding to "compassDirection"
	 */
	CytoPanel getCytoPanel(CytoPanelName compassDirection);

	/**
	 * Returns the JFrame that contains the application. 
	 * @return the JFrame that contains the application. 
	 */
	JFrame getJFrame();

	/**
	 * Returns the status JToolBar of the application. 
	 * @return the status JToolBar of the application. 
	 */
	JToolBar getStatusToolBar();
}
