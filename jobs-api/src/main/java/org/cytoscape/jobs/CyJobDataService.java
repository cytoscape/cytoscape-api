package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
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

import java.io.Reader;
import java.io.InputStream;

import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.VisualProperty;

/**
 * The main interface for the marshalling and unmarshalling
 * of data to be exchanged with remote services.  Implementations of
 * this interface will often be independent of the remote service (e.g. a JSON implementation of
 * {@link CyJobDataService} may be used for multiple backend services).  Not all of the methods will be
 * provided by every implementation (see the default methods below).
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule jobs-api
 */
public interface CyJobDataService {
	/**
	 * The name of the service.  This is usually the classname and is the name that should
	 * be registered with OSGi.
	 *
	 * @return the service name
	 */
	public String getServiceName();

	/**
	 * Returns an empty instance of {@link CyJobData} that may be used with this service.  Note
	 * that implementations of {@link CyJobData} are not meant to be compatible even though they
	 * implement a small common subset of methods.
	 *
	 * @return an empty {@link CyJobData}
	 */
	public CyJobData getDataInstance();

	/**
	 * Add generic key, value pairs to a {@link CyJobData} item.  If the <i>data</i>
	 * argument is <b>null</b>, create a new {@link CyJobData} object initialized
	 * with <i>mapData</i>.
	 *
	 * @param data if not null, add <i>mapData</i> to this data object.
	 * @param key a key string used to retrieve the data
	 * @param mapData the data to add.
	 * @return <i>data</i> if provided, else a new {@link CyJobData} object
	 */
	public CyJobData addData(CyJobData data, String key, Map<Object, Object> mapData);

	/**
	 * Add a single data item to a {@link CyJobData} object.  If the <i>data</i>
	 * argument is <b>null</b>, create a new {@link CyJobData} object initialized
	 * with <i>mapData</i>.
	 *
	 * @param data if not null, add <i>item</i> to this data object.
	 * @param key a key string used to retrieve the data
	 * @param item the data to add.
	 * @return <i>data</i> if provided, else a new {@link CyJobData} object
	 */
	public CyJobData addData(CyJobData data, String key, Object mapData);

	/**
	 * An optional method to add 
	 * network data to a {@link CyJobData} item.  If the <i>data</i>
	 * argument is <b>null</b>, create a new {@link CyJobData} object initialized
	 * with the network data.  If the list of nodes and edges (<i>nodesAndEdges</i>
	 * is provided, the network data will be restricted to this list.  The list
	 * of <i>nodeColumns</i> and <i>edgeColumns</i> will restrict the attributes
	 * to those columns.  For implementations that intent to use a column other
	 * than the SUID to map the data back, by convention the key column should be
	 * the first column in the list and the list should not be empty.<br/>
	 *
	 * <b>NOTE: it is important that implementations of this method deal with
	 * the mapping of network, node, and edge SUIDs, which will change
	 * across sessions.  Typically, this will be done by adding a new column
	 * in the appropriate HIDDEN_ATTRS table with the name or id of the job.  A
	 * utility class {@link SUIDUtil} is provided to support one possible
	 * mechanism to do this</b>
	 *
	 * @param data if not null, add the model data to this data object.
	 * @param key a key string used to retrieve the data
	 * @param network the {@link CyNetwork} to extract the data from.
	 * @param nodesAndEdges the list of {@link org.cytoscape.modelCyNode}s and {@link org.cytoscape.modelCyEdge}s
	 * to encode.  If null or empty, the entire network will be added
	 * @param nodeColumns the list of columns to include for node data
	 * @param edgeColumns the list of columns to include for edge data
	 * @return <i>data</i> if provided, else a new {@link CyJobData} object
	 */
	default public CyJobData addData(CyJobData data, String key, CyNetwork network, 
	                                 List<? extends CyIdentifiable> nodesAndEdges,
	                                 List<String> nodeColumns, List<String> edgeColumns) {
		throw new UnsupportedOperationException("This data service doesn't support network data");
	}

	/**
	 * An optional method to
	 * add table data to a {@link CyJobData} item.  If the <i>data</i>
	 * argument is <b>null</b>, create a new {@link CyJobData} object initialized
	 * with the table data.  If the list of {@link CyRow}s (<i>rows</i> is
	 * is provided, the table data will be restricted to these rows.  The list
	 * of <i>columns</i> will restrict the data
	 * to those columns.  For implementations that intent to use a column other
	 * than the SUID to map the data back, by convention the key column should be
	 * the first column in the list and the list should not be empty.<br/>
	 *
	 * <b>NOTE: it is important that implementations of this method deal with
	 * the mapping of SUIDs, which will change
	 * across sessions.  Typically, this will be done by adding a new column
	 * in the appropriate HIDDEN_ATTRS table with the name or id of the job.  A
	 * utility class {@link SUIDUtil} is provided to support one possible
	 * mechanism to do this</b>
	 *
	 * @param data if not null, add the model data to this data object.
	 * @param key a key string used to retrieve the data
	 * @param table the {@link CyTable} to extract the data from.
	 * @param rows the list of {@link CyRow}s 
	 * to encode.  If null or empty, the entire table will be added
	 * @param columns the list of columns to include from <i>table</i> 
	 * @return <i>data</i> if provided, else a new {@link CyJobData} object
	 */
	default public CyJobData addData(CyJobData data, String key, 
	                                 CyTable table, List<CyRow> rows, List<String> columns) {
		throw new UnsupportedOperationException("This data service doesn't support table data");
	}

	/**
	 * An optional method to
	 * add network view data to a {@link CyJobData} item.  If the <i>data</i>
	 * argument is <b>null</b>, create a new {@link CyJobData} object initialized
	 * with the network view data.  If the list of nodes and edges (<i>nodesAndEdges</i>
	 * is provided, the view data will be restricted to this list.  The list
	 * of <i>properties</i> will restrict the {@link VisualProperty}s to only those
	 * in the list.
	 *
	 * <b>NOTE: it is important that implementations of this method deal with
	 * the mapping of network, node, and edge SUIDs, which will change
	 * across sessions.  Typically, this will be done by adding a new column
	 * in the appropriate HIDDEN_ATTRS table with the name or id of the job.  A
	 * utility class {@link SUIDUtil} is provided to support one possible
	 * mechanism to do this</b>
	 *
	 * @param data if not null, add the model data to this data object.
	 * @param key a key string used to retrieve the data
	 * @param networkView the {@link CyNetworkView} to extract the data from.
	 * @param nodesAndEdges the list of {@link CyNode}s and {@link CyEdge}s
	 * to encode.  If null or empty, the entire network view will be added
	 * @param properties the list of visual properties to include
	 * @return <i>data</i> if provided, else a new {@link CyJobData} object
	 */
	default public CyJobData addData(CyJobData data, String key, CyNetworkView networkView, 
	                                 List<? extends CyIdentifiable> nodesAndEdges,
	                                 List<VisualProperty<?>> properties) {
		throw new UnsupportedOperationException("This data service doesn't support network view data");
	}


	// TODO: Should the get* routines be part of the CyJobData object?

	/**
	 * Extract data from a {@link CyJobData} object.
	 *
	 * @param data the {@link CyJobData} object that has the data
	 * @param key the key that accesses the data
	 * @return the untyped data, or null if the data doesn't exist.  
	 * Note that it is up to the consumer to determine if
	 * this object is of the correct type.  
	 */
	public Object getData(CyJobData data, String key);

	/**
	 * Extract a map from a {@link CyJobData} object.
	 *
	 * @param data the {@link CyJobData} object that has the map
	 * @param key the key that accesses the map
	 * @return the map itself, or null if the data doesn't exist.  
	 */
	public Map<Object, Object> getMapData(CyJobData data, String key);

	/**
	 * Optional method to extract network data from a {@link CyJobData} object.
	 *
	 * @param data the {@link CyJobData} object that has the {@link CyNetwork}
	 * @param key the key that accesses the network
	 * @return the returned network or null if it doesn't exist.  
	 * This should include any nodes, edges, and 
	 * network tables that were included
	 */
	default public CyNetwork getNetworkData(CyJobData data, String key) {
		throw new UnsupportedOperationException("This data service doesn't support network data");
	}

	/**
	 * Optional method to extract table data (usually not model associated tables) 
	 * from a {@link CyJobData} object.
	 *
	 * @param data the {@link CyJobData} object that has the {@link CyTable}
	 * @param key the key that accesses the table
	 * @return the returned table or null if it doesn't exist.  
	 * This should include any rows and columns that were included.
	 */
	default public CyTable getTableData(CyJobData data, String key) {
		throw new UnsupportedOperationException("This data service doesn't support table data");
	}

	/**
	 * Optional method to extract data for a {@link CyNetworkView}
	 * from a {@link CyJobData} object.
	 *
	 * @param data the {@link CyJobData} object that has the {@link CyNetworkView}
	 * @param key the key that accesses the table
	 * @return the returned view or null if it doesn't exist.  
	 * Note that this will only include view data for the nodes and edges
	 * specified in the {@link CyJobData} store.  All {@link VisualProperty}
	 * values will be default unless explicitly included, in which case they
	 * will be overrides.
	 */
	default public CyNetworkView getViewData(CyJobData data, String key) {
		throw new UnsupportedOperationException("This data service doesn't support network view data");
	}

	/**
	 * This method is used to extract the data from the {@link CyJobData} object
	 * in a form that is appropriate for use by the job execution service.  This
	 * method will be called by 
	 * {@link CyJobExecutionService#executeJob(CyJob, String, Map, CyJobData)} method
	 * to serialize all of the data in the {@link CyJobData} object for submission
	 * to the remote service.
	 *
	 * @param data the input data
	 * @return the serialized data
	 */
	public Object getSerializedData(CyJobData data);

	/**
	 * This method is used to create a new {@link CyJobData} object from a serialized
	 * data object retrieved from a remote execution.  
	 * This method will be called from the
	 * {@link CyJobExecutionService#fetchResults(CyJob, CyJobData)}
	 * to unmarshal the data sent by the finished job.
	 *
	 * @param object the serialized data object
	 * @return a the {@link CyJobData} object containing the unserialized data
	 */
	public CyJobData deserialize(Object object);

	/**
	 * This method is used to create a new {@link CyJobData} object from a serialized
	 * data stream retrieved from a remote execution.
	 * This method will be called from the
	 * {@link CyJobExecutionService#fetchResults(CyJob, CyJobData)}
	 * to unmarshal the data sent by the finished job.
	 *
	 * @param object the serialized data stream
	 * @return a the {@link CyJobData} object containing the unserialized data
	 */
	public CyJobData deserialize(Reader reader);

	/**
	 * This method is used to create a new {@link CyJobData} object from a serialized
	 * data stream retrieved from a remote execution.
	 * This method will be called from the
	 * {@link CyJobExecutionService#fetchResults(CyJob, CyJobData)}
	 * to unmarshal the data sent by the finished job.
	 *
	 * @param object the serialized data stream
	 * @return a the {@link CyJobData} object containing the unserialized data
	 */
	public CyJobData deserialize(InputStream inputStream);
}
