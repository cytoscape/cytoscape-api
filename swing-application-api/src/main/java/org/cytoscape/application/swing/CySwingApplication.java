/*
 File: CySwingApplication.java

 Copyright (c) 2006, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;


/**
 * This interface provides basic access to the Swing objects that
 * constitute this application.
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
