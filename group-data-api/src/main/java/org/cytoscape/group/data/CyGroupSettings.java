/* vim: set ts=2: */
/**
 * Copyright (c) 2012 The Regents of the University of California.
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
package org.cytoscape.group.data;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;

/**
 * CyGroupSettings provides interfaces to access user-requested settings
 * that influence how the {@link CyGroup} system works.  There are several groups
 * of settings that influence different aspects of groups: visual settings,
 * attribute aggregation settings, user interaction settings.
 *
 * <b>Visual Settings</b>
 *
 * <b>Attribute Aggregation Settings</b>
 *
 * <b>User Interaction Settings</b>
 * <ul><li>Double-click action: what should happen when a user double-clicks 
 * on a group node or a group member.  This can be either: <i>None</i>, <i>ExpandContract</i>,
 * or <i>Select</i>.</li>
 * </ul>
 */

public interface CyGroupSettings {
	enum DoubleClickAction { 
		None("None"), 
		ExpandContract("Expand/Contract"), 
		Select("Select");

		private final String name;
		DoubleClickAction(String n) {this.name = n;}
		public String toString() {return name;}
 }

	/**
	 * Get the default action to use when the user double-clicks on a group node or member node.
	 * This settings has no impact if Cytoscape is headless.
	 *
	 * @return the double-click action
	 */
	public DoubleClickAction getDoubleClickAction();

	/**
	 * Get the action to use when the user double-clicks a specific group node or member node.
	 * This settings has no impact if Cytoscape is headless.
	 *
	 * @param group the group we're interested in
	 * @return the double-click action
	 */
	public DoubleClickAction getDoubleClickAction(CyGroup group);

	/**
	 * Set the default action to use when the user double-clicks on a group node or member node.
	 * This settings has no impact if Cytoscape is headless.
	 *
	 * @param action the double-click action
	 */
	public void setDoubleClickAction(DoubleClickAction action);

	/**
	 * Set the action to use when the user double-clicks on a specific group node or member node.
	 * This settings has no impact if Cytoscape is headless.
	 *
	 * @param group the group we're interested in
	 * @param action the double-click action
	 */
	public void setDoubleClickAction(CyGroup group, DoubleClickAction action);

	/**
	 * Determine whether attribute aggregation (aggregating all of the attributes from member nodes
	 * onto the group node).
	 *
	 * @return whether attribute aggregation is enabled (true) or not (false)
	 */
	public boolean getEnableAttributeAggregation();

	/**
	 * Determine whether attribute aggregation (aggregating all of the attributes from member nodes
	 * onto the group node) is enabled for a specific group.
	 *
	 * @param group the group we're interested in
	 * @return whether attribute aggregation is enabled (true) or not (false)
	 */
	public boolean getEnableAttributeAggregation(CyGroup group);

	/**
	 * Set whether attribute aggregation (aggregating all of the attributes from member nodes
	 * onto the group node).
	 *
	 * @param aggregateAttributes whether attribute aggregation is enabled (true) or not (false)
	 */
	public void setEnableAttributeAggregation(boolean aggregateAttributes);

	/**
	 * Set whether attribute aggregation (aggregating all of the attributes from member nodes
	 * onto the group node) is enabled for a specific group.
	 *
	 * @param group the group we're interested in
	 * @param aggregateAttributes whether attribute aggregation is enabled (true) or not (false)
	 */
	public void setEnableAttributeAggregation(CyGroup group, boolean aggregateAttributes);

	/**
 	 * The the {@link Aggregator} for a specific group and column
 	 *
 	 * @param group the group to get the aggregator for
 	 * @param column the column to get the aggregator for
 	 * @return the aggregator to use for this group and column
 	 */
  public Aggregator getAggregator(CyGroup group, CyColumn column);

  public void setDefaultAggregation(CyGroup group, Class ovClass, Aggregator agg);
  public Aggregator getDefaultAggregation(CyGroup group, Class ovClass);

  public void setDefaultAggregation(Class ovClass, Aggregator agg);
  public Aggregator getDefaultAggregation(Class ovClass);

  public void setOverrideAggregation(CyGroup group, CyColumn column, Aggregator agg);
  public Aggregator getOverrideAggregation(CyGroup group, CyColumn column);

  public void setOverrideAggregation(CyColumn column, Aggregator agg);
  public Aggregator getOverrideAggregation(CyColumn column);

}
