/**
This package provides an API for creating and managing 
groups in Cytoscape.  A {@link org.cytoscape.group.CyGroup} is an
extention of Cytoscape's model that provides the following
additional capabilities:
<ol>
<li>Adds explicit hierarchies to the model.  Cytoscape's base model
(see {@link org.cytoscape.model}) provides for a single-level hierarchy:
a {@link org.cytoscape.model.subnetwork.CyRootNetwork} can contain a set
of {@link org.cytoscape.model.subnetwork.CySubNetwork}s.  The base model
also provides for the capability where a {@link org.cytoscape.model.CyNode}
can contain a pointer to a {@link org.cytoscape.model.CyNetwork}
(see {@link org.cytoscape.model.CyNode#getNetworkPointer}.  A
{@link org.cytoscape.group.CyGroup} uses the network pointer to point to a
{@link org.cytoscape.model.subnetwork.CySubNetwork} <i>within the same </i>
{@link org.cytoscape.model.subnetwork.CyRootNetwork} as the node.  This is
important for the next two additional capabilities.</li>
<li>Adds the ability for a group of nodes and edges to be "collapsed" into
a single, representative {@link org.cytoscape.model.CyNode}.  This representative
{@link org.cytoscape.model.CyNode} has as its network pointer a 
{@link org.cytoscape.model.subnetwork.CySubNetwork} that contains the collapsed nodes
and edges.</li>
<li>Adds the capability to track and restore {@link org.cytoscape.model.CyEdge}s that
link between a {@link org.cytoscape.model.CyNode} that exists within a group and
a {@link org.cytoscape.model.CyNode} that is outside of the group or within another
group.</li>
</li>Adds the capability to create "meta"-edges that can be used to represent collapsed
edges</li>
<li>Adds the ability to aggregate columns from all of the nodes within a group onto the
corresponding column of the group node (see {@link org.cytoscape.group.data}).</li>
</ol>
A {@link org.cytoscape.group.CyGroup} is stored internally as a
network and a list of external edges (edges that connect
from nodes within the group to nodes outside of the group.
Groups may contain nodes which represent groups, which allows
for the creation of explicit hierarchies of groups.  To create
a group, use one of the {@link org.cytoscape.group.CyGroupFactory} methods.  A
{@link org.cytoscape.group.CyGroupManager} service is provided that tracks which
groups apply in which networks and which {@link org.cytoscape.model.CyNode}s 
actually represent {@link org.cytoscape.group.CyGroup}s.
<h4>Creating and Managing Groups</h4>
<p>As with other core capabilities, there are two ways to create groups depending
on whether the App developers is creating an OSGi bundle or a "Simple App".  In either
case, the App developer must get a reference to a {@link org.cytoscape.group.CyGroupFactory}.
For a "simple app" this is available as part of the {@link org.cytoscape.app.CyAppAdapter}:
<dl><dd><code>CyGroupFactory groupFactory = adapter.getCyGroupFactory();</code></dd></dl>
For an OSGi bundle, the {@link org.cytoscape.group.CyGroupFactory} is available as a service:
<dl><dd><code>CyGroupFactory groupFactory = getService(bc, CyGroupFactory.class);</code></dd></dl>
A {@link org.cytoscape.group.CyGroup} is created either as an empty group (
({@link org.cytoscape.group.CyGroupFactory#createGroup(CyNetwork network, boolean register)}):
<dl><dd><code>CyGroup emptyGroup = groupFactory.createGroup(network, true);</code></dd></dl>
or by turning an existing node into an empty group
({@link org.cytoscape.group.CyGroupFactory#createGroup(CyNetwork network, CyNode node, boolean register)}):
<dl><dd><code>CyGroup emptyGroup = groupFactory.createGroup(network, node, true);</code></dd></dl>
or by providing lists of nodes and edges to be the initial membership of the group
({@link org.cytoscape.group.CyGroupFactory#createGroup(CyNetwork network, List nodes, List edges, boolean register)}):
<dl><dd><code>CyGroup emptyGroup = groupFactory.createGroup(network, nodes, edges, true);</code></dd></dl>
or by adding a list of nodes and edges to an existing node to make it a group
({@link org.cytoscape.group.CyGroupFactory#createGroup(CyNetwork network, CyNode node, List nodes, List edges, boolean register)}):
<dl><dd><code>CyGroup emptyGroup = groupFactory.createGroup(network, node, nodes, edges, true);</code></dd></dl>
In any of these cases, the last argument is a flag to tell the group factory code whether this
group should be registered with the {@link org.cytoscape.group.CyGroupManager}.  This should almost
always be set to "true".  For group factory methods that take a list of nodes and edges, the
edge list may be <b>null</b>.  In this case, the initial edge list will be the edges that
connect all of the provided nodes and the initial external edge list will be the edges that
connect the provided nodes to nodes outside of the list.  So, the easiest way to create a group
is to collect the list of nodes to be members and call:
<dl><dd><code>CyGroup emptyGroup = groupFactory.createGroup(network, nodes, null, true);</code></dd></dl>
<p>Once a group has been created and registered with the {@link org.cytoscape.group.CyGroupManager}
methods are available to determine the {@link org.cytoscape.group.CyGroup} that corresponds to
a given {@link org.cytoscape.model.CyNode} in a particular {@link org.cytoscape.model.CyNetwork}
({@link org.cytoscape.group.CyGroupManager#getGroup}), get all of the groups in a given
{@link org.cytoscape.model.CyNetwork} ({@link org.cytoscape.group.CyGroupManager#getGroupSet}) and
get all of the groups a particular {@link org.cytoscape.model.CyNode} is a member of
({@link org.cytoscape.group.CyGroupManager#getGroupsForNode(CyNode node)}).  A couple of other
useful methods are also provided.
<p>In addition to the {@link org.cytoscape.group.CyGroupManager} methods, 
{@link org.cytoscape.group.CyGroup} also provides some important methods.  In particular,
methods to add or remove nodes or edges to the group ({@link org.cytoscape.group.CyGroup#addNodes},
{@link org.cytoscape.group.CyGroup#addEdges}, {@link org.cytoscape.group.CyGroup#removeNodes},
{@link org.cytoscape.group.CyGroup#removeEdges}), 
methods to inquire as to group state ({@link org.cytoscape.group.CyGroup#isCollapsed},
{@link org.cytoscape.group.CyGroup#isInNetwork}), and methods to change the state of the group
({@link org.cytoscape.group.CyGroup#collapse},
{@link org.cytoscape.group.CyGroup#expand}).

<h4>Collapse and Expand</h4>
Cytoscape groups are primarily a model-level concept.  Some terms like "collapse" and
"expand" might suggest a visual representation, and indeed there is a default core implementation
bundle that handles view-level changes.  However, at this level, its probably best to
think of "collapse" and "expand" as creating and destroying hierarchical networks.  When
a group is "collapsed" it triggers several actions:
<ol>
<li>The base model is changed to add the collapsed group as a {@link org.cytoscape.model.CyNode} 
(the <i>group node</i>) and remove all of the member {@link org.cytoscape.model.CyNode}s.  </li>
<li>Any external 
{@link org.cytoscape.model.CyEdge}s (edges between group members and nodes outside of
the group) are replaced by meta-edges between the group node and the corresponding external
node. This process can be somewhat complicated by the fact that the external node of
the edge might itself be a collapsed member of a group.</li>
<li>{@link org.cytoscape.model.CyEdge}s that connect the group {@link org.cytoscape.model.CyNode}
itself to other nodes are added to the network.</li>
<li>The {@link org.cytoscape.model.CyTable} data for the collapsed group is updated to
include both the number of children and the number of descendents</li>
<li>The {@link org.cytoscape.model.CyTable} data for the collapsed group node is (optionally) updated to
reflect an aggregation of all of the data in the member nodes.</li>
</ol>
A group expansion involves the removal of the group node, its edges, and any meta-edges; and the
addition to the network of the member nodes and internal edges.  
<h4>Events ({@link org.cytoscape.group.events})</h4>
Group events are provided to inform App implementers of changes in the membership of groups
({@link org.cytoscape.group.events.GroupEdgesAddedListener}, 
{@link org.cytoscape.group.events.GroupNodesAddedListener}, 
{@link org.cytoscape.group.events.GroupEdgesRemovedListener}, and
{@link org.cytoscape.group.events.GroupNodesRemovedListener}), when groups are created and
destroyed
({@link org.cytoscape.group.events.GroupAboutToBeDestroyedListener},
{@link org.cytoscape.group.events.GroupAddedListener}), and perhaps most usefully when
the state of the group changes 
({@link org.cytoscape.group.events.GroupAboutToCollapseListener},
{@link org.cytoscape.group.events.GroupCollapsedListener}).  The latter two listeners
would be used by Apps that are providing their own view-level visualization for groups.
<h4>Column Aggregation ({@link org.cytoscape.group.data})</h4>
One of the options provided by the group package is the ability to aggregate the columns of
member nodes into the corresponding column of the group node.  For example, if nodes have an
affinity column, it could be useful to have the group node contain the average affinity of all
of its members.  Since there is clearly no "right" way to aggregate columns of various types,
the groups package provides for multiple ways to aggregate each data type.  In order to
provide a new aggregation, the App writer would first provide a new aggregation class that
implements the {@link org.cytoscape.group.data.Aggregator} interface.  The next step would
be to add that interface to the {@link org.cytoscape.group.data.CyGroupAggregationManager}.
{@link org.cytoscape.group.data.CyGroupAggregationManager} is a service that provides all
of the aggregators for a given type to the underlying group code.  When a group node is
collapsed, the aggregator for each column is called to aggregate all of the values of the
member nodes onto the column of the group node.
*/
package org.cytoscape.group;
