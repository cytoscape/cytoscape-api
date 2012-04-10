/**
 * This package provides an API for creating and managing 
 * groups in Cytoscape.  A {@link CyGroup} is a collection
 * of nodes and associated edges represented by a node in a
 * network.  It is stored internally as a
 * network and a list of external edges (edges that connect
 * from nodes within the group to nodes outside of the group.
 * Groups may contain nodes which represent groups, which allows
 * for the creation of explicit hierarchies of groups.  To create
 * a group, use one of the {@link CyGroupFactory} methods.  A
 * {@link CyGroupManager} service is provided that tracks which
 * groups apply in which networks and which {@link CyNode}s 
 * actually represent {@link CyGroups}.
 */
package org.cytoscape.group;
