package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
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

/**
 * An object that represents an edge within a network of nodes (vertices)
 * and edges.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyEdge extends CyIdentifiable {

	/**
	 * A String column created by default for every CyEdge that
	 * holds the interaction description of the edge. 
	 */
	String INTERACTION = "interaction";

	/**
	 * The Type enum is used by methods in {@link CyNetwork} to restrict
	 * the edges that match a query. 
	 */
	enum Type {

		/**
		 * matches only undirected edges
		 */
		UNDIRECTED,

		/**
		 * matches either undirected edges or directed edges that end with this node</li>
		 */
		INCOMING,

		/**
		 * matches either undirected edges or directed edges that start with this node</li>
		 */
		OUTGOING,

		/**
		 * matches directed edges regardless of whether this node is the source or the target
		 */
		DIRECTED,

		/**
	 	 * matches any edge
		 */
		ANY;
	}

	/**
	 * Returns the source node determining this edge. It will never be null.
	 * For undirected networks this method will consistently 
	 * return the same node.
	 * @return The source node of this edge.
	 */
	CyNode getSource();

	/**
	 * Returns the target node determining this edge. It will never be null.
	 * For undirected networks this method will consistently 
	 * return the same node.
	 * @return The target node of this edge.
	 */
	CyNode getTarget();

	/**
	 * This will return true if the edge is directed and false if the node is undirected.
	 * @return Returns true if this edge is directed and false if the node is undirected.
	 */
	boolean isDirected();
}
