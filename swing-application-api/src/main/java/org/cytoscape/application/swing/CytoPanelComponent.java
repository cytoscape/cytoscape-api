
/*
  File: CytoPanelComponent.java

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


import java.awt.Component;
import javax.swing.Icon;


/**
 * An interface that allows a component to be registered as a service
 * that will then be added to the appropriate {@link CytoPanel}.
 * @CyAPI.Spi.Interface
 */
public interface CytoPanelComponent {

	/**
	 * Returns the Component to be added to the CytoPanel. 
	 * @return The Component to be added to the CytoPanel. 
	 */
	Component getComponent();

	/**
	 * Returns the name of the CytoPanel that this component should be added to.
	 * @return the name of the CytoPanel that this component should be added to.
	 */
	CytoPanelName getCytoPanelName();

	/**
	 * Returns the title of the tab within the CytoPanel for this component.
	 * @return the title of the tab within the CytoPanel for this component.
	 */
	String getTitle();

	/**
	 * Returns the Icon to be used along with the title in the tab for this
	 * this component. May be null!
	 * @return the Icon to be used along with the title in the tab for this
	 * this component. May be null!
	 */
	Icon getIcon();
}
