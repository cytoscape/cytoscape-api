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


import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;


/**
 * A task factory that creates a task that operates on the specified View&lt;CyNode&gt; within
 * the specified CyNetworkView.
 * @CyAPI.Spi.Interface
 */
public interface NodeViewTaskFactory {
	/** 
	 * Provisions this factory with the node view and its associated network view, both of
	 * which will be passed into any task that will be created by this factory.
	 * @param nodeView  a non-null node view
	 * @param networkView   the non-null network view associated with the node view
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(View<CyNode> nodeView, CyNetworkView networkView);
	
	/** 
	 * Returns true if the node view and network view are in a state that is
	 * ready to be processed by the generated tasks and false otherwise.
	 * @param nodeView  a non-null node view
	 * @param networkView   the non-null network view associated with the node view
	 * @return true if the node view and network view are in a state that is
	 * ready to be processed by the generated tasks and false otherwise.
	 */
	boolean isReady(View<CyNode> nodeView, CyNetworkView networkView);
}
