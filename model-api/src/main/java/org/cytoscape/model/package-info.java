/**
This package has the core interfaces of network and
table data structures that are foundational to Cytoscape.  Cytoscape supports
two basic, separate types of data: network data ({@link org.cytoscape.model.CyNetwork}) and table data  
({@link org.cytoscape.model.CyTable}).

<p>
For the most part, the table and network data structures are
independent. However, Cytoscape can link them together by matching table rows with <i>network object</i> identifiers.
(<i>Network object</i> is a phrase that means nodes, edges, or networks.)
In fact, Cytoscape stores its own internal network object metadata in tables using this linkage.
</p>

<h3>CyTable</h3>
{@link org.cytoscape.model.CyTable} is Cytoscape's data structure for tabular data.  A {@link org.cytoscape.model.CyTable}
consists of columns ({@link org.cytoscape.model.CyColumn}) and rows ({@link org.cytoscape.model.CyRow}).  

<p>
Columns have unique names and are typed. A column has a single type that is only defined when it is created.
A column's type cannot be changed after it has been created.
A column would have the <i>string type</i> if it stores text. Or it could have the <i>integer type</i>
if it stores whole numbers. Besides strings and integers, columns can have other types, like booleans, floating
point numbers, and lists.
</p>

<p>
App writers are free to create columns having names meaningful to the user in the context of the app (e.g., MeanTemp). 
To avoid having multiple apps using the same column name for different data, we created a 
<a href="https://goo.gl/DgwpQu">community-maintained registry of columns created by apps</a>. App writers should 
create column names prefixed by a short identifier unique to the app (e.g., jam_MeanTemp).
</p>

<p>
You can get cell values in a table through a
{@link org.cytoscape.model.CyRow}. Each row has a <i>primary key</i>,
which is the row's unique identifier. No two rows in the
same table have the same primary key.
In each table, there is one column designated for storing the primary keys of each row.
The column for the primary key and its type are specified when the
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
Virtual columns are used to share data between networks.
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
Each {@link org.cytoscape.model.CyTable} has a unique identifier (SUID).
Network objects ({@link org.cytoscape.model.CyNetwork}s, {@link org.cytoscape.model.CyNode}s, and 
{@link org.cytoscape.model.CyEdge}s)
also have SUIDs that uniquely identify them. They all inherit from the same
interface, {@link org.cytoscape.model.CyIdentifiable}.
SUIDs are similar to primary keys. SUIDs refer to network objects and {@code CyTable}s,
whereas primary keys refer only to {@code CyRow}s. You cannot specify SUIDs, and they
always have the type {@code long}. In contrast to SUIDs, you can control the values of
primary keys and its type.
SUIDs do not change in the same session. When the user saves the session and opens it
up again later, SUIDs will stay the same. If the user imports the same network in two different
sessions (or imports the same network twice), the SUIDs of network objects are not
guaranteed to be the same for the same network objects.
</p>

<h4>Creating a {@code CyTable}</h4>
Typically, you do not need to create {@link org.cytoscape.model.CyTable}s yourself. Instead, use 
tables created for each network object. However, you may need to create separate {@code CyTable}s 
for storing tabular data not directly related to network objects. To create a
{@code CyTable}, use {@link org.cytoscape.model.CyTableFactory}.

<p>
Before creating a {@code CyTable}, you need a {@code CyTableFactory}. Getting a
{@code CyTableFactory} depends on your type of app:
  <ul>
    <li>
      <i>If your app is a simple app</i>:
      You have a class that inherits from {@link org.cytoscape.app.swing.AbstractCySwingApp} or {@link org.cytoscape.app.AbstractCyApp}.
      You use one of these classes to get {@link org.cytoscape.model.CyTableFactory} with 
      the {@link org.cytoscape.app.CyAppAdapter#getCyTableFactory} method.
    </li>
    <li>
      <i>If your app is an OSGi bundle</i>:
      You can get a {@link org.cytoscape.model.CyTableFactory} by adding this to your {@code CyActivator}
      (see {@link org.cytoscape.service.util.AbstractCyActivator}):
      <pre>{@code CyTableFactory tableFactory = getService(bc, CyTableFactory.class); }</pre>
      where {@code bc} is the OSGi {@code BundleContext}. 
    </li>
  </ul>
</p>

<h3>CyNetwork</h3>
A network ({@link org.cytoscape.model.CyNetwork}) is a collection
of nodes ({@link org.cytoscape.model.CyNode}) and edges ({@link org.cytoscape.model.CyEdge}) that connect nodes together.
An <i>edge</i> is defined as a link between two nodes. This implies that
nodes must exist in the network for the edge to exist. {@code CyNetwork}s support
<a href="http://www.wikipedia.org/wiki/Multigraph">multigraphs</a>, 
which means that two nodes can be connected together by more than one edge. {@code CyNetwork}s do not support
<a href="hhtp://www.wikipedia.org/wiki/Hypergraph">hypergraphs</a>, so edges cannot connect more than two nodes.

<h4>Nested Networks</h4>
A <i>metanode</i> is a node that has its own nested network inside of it. Metanodes establish a hierarchy of networks,
where, in a higher level network, a node contains a nested network. That contained network could, in turn, have
other metanodes that contain their own nested networks. {@code CyNode}s have <i>network pointers</i>
(see {@link org.cytoscape.model.CyNode#getNetworkPointer}), which point to the node's nested network.
However, if you want to use nested networks, use the Groups API ({@link org.cytoscape.group}).

<h4>Root networks and subnetworks</h4>
Cytoscape has multiple <i>root networks</i>
({@link org.cytoscape.model.subnetwork.CyRootNetwork}). Each root network has multiple
<i>subnetworks</i> ({@link org.cytoscape.model.subnetwork.CySubNetwork}).
When you are using a {@link org.cytoscape.model.CyNetwork}, you're really using a
{@link org.cytoscape.model.subnetwork.CySubNetwork}.
Accessing root networks and subnetworks is hidden, because you usually do not need
to know about the root network.
Root networks and subnetworks are defined in this subpackage: {@link org.cytoscape.model.subnetwork}.

<p>
All nodes and edges exist in the root network.
The subnetwork merely refers to a subset of nodes and edges in the root network.
Nodes and edges are shared amongst all of the subnetworks of the same root network.
The purpose of this structure is so that you can share table data across networks.
You can set up shared tables so that data values in one subnetwork (say for a node) are shared with the
same node in another subnetwork. This is useful for importing a lot of data (e.g. large expression data sets).
You can also "save" nodes and edges for later use by keeping them in the root network and removing them
from the subnetwork. This is used extensively
in the {@link org.cytoscape.group.CyGroup} implementation, which builds a hierarchical structure onto Cytoscape's model.
</p>

<p align="center">
<img src="doc-files/CySubNetwork-vs-CyNetwork.png">
</p>

<p>
When the first network is created by the {@link org.cytoscape.model.CyNetworkFactory}, a new
{@link org.cytoscape.model.subnetwork.CyRootNetwork} is first created. Each root network has a <i>base network</i>, 
which is a {@link org.cytoscape.model.subnetwork.CySubNetwork} that contains everything in the root network. The base network is
is returned by {@link org.cytoscape.model.CyNetworkFactory#createNetwork}.
</p>

<p>
To create a new 
{@link org.cytoscape.model.subnetwork.CySubNetwork} with the same {@code CyNetwork}'s {@link org.cytoscape.model.subnetwork.CyRootNetwork},
cast your {@link org.cytoscape.model.CyNetwork} to 
{@link org.cytoscape.model.subnetwork.CySubNetwork} and call the {@link org.cytoscape.model.subnetwork.CySubNetwork#getRootNetwork}
method:
<pre>{@code CyRootNetwork rootNetwork = ((CySubNetwork)network).getRootNetwork(); }</pre>

{@link org.cytoscape.model.subnetwork.CyRootNetwork} also provides methods to create and add new subnetworks
(see {@link org.cytoscape.model.subnetwork.CyRootNetwork#addSubNetwork}).
</p>

<h3>Tables associated with networks</h3>

<h4>Types of tables</h4>
For each {@link org.cytoscape.model.CyNetwork}, Cytoscape has several types of {@link org.cytoscape.model.CyTable}s
that store information for each type of network object.
A row in each of these tables contains data for a single network object.
Here are the types of tables:
<ol>
  <li>
    <i>Shared</i>: data in this table is shared across
    all networks with the same
    {@link org.cytoscape.model.subnetwork.CyRootNetwork} and is
    visible to the user.
  </li>
  <li>
    <i>Local</i>: data in this table is specific only to the
    network and is visible to the user.
  </li>
  <li>
    <i>Hidden</i>: data in this table is local to the
    network and is invisible to the user.
  </li>
</ol>

<p align="center">
<img src="doc-files/CyNetworks-linked-to-CyTables.png">
</p>

<h5>The default table</h5>
Most of the time, it is not important whether data is coming from local or shared tables.
To make it easier to retrieve data, we
establish <i>default tables</i>, which combine <i>local tables</i>
and the <i>shared tables</i> together
in one table. Default tables do not include <i>hidden tables</i>.

<p>
When a new column is created in the default
table, it is actually created in the shared table.
This makes the column available to
all networks in the same root. Use the private local table to create a column
local to only one network.
</p>

<h4>Getting a table associated with a network</h4>
You can get a table associated with a network using the {@link org.cytoscape.model.CyNetwork#getTable} method.
This method takes two parameters:
 <ol>
  <li>
   The first parameter specifies the type of network object you want:
   <table border="1" cellpadding="10" style="margin-top: 1em; margin-bottom: 1em;">
    <tr>
      <td>
        <i>If you want...</i>
      </td>
      <td>
        <i>Pass this in as the first parameter:</i>
      </td>
    </tr>
    <tr>
      <td>
        network data
      </td>
      <td>
        {@code CyNetwork.class}
      </td>
    </tr>
    <tr>
      <td>
        node data
      </td>
      <td>
        {@code CyNode.class}
      </td>
    </tr>
    <tr>
      <td>
        edge data
      </td>
      <td>
        {@code CyEdge.class}
      </td>
    </tr>
   </table>
  </li>
  <li>
   The second parameter specifies the type of table you want:
   <table border="1" cellpadding="10" style="margin-top: 1em; margin-bottom: 1em;">
    <tr>
      <td>
        <i>If you want...</i>
      </td>
      <td>
        <i>Pass this in as the second parameter:</i>
      </td>
    </tr>
    <tr>
      <td>
        the shared table
      </td>
      <td>
        {@link org.cytoscape.model.subnetwork.CyRootNetwork#SHARED_ATTRS}
      </td>
    </tr>
    <tr>
      <td>
        the local table
      </td>
      <td>
        {@link org.cytoscape.model.CyNetwork#LOCAL_ATTRS}
      </td>
    </tr>
    <tr>
      <td>
        the hidden table
      </td>
      <td>
        {@link org.cytoscape.model.CyNetwork#HIDDEN_ATTRS}
      </td>
    </tr>
    <tr>
      <td>
        the default table
      </td>
      <td>
        {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS}
      </td>
    </tr>
   </table>
  </li>
 </ol>

<h5>Getting the default table</h5>
{@code CyNetwork} has three shortcut methods for getting default tables for each type of network object:
<table border="1" cellpadding="10" style="margin-top: 1em; margin-bottom: 1em;">
 <tr>
   <td>
     <i>If you want the default table of...</i>
   </td>
   <td>
     <i>Call this method:</i>
   </td>
   <td>
     <i>Which is equivalent to:</i>
   </td>
 </tr>
 <tr>
   <td>
     network data
   </td>
   <td>
     {@link org.cytoscape.model.CyNetwork#getDefaultNetworkTable}
   </td>
   <td>
     {@code CyNetwork.getTable(CyNetwork.class, CyNetwork.DEFAULT_ATTRS)}
   </td>
 </tr>
 <tr>
   <td>
     node data
   </td>
   <td>
     {@link org.cytoscape.model.CyNetwork#getDefaultNodeTable}
   </td>
   <td>
     {@code CyNetwork.getTable(CyNode.class, CyNetwork.DEFAULT_ATTRS)}
   </td>
 </tr>
 <tr>
   <td>
     edge data
   </td>
   <td>
     {@link org.cytoscape.model.CyNetwork#getDefaultEdgeTable}
   </td>
   <td>
     {@code CyNetwork.getTable(CyEdge.class, CyNetwork.DEFAULT_ATTRS)}
   </td>
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
    CyTable hiddenTable = network.getTable(CyEdge.class, CyNetwork.HIDDEN_ATTRS);
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

<h4>Registration of Networks and Tables</h4>
When you first create a network or table, it is not shown to the user.
In fact, Cytoscape is not even aware that you have just created a table or network.
You must first <i>register</i> your new table or network with 
{@link org.cytoscape.model.CyTableManager} or {@link org.cytoscape.model.CyNetworkManager},
respectively, if you want Cytoscape to be aware of the network or table and for it to
be shown to the user.

<p>
You do not have to register networks or tables. The benefit of unregistered networks
and tables is that you can use
{@code CyNetwork} and {@code CyTable} as flexible data structures. You can use them in your
own internal algorithms and storage for intermediary data, where you do not want the user
to see them.
</p>

<p>
The {@link org.cytoscape.model.CyNetworkManager} is the repository
for the information about registered networks.  It provides mechanisms to add ({@link org.cytoscape.model.CyNetworkManager#addNetwork})
remove ({@link org.cytoscape.model.CyNetworkManager#destroyNetwork}) and inquire about networks
({@link org.cytoscape.model.CyNetworkManager#getNetwork}, {@link org.cytoscape.model.CyNetworkManager#getNetworkSet}, and
{@link org.cytoscape.model.CyNetworkManager#networkExists}).  Similarly, the {@link org.cytoscape.model.CyTableManager}
is the repository for the information about registered tables.
</p>

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
to be called, it must be registered as an OSGi service:
<ul>
  <li>
    If the App is an OSGi bundle, register the listener in the {@code CyActivator} class.
  </li>
  <li>
    If the App is a Simple App, which has a class that inherits from {@link org.cytoscape.app.AbstractCyApp}, then
the App developer should use the following code:
<pre>{@code
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
XYZListener myListener = ...;
serviceRegistrar.registerService(myListener, XYZListener.class, new Properties());
}</pre>
  </li>
</ul>
</p>

<h4>Selection Event Example</h4>
Here is an example of listening to selection events.
Selection in Cytoscape 3 is handled by the boolean column
{@link org.cytoscape.model.CyNetwork#SELECTED} in the {@link org.cytoscape.model.CyNetwork#DEFAULT_ATTRS} 
table for a node or edge. To listen for selection,
you listen for changes in this column:
<pre>{@code
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
}</pre>
Now register your listener:
<pre>{@code
ServiceRegistrar serviceRegistrar = getCyServiceRegistrar(); // This comes from CyAppAdapter
NetworkAddedListener myListener = new MyListener();
serviceRegistrar.registerService(myListener, RowsSetListener.class, new Properties());
}</pre>

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
