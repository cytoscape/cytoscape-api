/*
  File: AbstractNetworkViewTask.java

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
package org.cytoscape.task;


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;

/** The base class for all tasks that need to operate on a network view.
 */
public abstract class AbstractNetworkViewTask extends AbstractTask {
	/** The network view that descendants of this class will operate on. */
	final protected CyNetworkView view; // TODO: should be renamed to networkView

	/** A base class for tasks that need to operate on a network view.
	 *  @param view  must be a non-empty network view for descendants to operate on
	 */
	public AbstractNetworkViewTask(final CyNetworkView view) {
		if (view == null)
			throw new NullPointerException("CyNetworkView is null");

		this.view = view;	
	}
}
