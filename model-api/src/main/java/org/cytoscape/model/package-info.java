/**
This package contains the core interfaces that define the basic network and
table data structures that are foundational to Cytoscape.  Cytoscape supports
two basic, separate types of data: network data {@link org.cytoscape.model.CyNetwork} and table data  
{@link org.cytoscape.model.CyTable}.

<p>
For the most part, the table and network data structures are
independent. However, Cytoscape can link them together by indexing table rows using network object ids.
In fact, Cytoscape uses this linkage internally to store network metadata in the tables.
</p>

<h3>CyTable</h3>
A {@link org.cytoscape.model.CyTable} is Cytoscape's representation of tabular data.  A {@link org.cytoscape.model.CyTable}
consists of a series of columns ({@link org.cytoscape.model.CyColumn}) and rows ({@link org.cytoscape.model.CyRow}).  

<p>
Columns have unique names and are typed. A column has a single type that is only defined when it is created.
A column would have the <i>string type</i> if it stores text. Or it could have the <i>integer type</i>
if it stores whole numbers. Besides strings and integers, columns can have other types.
</p>

<p>
Cell values in the table are accessed through a
{@link org.cytoscape.model.CyRow}. Each row has a <i>primary key</i>.
A primary key is the row's unique identifier. No two rows in the
same table have the same primary key.
In each table, there is one column designated for storing the primary keys of each row.
The column for the primary key is created when the
table is created (see {@link org.cytoscape.model.CyTableFactory}).
</p>

<p align="center">
<img src="doc-files/CyTable-CyRows-CyColumns.png">
</p>

<h4>Virtual Columns</h4>
<p>
Cytoscape allows the contents of a column in one table to 
be shared with another table. These are called <i>virtual columns</i>.
If cell values change in one virtual column, all other tables with
that virtual column will change too.
Virtual columns are used extensively by Cytoscape to provide shared data between networks.
</p>

<p>
To add a virtual column, you will have to specify a couple things:
  <ul>
    <li>
      <i>Target table</i>: the table into which you want to put the virtual column.
    </li>
    <li>
      <i>Target column</i>: the name of the virtual column you want in the target table.
    </li>
    <li>
      <i>Source table</i>: the table from which you want to get your virtual column's contents.
    </li>
    <li>
      <i>Source column</i>: the column in the source table from which you want to get your virtual column's contents.
    </li>
    <li>
      <i>Target join key</i>: the column in the target table whose cells match the source table's primary key column.
      This is used to match up the rows between the source and target tables.
    </li>
  </ul>
</p>

<p align="center">
<img src="doc-files/CyTable-virtual-column.png">
</p>

<h4>Unique Identifiers (SUIDs)</h4>
<p>
Each table in Cytoscape has a unique identifier (SUID).  This SUID may be used to
uniquely identify the table.  {@link org.cytoscape.model.CyNetwork}s, {@link org.cytoscape.model.CyNode}s, and 
{@link org.cytoscape.model.CyEdge}s also have SUIDs that uniquely identify them.  They all inherit from the same
base interface, {@link org.cytoscape.model.CyIdentifiable}.
</p>

<h4>Creating a CyTable ({@link org.cytoscape.model.CyTableFactory})</h4>
Usually, App developers do not need to create {@link org.cytoscape.model.CyTable}s. Instead, they would use the default
tables created for each network (see below). However, it is sometimes needed to create {@code CyTable}s for apps
that need separate tables for storing tabular data that is not directly related to nodes and edges in a network.

<p>Creating a new CyTable depends on the type of App that you are developing.
  <ul>
    <li>
      <i>If your App is a simple app</i>
      <p>
      You have a class that inherits from {@link org.cytoscape.app.swing.AbstractCySwingApp} or {@link org.cytoscape.app.AbstractCyApp}.
      These classes have direct access to the {@link org.cytoscape.model.CyTableFactory} through the
      {@link org.cytoscape.app.CyAppAdapter#getCyTableFactory} method.
      </p>
    </li>
    <li>
      <i>If your App is an OSGi bundle</i>
      <p>
      You can get the {@link org.cytoscape.model.CyTableFactory} service by adding this to your {@code CyActivator}
      (see {@link org.cytoscape.service.util.AbstractCyActivator}):
      <dl><dd>
      <code>CyTableFactory tableFactory = getService(bc, CyTableFactory.class);</code>
      </dd></dl>
      where {@code bc} is the OSGi {@code BundleContext}. 
      </p>
    </li>
  </ul>
</p>

<h3>CyNetwork</h3>
A network ({@link org.cytoscape.model.CyNetwork}) is a collection
of nodes ({@link org.cytoscape.model.CyNode}) and the edges ({@link org.cytoscape.model.CyEdge}) that connect nodes together.
It's important to note that because an edge is defined as a link between two nodes, both
nodes must exist in the network for the edge to exist. {@code CyNetwork} supports
<a href="http://www.wikipedia.org/wiki/Multigraph">multigraphs</a>, 
which means that two nodes can be connected together by more than one edge. {@code CyNetwork} does not support
<a href="hhtp://www.wikipedia.org/wiki/Hypergraph">hypergraphs</a>, so edges cannot connect more than two nodes.

<p>
Cytoscape does not directly support a network hierarchy, although
it can be implemented using the Cytoscape model by combining network pointers and subnetworks (see the discussion in
the next section).
</p>

<h4>CyRootNetwork and CySubNetwork ({@link org.cytoscape.model.subnetwork})</h4>
Cytoscape's network model is a little more complicated than what a cursory glance at the
{@link org.cytoscape.model} package suggests. Cytoscape has multiple <i>root networks</i>
({@link org.cytoscape.model.subnetwork.CyRootNetwork}). Each root network has multiple
<i>subnetworks</i> ({@link org.cytoscape.model.subnetwork.CySubNetwork}).
This structure is hidden since most App implementers don't need
to know about the root network.
When you are working with a {@link org.cytoscape.model.CyNetwork}, you're really working with a
{@link org.cytoscape.model.subnetwork.CySubNetwork}. {@code CySubNetwork}s are just {@code CyNetwork}s
with a couple additional methods. 

<p align="center">
<img src="doc-files/CySubNetwork-vs-CyNetwork.png">
</p>

<p>
This hierarchy provides some nice features.
All nodes and edges really exist in the root network, not the subnetwork.
The subnetwork is merely refers to a subset of nodes and edges in the root network.
This implies that nodes and edges are shared amongst all of the subnetworks.  This
allows Cytoscape to set up shared tables so that data values in one subnetwork (say for a node) are shared with the
same node in another subnetwork.  This can be very useful when importing a lot of data (e.g. large expression data sets).
The other advantage is that it provides a place to "save" nodes and edges that we might use later.  This is used extensively
in the {@link org.cytoscape.group.CyGroup} implementation, which builds a hierarchical structure onto Cytoscape's model.
<p>The way all of this works is that when the first network is by the {@link org.cytoscape.model.CyNetworkFactory}, a new
{@link org.cytoscape.model.subnetwork.CyRootNetwork} is actually created.  A root network always has a "base network" defined, 
which is essentially a {@link org.cytoscape.model.subnetwork.CySubNetwork} that contains everything in the root network.  This is
what is returned by {@link org.cytoscape.model.CyNetworkFactory#createNetwork}.  To create a new 
{@link org.cytoscape.model.subnetwork.CySubNetwork} that is part of the same {@link org.cytoscape.model.subnetwork.CyRootNetwork},
all that needs to be done is to cast your {@link org.cytoscape.model.CyNetwork} to 
{@link org.cytoscape.model.subnetwork.CySubNetwork} and call the {@link org.cytoscape.model.subnetwork.CySubNetwork#getRootNetwork}
method:
<dl>
<dd><code>CyRootNetwork rootNetwork = ((CySubNetwork)network).getRootNetwork();</code></dd>
</dl>
{@link org.cytoscape.model.subnetwork.CyRootNetwork} provides methods to create and add new subnetworks
(see {@link org.cytoscape.model.subnetwork.CyRootNetwork#addSubNetwork} for example).
</p>


<h4>CyNetworks and CyTables</h4>
When a {@link org.cytoscape.model.CyNetwork} is created, Cytoscape also creates a series of {@link org.cytoscape.model.CyTable}s
to contain information about the network and its nodes and edges.  Some of these tables are public (visible to the user) but local
to this network, some
are hidden, and some are shared with all of the other networks with the same {@link org.cytoscape.model.subnetwork.CyRootNetwork}.

Each {@link org.cytoscape.model.CyTable} has a name, and may be accessed using that name.  For example, first two tables mentioned
above are {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS} and {@link org.cytoscape.model.CyNetwork#HIDDEN_ATTRS}.  The shared table
is accessed through the {@link org.cytoscape.model.subnetwork.CyRootNetwork} and has the name
{@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS}.  While having separate local and shared tables provides lots
of flexibility for certain applications, for the most part, an App developer will just want to get all of the public information
about a network, node, or edge.  To facilitate this, Cytoscape provides a "default" table 
({@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS}).  This table contains pointers to all of the local and shared information for
the corresponding network, node, or edge.  App implementers should be aware that when a new column is created in the default
table, it is actually created in the {@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS} table and made available to
all networks in the same root.  Use the {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS} table to specifically create a column
local to only this network.  For example, to get the hidden table for nodes:
<dl>
<dd><code>CyTable hiddenTable = network.getTable(CyNode.class, CyNetwork.HIDDEN_ATTRS);</code></dd>
</dl>
and to get the default row for an edge, there are two approaches:
<dl>
<dd><code>CyRow edgeRow = network.getRow(edge, CyNetwork.DEFAULT_ATTRS);</code></dd>
</dl>
or, since the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} table is the default:
<dl>
<dd><code>CyRow edgeRow = network.getRow(edge);</code></dd>
</dl>
Cytoscape provides convenience methods to get the default table for a network 
({@link org.cytoscape.model.CyNetwork#getDefaultNetworkTable}), 
nodes ({@link org.cytoscape.model.CyNetwork#getDefaultNodeTable}), and 
edges ({@link org.cytoscape.model.CyNetwork#getDefaultEdgeTable}).
<p>One thing to keep in mind -- when working with the {@link org.cytoscape.model.CyNetwork#getRow} methods, the argument
is the actual {@link org.cytoscape.model.CyNode} and {@link org.cytoscape.model.CyEdge}.  However, when you are using the
{@link org.cytoscape.model.CyTable#getRow} method, the argument is the <i>ID</i> 
({@link org.cytoscape.model.CyIdentifiable#SUID}) of the desired
{@link org.cytoscape.model.CyNode} or {@link org.cytoscape.model.CyEdge}.  This is because the {@link org.cytoscape.model.CyTable}
interface is meant to provide a general mechanism for tabular data and the primary keys aren't necessarily
{@link org.cytoscape.model.CyNode}, {@link org.cytoscape.model.CyEdge}, or even SUIDs.
<h4>Managing CyNetworks ({@link org.cytoscape.model.CyNetworkManager}) and CyTables ({@link org.cytoscape.model.CyTableManager})</h4>
A generalized graph or network is a very useful data construct that can be usefully used by algorithms and Apps for
purposes other than providing a user with a network visualization.  Since a lot of Apps will need to know about
the creation of new networks the user might want to manipulate, Cytoscape provides a mechanism to "register"
networks that are meant to be available to the user.  The {@link org.cytoscape.model.CyNetworkManager} is the repository
for the information about registered networks.  It provides mechanisms to add ({@link org.cytoscape.model.CyNetworkManager#addNetwork})
remove ({@link org.cytoscape.model.CyNetworkManager#destroyNetwork}) and inquire about networks
({@link org.cytoscape.model.CyNetworkManager#getNetwork}, {@link org.cytoscape.model.CyNetworkManager#getNetworkSet}, and
{@link org.cytoscape.model.CyNetworkManager#networkExists}).  Similarly, the {@link org.cytoscape.model.CyTableManager}
is the repository for the information about registered tables.
<h4>Events ({@link org.cytoscape.model.events})</h4>
Sometimes, it's useful for an App to receive notification that something has changed within the model.  For example,
an App that provides the user with a list of available column names would want to update that list when
a new network was added.  Two very common examples are to be notified when a network has been added, and
to be notified when the the user has selected something.   Cytoscape 3 event listeners use the OSGi service model rather than
the older "addXXXListener" used by previous versions of Cytoscape.  To listen for the addition of a new network, for example,
the App writer would provide a class that implements {@link org.cytoscape.model.events.NetworkAddedListener}.  This interface
provides a single method: {@link org.cytoscape.model.events.NetworkAddedListener#handleEvent}, which takes as an argument
the {@link org.cytoscape.model.events.NetworkAddedEvent}, which is fired by the {@link org.cytoscape.model.CyNetworkManager} when
a new network is added (using {@link org.cytoscape.model.CyNetworkManager#addNetwork}).  In order for this listener
to be called, it must be registered as an OSGi service.  If the App is an OSGi bundle, this may be done in the 
<b>CyActivator</b> class provided by the bundle.  If the App is a Simple App (inherits from {@link org.cytoscape.app.AbstractCyApp}) then
the App implementer should use the following code:
<dl>
<dd><pre><code>
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
NetworkAddedListener myListener = new MyListener();
serviceRegistrar.registerService(myListener, NetworkAddedListener.class, new Properties());
</code></pre></dd>
</dl>
Selection in Cytoscape 3 is actually handled by setting a boolean value in a {@link org.cytoscape.model.CyTable} -- 
in this case, the {@link org.cytoscape.model.CyNetwork#SELECTED} column in the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} 
table for the associated node or edge.  As a result, the way to listen for selection is
actually to listen for a change in the appropriate row:
<dl>
<dd><pre><code>
public class MyListener implements RowsSetListener {
	// Probably want a CyNetwork or list of CyNetworks as arguments here?
	public MyListener() {
	}

	public void handleEvent(RowsSetEvent e) {
		Collection&lt;RowSetRecord&gt; rowsSet = e.getColumnRecords(CyNetwork.SELECTED);
		for (RowSetRecord record: rowsSet) {
			CyRow row = record.getRow(); // Get the row that was set
			boolean selected = ((Boolean)record.getValue()).booleanValue();  // What it was set to
			// Take appropriate action.  For example, might want to get
			// the node or edge that was selected (or unselected)
			// CyNode node = network.getNode(row.get(CyIdentifiable.SUID, Long.class));
		}
	}
}
</code></pre></dd>
</dl>
and then, to register the listener...
<dl>
<dd><pre><code>
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
NetworkAddedListener myListener = new MyListener();
serviceRegistrar.registerService(myListener, RowsSetListener.class, new Properties());
</code></pre></dd>
</dl>

<h3>Some hints</h3>
<h4>Selected nodes and edges</h4>
One of the common requirements for Apps is to be able to get the list of selected nodes or edges.  In Cytoscape, the information
about what's selected is stored in the {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS} table for nodes and edges, which is
also available through the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} table.  Because getting the state of nodes and edges
is such a common equirement, Cytoscape provides some utility methods ({@link org.cytoscape.model.CyTableUtil}) to help out.  For
selection, there are two: {@link org.cytoscape.model.CyTableUtil#getEdgesInState} and 
{@link org.cytoscape.model.CyTableUtil#getNodesInState}.  These methods can be easily used to get the list of selected nodes or edges.  
For example, to get the list of selected nodes:
<dl>
<dd><code>List&lt;CyNode&gt; selectedNodes = CyTableUtil.getNodesInState(network, CyNetwork#SELECTED, true);</code></dd>
</dl>
*/
package org.cytoscape.model;
