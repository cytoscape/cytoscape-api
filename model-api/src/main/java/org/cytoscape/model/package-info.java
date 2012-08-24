/**
This package has the core interfaces that define the basic network and
table data structures that are foundational to Cytoscape.  Cytoscape supports
two basic, separate types of data: network data ({@link org.cytoscape.model.CyNetwork}) and table data  
({@link org.cytoscape.model.CyTable}).

<p>
For the most part, the table and network data structures are
independent. However, Cytoscape can link them together by matching table rows with network object identifiers.
In fact, Cytoscape uses this linkage internally to store network metadata in the tables.
</p>

<h3>CyTable</h3>
{@link org.cytoscape.model.CyTable} is Cytoscape's data structure for tabular data.  A {@link org.cytoscape.model.CyTable}
consists of columns ({@link org.cytoscape.model.CyColumn}) and rows ({@link org.cytoscape.model.CyRow}).  

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
You can share the contents of a column in one table 
with another table. These are called <i>virtual columns</i>.
If cell values change in one virtual column, all other tables with
that virtual column will change too.
Virtual columns are used a lot by Cytoscape to shared data between networks.
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
Each table in Cytoscape has a unique identifier (SUID).
{@link org.cytoscape.model.CyNetwork}s, {@link org.cytoscape.model.CyNode}s, and 
{@link org.cytoscape.model.CyEdge}s also have SUIDs that uniquely identify them.  They all inherit from the same
interface, {@link org.cytoscape.model.CyIdentifiable}.
</p>

<h4>Creating a CyTable ({@link org.cytoscape.model.CyTableFactory})</h4>
Usually, App developers do not need to create {@link org.cytoscape.model.CyTable}s. Instead, they use 
tables created for each network (see below). However, apps sometimes need to create separate {@code CyTable}s 
for storing tabular data that is not directly related to nodes and edges in a network.

<p>
Creating a new {@code CyTable} depends on the type of App that you are developing.
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
of nodes ({@link org.cytoscape.model.CyNode}) and edges ({@link org.cytoscape.model.CyEdge}) that connect nodes together.
It's important to note that because an edge is defined as a link between two nodes, both
nodes must exist in the network for the edge to exist. {@code CyNetwork} supports
<a href="http://www.wikipedia.org/wiki/Multigraph">multigraphs</a>, 
which means that two nodes can be connected together by more than one edge. {@code CyNetwork} does not support
<a href="hhtp://www.wikipedia.org/wiki/Hypergraph">hypergraphs</a>, so edges cannot connect more than two nodes.

<p>
Cytoscape does not directly support a network hierarchy, although
it can be implemented using the Cytoscape model by combining network pointers and subnetworks (see 
the next section).
</p>

<h4>CyRootNetwork and CySubNetwork ({@link org.cytoscape.model.subnetwork})</h4>
Cytoscape's network model is a little more complicated than what a cursory glance at the
{@link org.cytoscape.model} package suggests. Cytoscape has multiple <i>root networks</i>
({@link org.cytoscape.model.subnetwork.CyRootNetwork}). Each root network has multiple
<i>subnetworks</i> ({@link org.cytoscape.model.subnetwork.CySubNetwork}).
When you are working with a {@link org.cytoscape.model.CyNetwork}, you're really working with a
{@link org.cytoscape.model.subnetwork.CySubNetwork}. After all, {@code CySubNetwork}s are just {@code CyNetwork}s
with a couple additional methods. 
This structure is hidden since most App developers don't need
to know about the root network.

<p align="center">
<img src="doc-files/CySubNetwork-vs-CyNetwork.png">
</p>

<p>
All nodes and edges exist in the root network.
The subnetwork merely refers to a subset of nodes and edges in the root network.
Nodes and edges are shared amongst all of the subnetworks with a common root network.
The purpose of this structure is so that you can share table data across networks.
You can set up shared tables so that data values in one subnetwork (say for a node) are shared with the
same node in another subnetwork. This is useful for importing a lot of data (e.g. large expression data sets).
You can also "save" nodes and edges for later use by keeping them in the root network and removing them
from the subnetwork. This is used extensively
in the {@link org.cytoscape.group.CyGroup} implementation, which builds a hierarchical structure onto Cytoscape's model.
</p>

<p>
When the first network is created by the {@link org.cytoscape.model.CyNetworkFactory}, a new
{@link org.cytoscape.model.subnetwork.CyRootNetwork} is first created. Each root network has a <i>base network</i>, 
which is a {@link org.cytoscape.model.subnetwork.CySubNetwork} that contains everything in the root network. The base network is
is returned by {@link org.cytoscape.model.CyNetworkFactory#createNetwork}.
</p>

<p>
To create a new 
{@link org.cytoscape.model.subnetwork.CySubNetwork} that is part of the same {@link org.cytoscape.model.subnetwork.CyRootNetwork},
cast your {@link org.cytoscape.model.CyNetwork} to 
{@link org.cytoscape.model.subnetwork.CySubNetwork} and call the {@link org.cytoscape.model.subnetwork.CySubNetwork#getRootNetwork}
method:
<dl>
<dd><code>CyRootNetwork rootNetwork = ((CySubNetwork)network).getRootNetwork();</code></dd>
</dl>
{@link org.cytoscape.model.subnetwork.CyRootNetwork} provides methods to create and add new subnetworks
(see {@link org.cytoscape.model.subnetwork.CyRootNetwork#addSubNetwork}).
</p>

<h3>CyNetworks and CyTables</h3>

<h4>Types of tables</h4>
When a {@link org.cytoscape.model.CyNetwork} is created, Cytoscape creates three types of {@link org.cytoscape.model.CyTable}s
that store information about the network, its nodes, and its edges:
<ol>
  <li>
    <i>Shared</i>: data in this table is shared across
    all networks with the same
    {@link org.cytoscape.model.subnetwork.CyRootNetwork} and is
    visible to the user.
  </li>
  <li>
    <i>Local</i>: data in this table is local to the
    network and is visible to the user.
  </li>
  <li>
    <i>Hidden</i>: data in this table is local to the
    network and is invisible to the user.
  </li>
</ol>

<h5>The default table</h5>
Local and shared tables provide flexibility for certain scenarios.
However, App developers usually does not care whether data is local or shared.
They will often want to get all of the visible information
about a network, node, or edge, regardless of whether it's shared or local. <i>Default tables</i>
has all of the local and shared data for networks, nodes, and edges.

<p>
App implementers should be aware that when a new column is created in the default
table, it is actually created in the public shared table.
This makes the column available to
all networks in the same root.  Use the private local table to specifically create a column
local to only this network.
</p>

<h4>Getting a table associated with a network</h4>
<table border="1" cellpadding="10">
  <tr>
    <td></td>
    <td><i>Shared</i></td>
    <td><i>Local</i></td>
  </tr>
  <tr>
    <td><i>Network Data</i></td>
    <td>{@code cyNetwork.getTable(CyNetwork.class,} {@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS}{@code )}</td>
    <td>{@code cyNetwork.getTable(CyNetwork.class,} {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS}{@code )}</td>
  </tr>
  <tr>
    <td><i>Node Data</i></td>
    <td>{@code cyNetwork.getTable(CyNode.class,} {@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS}{@code )}</td>
    <td>{@code cyNetwork.getTable(CyNode.class,} {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS}{@code )}</td>
  </tr>
  <tr>
    <td><i>Edge Data</i></td>
    <td>{@code cyNetwork.getTable(CyEdge.class,} {@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS}{@code )}</td>
    <td>{@code cyNetwork.getTable(CyEdge.class,} {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS}{@code )}</td>
  </tr>
  <tr>
    <td></td>
    <td><i>Default</i></td>
    <td><i>Hidden</i></td>
  </tr>
  <tr>
    <td><i>Network Data</i></td>
    <td>
      {@link org.cytoscape.model.CyNetwork#getDefaultNetworkTable}
      <p align="center">&mdash;<i>or</i>&mdash;</p>
      {@code cyNetwork.getTable(CyNetwork.class,} {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS}{@code )}
    </td>
    <td valign="top">{@code cyNetwork.getTable(CyNetwork.class,} {@link org.cytoscape.model.CyNetwork#HIDDEN_ATTRS}{@code )}</td>
  </tr>
  <tr>
    <td><i>Node Data</i></td>
    <td>
      {@link org.cytoscape.model.CyNetwork#getDefaultNodeTable}
      <p align="center">&mdash;<i>or</i>&mdash;</p>
      {@code cyNetwork.getTable(CyNode.class,} {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS}{@code )}
    </td>
    <td valign="top">{@code cyNetwork.getTable(CyNode.class,} {@link org.cytoscape.model.CyNetwork#HIDDEN_ATTRS}{@code )}</td>
  </tr>
  <tr>
    <td><i>Edge Data</i></td>
    <td>
      {@link org.cytoscape.model.CyNetwork#getDefaultEdgeTable}
      <p align="center">&mdash;<i>or</i>&mdash;</p>
      {@code cyNetwork.getTable(CyEdge.class,} {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS}{@code )}
    </td>
    <td valign="top">{@code cyNetwork.getTable(CyEdge.class,} {@link org.cytoscape.model.CyNetwork#HIDDEN_ATTRS}{@code )}</td>
  </tr>
</table>

<h4>Getting node and edge data</h4>
<p>
There are two ways of getting node and edge data from one of the tables listed above.
<ol>
  <li>
    First, get the table as shown above. Then use {@link org.cytoscape.model.CyTable#getRow} method.
    Here's an example for getting the hidden attribtues of an edge:
    <pre>{@code
    CyTable hiddenTable = network.getTable(CyNode.class, CyNetwork.HIDDEN_ATTRS);
CyRow edgeRow = hiddenTable.getRow(edge.getSUID());
    }</pre>
    <p>Here, you pass in the SUID when using {@link org.cytoscape.model.CyTable#getRow} method, not the edge itself.</p>
  </li>

  <li>
    You can go straight into getting the row without having to get the table first with the
    {@link org.cytoscape.model.CyNetwork#getRow} method. Here's the same example but
    with using this method:
    <pre>{@code
        CyRow edgeRow = cyNetwork.getRow(edge, CyNetwork.HIDDEN_ATTRS);
    }</pre>
    <p>Instead of passing in the SUID, you just pass in the edge itself.</p>
  </li>
</ol>

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

<h3>Events ({@link org.cytoscape.model.events})</h3>
Apps can receive notifications that something has changed within the model.  For example,
an App that provides the user with a list of available column names would want to update that list when
a new network was added. Two very common examples are to be notified when a network has been added, and
to be notified when the the user has selected something.


<p>
Cytoscape 3 event listeners use the OSGi service model rather than
the older "addXXXListener" used by previous versions of Cytoscape.  To listen for the addition of a new network, for example,
the App writer would provide a class that implements {@link org.cytoscape.model.events.NetworkAddedListener}.  This interface
provides a single method: {@link org.cytoscape.model.events.NetworkAddedListener#handleEvent}, which takes as an argument
the {@link org.cytoscape.model.events.NetworkAddedEvent}, which is fired by the {@link org.cytoscape.model.CyNetworkManager} when
a new network is added (using {@link org.cytoscape.model.CyNetworkManager#addNetwork}).  In order for this listener
to be called, it must be registered as an OSGi service.

<ul>
  <li>
    If the App is an OSGi bundle, register the listener in the {@code CyActivator} class.
  </li>
  <li>
    If the App is a Simple App, which has a class that inherits from {@link org.cytoscape.app.AbstractCyApp}, then
the App developer should use the following code:
<dl>
<dd><pre><code>
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
NetworkAddedListener myListener = new MyListener();
serviceRegistrar.registerService(myListener, NetworkAddedListener.class, new Properties());
</code></pre></dd>
</dl>
  </li>
</ul>
</p>

<h4>Selection Event Example</h4>
Selection in Cytoscape 3 is handled by the boolean column
{@link org.cytoscape.model.CyNetwork#SELECTED} in the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} 
table for a node or edge. To listen for selection,
you listen for changes in this column:
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
Now register your listener:
<dl>
<dd><pre><code>
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
NetworkAddedListener myListener = new MyListener();
serviceRegistrar.registerService(myListener, RowsSetListener.class, new Properties());
</code></pre></dd>
</dl>

<h3>Some hints</h3>
<h4>Selected nodes and edges</h4>
A very common need is to get the list of selected nodes or edges.  In Cytoscape, selection information
is stored in the {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS} table for nodes and edges. As described above, this is
also available through the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} table.  Because getting the state of nodes and edges
is such a common equirement, Cytoscape provides some utility methods in {@link org.cytoscape.model.CyTableUtil} to make this easier, like
{@link org.cytoscape.model.CyTableUtil#getEdgesInState} and 
{@link org.cytoscape.model.CyTableUtil#getNodesInState}.  These methods can be used to get the list of selected nodes or edges.  
For example, to get the list of selected nodes:
<dl>
<dd><code>List&lt;CyNode&gt; selectedNodes = CyTableUtil.getNodesInState(network, CyNetwork#SELECTED, true);</code></dd>
</dl>
*/
package org.cytoscape.model;
