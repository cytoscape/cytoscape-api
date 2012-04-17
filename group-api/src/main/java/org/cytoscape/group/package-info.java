/**
 * This package provides an API for creating and managing 
 * groups in Cytoscape.  A {@link org.cytoscape.group.CyGroup} is a collection
 * of nodes and associated edges represented by a node in a
 * network.  It is stored internally as a
 * network and a list of external edges (edges that connect
 * from nodes within the group to nodes outside of the group.
 * Groups may contain nodes which represent groups, which allows
 * for the creation of explicit hierarchies of groups.  To create
 * a group, use one of the {@link org.cytoscape.group.CyGroupFactory} methods.  A
 * {@link org.cytoscape.group.CyGroupManager} service is provided that tracks which
 * groups apply in which networks and which {@link org.cytoscape.model.CyNode}s 
 * actually represent {@link org.cytoscape.group.CyGroup}s.
 */
package org.cytoscape.group;
