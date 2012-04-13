/*
  File: AbstractNetworkTask.java

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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.work.AbstractTask;

/** Base class for all tasks that need to operate on a {@link CyNetwork}. 
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkTask extends AbstractTask {

    /**
     * The network that descendants of this class will operate on.
     */
    protected final CyNetwork network;

    /**
     * Base class for tasks that need to operate on a {@link CyNetwork}
     * 
     * @param network the {@link CyNetwork} the tasks need to operate on. Must be a non-null CyNetwork.
     */
    public AbstractNetworkTask(final CyNetwork network) {
	if (network == null)
	    throw new NullPointerException("CyNetwork is null");

	this.network = network;
    }
}
