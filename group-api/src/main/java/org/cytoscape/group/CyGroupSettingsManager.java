package org.cytoscape.group;

/*
 * #%L
 * Cytoscape Groups API (group-api)
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


import java.util.HashMap;
import java.util.Map;

import org.cytoscape.group.CyGroup;
import org.cytoscape.group.data.Aggregator;
import org.cytoscape.model.CyColumn;


/**
 * The CyGroupSettingsManager is responsible for providing an interface
 * to all of the possible settings controlling {@link CyGroup}s, including
 * the default settings and group specific settings.  This includes
 * both view settings as well as the various aggregation settings.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule group-api
 */

public interface CyGroupSettingsManager {

	/**
	 * The {@link DoubleClickAciton} enum provides the options for
	 * what to do when the user double-clicks on either a group node
	 * or a node that's a member of a group.
	 */
	public static enum DoubleClickAction {
		NONE("None"), // Do nothing on double-click
		EXPANDCONTRACT("Expand/Contract"), // Expand or contract the group on double-click
		SELECT("Select"); // Select the nodes in the group on double-click

		private final String name;
		DoubleClickAction(String n) {
			this.name = n;
		}
		public String toString() {return name;}
	}

	/**
	 * The {@link GroupViewType} enum provides the options for
	 * how to visualize a group.
	 */
	public static enum GroupViewType {
		NONE("None"), // No special handling for the group
		COMPOUND("Compound Node"), // Treat the group as a compound node
		SINGLENODE("Single Node"), // Treat all of the nodes as a single node
		SHOWGROUPNODE("Show Group Node"); // Show the group node and add member edges

		private final String name;
		GroupViewType(String n) {
			this.name = n;
		}
		public String toString() {return name;}
	}

	/**
	 * Get the current default {@link DoubleClickAction}.  All new groups
	 * will utilize this action unless changed by a group-specific set.
	 *
	 * @return the current default DoubleClickAction
	 */
	public DoubleClickAction getDoubleClickAction();

	/**
	 * Get the current {@link DoubleClickAction} for the specified group.  
	 *
	 * @param group the {@link CyGroup} we're interested in
	 * @return the current DoubleClickAction for this group
	 */
	public DoubleClickAction getDoubleClickAction(CyGroup group);

	/**
	 * Set the current default {@link DoubleClickAction}.  All new groups
	 * will utilize this action unless changed by a group-specific set.
	 *
	 * @param action the {@link DoubleClickAction} to set as the default
	 */
	public void setDoubleClickAction(DoubleClickAction action);

	/**
	 * Set the {@link DoubleClickAction} for a specific group.
	 *
	 * @param group the {@link CyGroup} to set the value for
	 * @param action the {@link DoubleClickAction} to set for this group
	 */
  public void setDoubleClickAction(CyGroup group, DoubleClickAction action);

	/**
	 * Get the current default {@link GroupViewType}.  All new groups
	 * will utilize this visualization unless overridden by a specific
	 * setting
	 *
	 * @return the current default {@link GroupViewType} to set
	 */
	public GroupViewType getGroupViewType();

	/**
	 * Get the current {@link GroupViewType} for the specified group.  
	 *
	 * @param group the {@link CyGroup} we're interested in
	 * @return the current {@link GroupViewType} for this group
	 */
	public GroupViewType getGroupViewType(CyGroup group);

	/**
	 * Set the current default {@link GroupViewType}.  All new groups
	 * will utilize this visualization unless changed by a group-specific set.
	 *
	 * @param action the {@link GroupViewType} to set as the default
	 */
	public void setGroupViewType(GroupViewType action);

	/**
	 * Set the {@link GroupViewType} for a specific group.
	 *
	 * @param group the {@link CyGroup} to set the value for
	 * @param action the {@link GroupViewType} to set for this group
	 */
  public void setGroupViewType(CyGroup group, GroupViewType action);

	/**
	 * Get the default setting for whether or not to create a nested
	 * network view on collapsed nodes. 
	 *
	 * @return use nested network image on collapsed nodes if true
	 */
  public boolean getUseNestedNetworks();

	/**
	 * Get the setting for whether or not to create a nested
	 * network view on collapsed nodes for the specified {@link CyGroup}. 
	 *
	 * @param group the {@link CyGroup} we want to get the nested network setting for
	 * @return use nested network image on collapsed nodes if true
	 */
  public boolean getUseNestedNetworks(CyGroup group);

	/**
	 * Set the default setting for whether or not to create a nested
	 * network view on collapsed nodes. 
	 *
	 * @param useNestedNetwork if true, create a nested network
	 *                         image by default
	 */
  public void setUseNestedNetworks(boolean useNestedNetwork);

	/**
	 * Set the setting for whether or not to create a nested
	 * network view on collapsed nodes for a specific {@link CyGroup}. 
	 *
	 * @param group the {@link CyGroup} we're setting 
	 * @param useNestedNetwork if true, create a nested network
	 *                         image by default
	 */
  public void setUseNestedNetworks(CyGroup group, boolean useNestedNetwork);

	/**
	 * Get the setting for whether or not we're enabling aggregation.  If
	 * the return value is true, then the values in the member nodes will
	 * be aggregated onto the corresponding column in the group node.
	 *
	 * @return true if we are aggregating values, false otherwise
	 */
	public boolean getEnableAttributeAggregation();

	/**
	 * Get the setting for whether or not we're enabling aggregation for
	 * a specific group.  If
	 * the return value is true, then the values in the member nodes will
	 * be aggregated onto the corresponding column in the group node.
	 *
	 * @param group the {@link CyGroup} we're interested in
	 * @return true if we are aggregating values, false otherwise
	 */
	public boolean getEnableAttributeAggregation(CyGroup group);

	/**
	 * Set the setting for whether or not we're aggregating values from
	 * member nodes onto the group node.  
	 *
	 * @param aggregate true if we are aggregating values, false otherwise
	 */
	public void setEnableAttributeAggregation(boolean aggregate);

	/**
	 * Set the setting for whether or not we're aggregating values for
	 * member nodes onto the group node for a specific group.
	 *
	 * @param group the {@link CyGroup} we're interested in
	 * @param aggregate true if we are aggregating values, false otherwise
	 */
	public void setEnableAttributeAggregation(CyGroup group, boolean aggregate);

	/**
	 * Get the {@link Aggregator} that will be used to aggregate the values
	 * in a specific {@link CyColumn} for all of the nodes in a group onto
	 * the corresponding column in the group node.  The returned 
	 * {@link Aggregator} will take into account any overrides and
	 * group-specific settings, if there are any.
	 *
	 * @param group the {@link CyGroup} to get the {@link Aggregator} for
	 * @param column the {@link CyColumn} to get the {@link Aggregator} for
	 * @return the {@link Aggregator} for the specific group and column
	 */
  public Aggregator<?> getAggregator(CyGroup group, CyColumn column);

	/**
	 * Get the default {@link Aggregator} for a specific column type
	 * as expressed as a java {@link Class}.
	 *
	 * @param ovClass the {@link Class} to get the default {@link Aggregator} for
	 * @return the default {@link Aggregator} for the specific {@link Class}
	 */
	public Aggregator<?> getDefaultAggregation(Class<?> ovClass);

	/**
	 * Get the default {@link Aggregator} for a specific List column type
	 * as expressed as a java {@link Class}.
	 *
	 * @param ovClass the List {@link Class} to get the default {@link Aggregator} for
	 * @return the default {@link Aggregator} for the specific {@link Class}
	 */
	public Aggregator<?> getDefaultListAggregation(Class<?> ovClass);

	/**
	 * Set the default {@link Aggregator} for a specific column type
	 * as expressed as a java {@link Class}.
	 *
	 * @param ovClass the {@link Class} to get the default {@link Aggregator} for
	 * @param agg the {@link Aggregator} to set as the default for the specified {@link Class}
	 */
  public void setDefaultAggregation(Class<?> ovClass, Aggregator<?> agg);

	/**
	 * Set the default {@link Aggregator} for a specific List column type
	 * as expressed as a java {@link Class}.
	 *
	 * @param ovClass the List {@link Class} to set the default {@link Aggregator} for
	 * @param agg the {@link Aggregator} to set as the default for the specified {@link Class}
	 */
  public void setDefaultListAggregation(Class<?> ovClass, Aggregator<?> agg);

	/**
	 * Get the {@link Aggregator} for a specific column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to get the {@link Aggregator} for
	 * @param ovClass the {@link Class} to get the {@link Aggregator} for
	 * @return the {@link Aggregator} for the specific {@link Class} in this {@link CyGroup}
	 */
	public Aggregator<?> getDefaultAggregation(CyGroup group, Class<?> ovClass);

	/**
	 * Get the {@link Aggregator} for a specific List column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to get the {@link Aggregator} for
	 * @param ovClass the {@link Class} to get the {@link Aggregator} for
	 * @return the {@link Aggregator} for the specific {@link Class} in this {@link CyGroup}
	 */
	public Aggregator<?> getDefaultListAggregation(CyGroup group, Class<?> ovClass);

	/**
	 * Set the {@link Aggregator} for a specific column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to get the {@link Aggregator} for
	 * @param ovClass the {@link Class} to get the {@link Aggregator} for
	 * @param agg the {@link Aggregator} to use for the specified {@link Class} in this {@link CyGroup}
	 */
  public void setDefaultAggregation(CyGroup group, Class<?> ovClass, Aggregator<?> agg);

	/**
	 * Set the {@link Aggregator} for a specific List column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to set the {@link Aggregator} to
	 * @param ovClass the {@link Class} to set the {@link Aggregator} to
	 * @param agg the {@link Aggregator} to use for the specified {@link Class} in this {@link CyGroup}
	 */
  public void setDefaultListAggregation(CyGroup group, Class<?> ovClass, Aggregator<?> agg);

	/**
	 * Set the {@link Aggregator} for a specific column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to set the {@link Aggregator} to
	 * @param ovClass the {@link Class} to et the {@link Aggregator} to
	 * @param aggName the name of the {@link Aggregator} to use for the specified {@link Class} in this {@link CyGroup}
	 */
  public void setDefaultAggregation(CyGroup group, Class<?> ovClass, String aggName);

	/**
	 * Set the {@link Aggregator} for a specific List column type
	 * as expressed as a java {@link Class} that will be used in
	 * the specified {@link CyGroup}.
	 *
	 * @param group the {@link CyGroup} to set the {@link Aggregator} to
	 * @param ovClass the List {@link Class} to set the {@link Aggregator} to
	 * @param aggName the name of the {@link Aggregator} to use for the specified {@link Class} in this {@link CyGroup}
	 */
  public void setDefaultListAggregation(CyGroup group, Class<?> ovClass, String aggName);

  /**
   * Get the override aggregation setting for a column (if any).  Override aggregation provides
	 * the ability to override the {@link Aggregator} used for a specific column.  For example
	 * we may not want to aggregate the "shared node" column.
	 *
	 * @param column the {@link CyColumn} to get the default override for
	 * @return the {@link Aggregator} for this column, or null if no override was set
   */
  public Aggregator<?> getOverrideAggregation(CyColumn column);

  /**
   * set the override aggregation setting for a column (if any).  Override aggregation provides
	 * the ability to override the {@link Aggregator} used for a specific column.  For example
	 * we may not want to aggregate the "shared node" column.
	 *
	 * @param column the {@link CyColumn} to set the default override for
	 * @param agg the {@link Aggregator} for this column
   */
  public void setOverrideAggregation(CyColumn column, Aggregator<?> agg);

  /**
   * Get the override aggregation setting for a column (if any) in a specific group.  
	 * Override aggregation provides
	 * the ability to override the {@link Aggregator} used for a specific column.  For example
	 * we may not want to aggregate the "shared node" column.
	 *
	 * @param group the {@link CyGroup} to get the {@link Aggregator} for
	 * @param column the {@link CyColumn} to get the override for
	 * @return the {@link Aggregator} for this column in this group, or null if no override was set
	 */
  public Aggregator<?> getOverrideAggregation(CyGroup group, CyColumn column);

  /**
   * set the override aggregation setting for a column in a group (if any).  Override aggregation provides
	 * the ability to override the {@link Aggregator} used for a specific column.  For example
	 * we may not want to aggregate the "shared node" column.
	 *
	 * @param group the {@link CyGroup} to set the {@link Aggregator} for
	 * @param column the {@link CyColumn} to set the default override for
	 * @param agg the {@link Aggregator} for this column in this group
   */
  public void setOverrideAggregation(CyGroup group, CyColumn column, Aggregator<?> agg);

}
