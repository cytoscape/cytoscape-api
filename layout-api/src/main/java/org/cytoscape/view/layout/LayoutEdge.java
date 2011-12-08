/* vim: set ts=2: */
/**
 * Copyright (c) 2006 The Regents of the University of California.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *   1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions, and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions, and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *   3. Redistributions must acknowledge that this software was
 *      originally developed by the UCSF Computer Graphics Laboratory
 *      under support by the NIH National Center for Research Resources,
 *      grant P41-RR01081.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.cytoscape.view.layout;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyRow;

/**
 * The LayoutEdge class.  This class is used as a container for information
 * about the edges in a layout.  In particular, it provides a convenient handle
 * to information about the weights associated with edges, and pointers to the
 * LayoutNodes that are joined by this edge.
 * @CyAPI.Final.Class
 */
public final class LayoutEdge {
	// instance variables
	private LayoutNode v1;
	private LayoutNode v2;
	private double weight = 0.5;
	private double logWeight;
	private CyEdge edge;
	private CyRow row;

	/**
	 * Create a LayoutEdge that will contain information about this edge.
	 * Additional information must be filled in later.
	 *
	 * @param    edge    Edge that this LayoutEdge represents
	 */
	public LayoutEdge(CyEdge edge, CyRow row) {
		this.edge = edge;
		this.row = row;
	}

	/**
	 * Create a LayoutEdge that will contains information about this edge,
	 * and that record that it connects LayoutNodes v1 and v2.
	 *
	 * @param    edge    Edge that this LayoutEdge represents
	 * @param    v1    The LayoutNode that represents the source of the edge
	 * @param    v2    The LayoutNode that represents the target of the edge
	 */
	public LayoutEdge(CyEdge edge, LayoutNode v1, LayoutNode v2, CyRow row) {
		this.edge = edge;
		this.v1 = v1;
		this.v2 = v2;
		this.row = row;

		if (v1 != v2) {
			v1.addNeighbor(v2);
			v2.addNeighbor(v1);
		}
	}

	/**
	 * Set the nodes associated with this edge.  This is used subsequent
	 * to a call to the LayoutEdge(Edge) constructor to associate the
	 * source and target nodes since we don't always know that information
	 * at the time the edge is encountered.
	 *
	 * @param    v1    The LayoutNode that represents the source of the edge
	 * @param    v2    The LayoutNode that represents the target of the edge
	 */
	public void addNodes(LayoutNode v1, LayoutNode v2) {
		this.v1 = v1;
		this.v2 = v2;

		if (v1 != v2) {
			v1.addNeighbor(v2);
			v2.addNeighbor(v1);
		}
	}

	/**
	 * Set the weight for this LayoutEdge.  This assumes that all of the weight
	 * calculations are being handled externally.
	 *
	 * @param weight the actual weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Set the log weight for this LayoutEdge.  This assumes that all of the weight
	 * calculations are being handled externally.
	 *
	 * @param logWeight the actual -log of the weight	
	 */
	public void setLogWeight(double logWeight) {
		this.logWeight = logWeight;
	}

	/**
	 * Return the current value for this edge's weight.
	 *
	 * @return     This edge's weight as a double
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Return the current value for this edge's logWeight (-log(weight)).
	 *
	 * @return     This edge's logWeight as a double
	 */
	public double getLogWeight() {
		return this.logWeight;
	}

	/**
	 * Return the source of this edge
	 *
	 * @return     This edge's source
	 */
	public LayoutNode getSource() {
		return this.v1;
	}

	/**
	 * Return the target of this edge
	 *
	 * @return     This edge's target
	 */
	public LayoutNode getTarget() {
		return this.v2;
	}

	/**
	 * Return the Edge this LayoutEdge represents
	 *
	 * @return     The Edge for this LayoutEdge
	 */
	public CyEdge getEdge() {
		return this.edge;
	}

	/**
	 * Return the CyRow for this LayoutEdge 
	 *
	 * @return     The CyRow for this LayoutEdge
	 */
	public CyRow getRow() {
		return this.row;
	}

	/**
	 * Return a string representation for this LayoutEdge.
	 *
	 * @return    A String containing the name of the Edge, the connecting LayoutNodes
	 *          and the current weight.
	 */
	public String toString() {
		String source = "undefined";
		String target = "undefined";

		if (v1 != null)
			source = v1.getIdentifier();

		if (v2 != null)
			target = v2.getIdentifier();

		return "Edge " + edge.getSUID() + " connecting " + source + " and " + target
		       + " with weight " + weight;
	}
}
