/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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


import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;

/**
 * A task factory that creates one or more tasks that operate on the specified View&lt;CyEdge&gt; within
 * the specified CyNetworkView.
 * @CyAPI.Spi.Interface
 */
public interface EdgeViewTaskFactory {
	/**
	 * Creates a new TaskIterator using the given edge view and network view.
	 * @param edgeView  a non-null edge view
	 * @param networkView  a non-null network view
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(View<CyEdge> edgeView, CyNetworkView networkView);
	
	/**
	 * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param edgeView  a non-null edge view
	 * @param networkView  a non-null network view
	 * @return true if this task factory is ready to produce a TaskIterator.
	 */
	boolean isReady(View<CyEdge> edgeView, CyNetworkView networkView);
}
