package org.cytoscape.filter.transformers;

import org.cytoscape.filter.model.TransformerSink;
import org.cytoscape.filter.predicates.Predicate;

/**
 * Identifiers of the {@link org.cytoscape.filter.model.Transformer Transformer}s
 * supplied by the core.  Details of the tunable parameters for each Transformer
 * are specified here as well.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface Transformers {
	/**
	 * The id of the core Column Filter, which finds nodes and edges based on their column values.
	 * 
	 * <dl>
	 * <dt><b>Tunable parameters:</b></dt>
	 * <dd>
	 * <dl>
	 * 
	 * <dt><code>type</code></dt>
	 * <dd>Determines what type of objects will be matched.  Must be one of the following Strings:
	 * <ul>
	 * <li><code>nodes</code></li>
	 * <li><code>edges</code></li>
	 * <li><code>nodes+edges</code></li>
	 * </ul>
	 * </dd>
	 * 
	 * <dt><code>caseSensitive</code></dt>
	 * <dd>Applicable only to String columns. Set this to <code>true</code> if
	 * the predicate should be applied to the criterion in a case-sensitive way,
	 * or <code>false</code>, otherwise. </dd>
	 * 
	 * <dt><code>columnName</code></dt>
	 * <dd>The name of the column the predicate will be applied to.</dd>
	 * 
	 * <dt><code>criterion</code></dt>
	 * <dd>When used as with a binary {@link Predicate}, this value is passed as
	 * the second argument. In the case of a ternary predicate (e.g.
	 * {@link Predicate#BETWEEN}), this should be a 2-element
	 * <code>Number</code> array containing the second and third arguments.</dd>
	 * 
	 * <dt><code>predicate</code></dt>
	 * <dd>The {@link Predicate} used to test the column's cell value, which is
	 * passed as the first argument to the predicate.</dd>
	 * 
	 * </dl>
	 * </dd>
	 * </dl>
	 */
	static final String COLUMN_FILTER = "org.cytoscape.ColumnFilter";
	
	/**
	 * The id of the core Degree Filter, which finds nodes based on their degree.
	 * 
	 * <dl>
	 * <dt><b>Tunable parameters:</b></dt>
	 * <dd>
	 * <dl>
	 * 
	 * <dt><code>edgeType</code></dt>
	 * <dd>Determines which edges to follow when computing the degree.</dd>
	 * 
	 * <dt><code>criterion</code></dt>
	 * <dd>When used as with a binary {@link Predicate}, this value is passed as
	 * the second argument. In the case of a ternary predicate (e.g.
	 * {@link Predicate#BETWEEN}), this should be a 2-element
	 * <code>Number</code> array containing the second and third arguments.</dd>
	 * 
	 * <dt><code>predicate</code></dt>
	 * <dd>The {@link Predicate} used to test against the node degree, which is
	 * passed as the first argument to the predicate.</dd>
	 * 
	 * </dl>
	 * </dd>
	 * </dl>
	 */
	static final String DEGREE_FILTER = "org.cytoscape.DegreeFilter";

	/**
	 * The id of the core Topology Filter, which finds nodes based on the size
	 * of their neighborhood.  In particular, this filter picks nodes with
	 * a particular number of neighbors within a given distance.
	 * 
	 * <dl>
	 * <dt><b>Tunable parameters:</b></dt>
	 * <dd>
	 * <dl>
	 * 
	 * <dt><code>distance</code></dt>
	 * <dd>The maximum distance to consider when computing the size of a node's
	 * neighborhood.</dd>
	 * 
	 * <dt><code>threshold</code></dt>
	 * <dd>This value is passed as the second argument to the {@link Predicate}.</dd>
	 * 
	 * <dt><code>predicate</code></dt>
	 * <dd>The binary {@link Predicate} used to test against the node
	 * neighborhood size, which is passed as the first argument to the
	 * predicate.</dd>
	 * 
	 * </dl>
	 * </dd>
	 * </dl>
	 */
	static final String TOPOLOGY_FILTER = "org.cytoscape.TopologyFilter";

	/**
	 * The id of the core Interaction Transformer, which finds nodes based on
	 * how they are connected to the input edges. 
	 * 
	 * <dl>
	 * <dt><b>Tunable parameters:</b></dt>
	 * <dd>
	 * <dl>
	 * 
	 * <dt><code>selectSource</code></dt>
	 * <dd>When <code>true</code>, adds the source nodes of the input edges to the {@link TransformerSink}.</dd>
	 * 
	 * <dt><code>selectTarget</code></dt>
	 * <dd>When <code>true</code>, adds the target nodes of the input edges to the {@link TransformerSink}.</dd>
	 * 
	 * </dl>
	 * </dd>
	 * </dl>
	 */
	static final String INTERACTION_TRANSFORMER = "org.cytoscape.InteractionTransformer";
	
	/**
	 * The id of the core Adjacency Transformer, which finds adjacent nodes and edges
	 * based on how they are connected to the input nodes. 
	 * 
	 * <dl>
	 * <dt><b>Tunable parameters:</b></dt>
	 * <dd>
	 * <dl>
	 * 
	 * <dt><code>action</code></dt>
	 * <dd>ADD - Output adjacent nodes/edges (as determined by the 'output' parameter) and also output all the nodes/edges that are in the input.</dd>
	 * <dd>REPLACE - Only output adjacent nodes/edges (as determined by the 'output' parameter).</dd>
	 * 
	 * <dt><code>output</code></dt>
	 * <dd>NODES - Only output adjacent nodes.</dd>
	 * <dd>EDGES - Only output adjacent edges.</dd>
	 * <dd>NODES_AND_EDGES - Output adjacent nodes/edges.</dd>
	 * 
	 * <dt><code>edgesAre</code></dt>
	 * <dd>INCOMING - Only consider adjacent nodes/edges when the adjacent edges are incoming.</dd>
	 * <dd>OUTGOING - Only consider adjacent nodes/edges when the adjacent edges are outgoing.</dd>
	 * <dd>INCOMING_AND_OUTGOING - Consider all adjacent nodes/edges.</dd>
     *
     * <dt><code>filterTarget</code></dt>
	 * <dd>NODES - Only apply nested sub-filter to adjacent nodes.</dd>
	 * <dd>EDGES - Only apply nested sub-filter to adjacent nodes.</dd>
	 * <dd>NODES_AND_EDGES - Apply nested sub-filter to the adjacent node and edge. Note, the filter will only pass if it is an OR filter that has separate column filters for nodes and edges.</dd>
	 * 
	 * </dl>
	 * </dd>
	 * </dl>
	 */
	static final String ADJACENCY_TRANSFORMER = "org.cytoscape.AdjacencyTransformer";
}
