/*
  File: AbstractEdgeWeightedLayoutAlgorithm.java

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
package org.cytoscape.view.layout;


import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.util.ListSingleSelection;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.undo.UndoSupport;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * The AbstractEdgeWeightedLayoutAlgorithm provides edge weighting Tunables that
 * can be used by an implementing layout algorithm.
 * @CyAPI.Abstract.Class
 */
abstract public class AbstractEdgeWeightedLayoutAlgorithm extends AbstractLayoutAlgorithm {

	private static final String groupName = "Edge Weight Settings";

	/** A tunable for determining the edge attribute that contains the weights. */	
	@Tunable(description="The edge attribute that contains the weights",groups=groupName)
	public ListSingleSelection<String> edgeWeightColumn;

	/** A tunable for determining how to interpret weight values. */	
	@Tunable(description="How to interpret weight values",groups=groupName)
	public ListSingleSelection<WeightTypes> weightChoices;

	/** A tunable for determining the minimum edge weight to consider. */
	@Tunable(description="The minimum edge weight to consider",groups=groupName)
	public double minWeight = 0;	

	/** A tunable for determining the maximum edge weight to consider. */
	@Tunable(description="The maximum edge weight to consider",groups=groupName)
	public double maxWeight = Double.MAX_VALUE;	

    /**
     * The Constructor.
     * @param undo the UndoSupport object used for allowing undo of layouts.
     * @param computerName a computer readable name used to construct property strings.
     * @param humanName a user visible name of the layout.
     * @param supportsSelectedOnly indicates whether only selected nodes should be laid out.
     */
    public AbstractEdgeWeightedLayoutAlgorithm(final UndoSupport undo, final String computerName, 
	                                           final String humanName, boolean supportsSelectedOnly) {
		super(undo,computerName,humanName,supportsSelectedOnly);
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public void setNetworkView(CyNetworkView view) {
		super.setNetworkView(view);
		initSelectionTunables();	
	}

	private void initSelectionTunables() {
		weightChoices = new ListSingleSelection<WeightTypes>( WeightTypes.values() );	

		CyTable edgeTable = network.getDefaultEdgeTable();
		SortedSet<String> goodColumns = new TreeSet<String>();
		for ( CyColumn col : edgeTable.getColumns() ) {
			if ( col.getType() == Integer.class || col.getType() == Double.class )
				goodColumns.add(col.getName());
		}
		edgeWeightColumn = new ListSingleSelection<String>( new ArrayList<String>( goodColumns ) );
	}
}
