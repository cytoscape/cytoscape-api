package org.cytoscape.app;

import java.util.Properties;

/*
 * #%L
 * Cytoscape App API (app-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.application.CyApplicationConfiguration;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.CyVersion;
import org.cytoscape.command.AvailableCommands;
import org.cytoscape.command.CommandExecutorTaskFactory;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.group.CyGroupFactory;
import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.data.CyGroupAggregationManager;
import org.cytoscape.io.datasource.DataSourceManager;
import org.cytoscape.io.read.CyNetworkReaderManager;
import org.cytoscape.io.read.CyPropertyReaderManager;
import org.cytoscape.io.read.CySessionReaderManager;
import org.cytoscape.io.read.CyTableReaderManager;
import org.cytoscape.io.util.StreamUtil;
import org.cytoscape.io.write.CyNetworkViewWriterManager;
import org.cytoscape.io.write.CyPropertyWriterManager;
import org.cytoscape.io.write.CySessionWriterManager;
import org.cytoscape.io.write.CyTableWriterManager;
import org.cytoscape.io.write.PresentationWriterManager;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyTableFactory;
import org.cytoscape.model.CyTableManager;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.property.CyProperty;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.session.CySessionManager;
import org.cytoscape.task.create.CloneNetworkTaskFactory;
import org.cytoscape.task.create.CreateNetworkViewTaskFactory;
import org.cytoscape.task.create.NewEmptyNetworkViewFactory;
import org.cytoscape.task.create.NewNetworkSelectedNodesAndEdgesTaskFactory;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.task.create.NewSessionTaskFactory;
import org.cytoscape.task.destroy.DeleteColumnTaskFactory;
import org.cytoscape.task.destroy.DeleteSelectedNodesAndEdgesTaskFactory;
import org.cytoscape.task.destroy.DeleteTableTaskFactory;
import org.cytoscape.task.destroy.DestroyNetworkTaskFactory;
import org.cytoscape.task.destroy.DestroyNetworkViewTaskFactory;
import org.cytoscape.task.edit.CollapseGroupTaskFactory;
import org.cytoscape.task.edit.ConnectSelectedNodesTaskFactory;
import org.cytoscape.task.edit.EditNetworkTitleTaskFactory;
import org.cytoscape.task.edit.ExpandGroupTaskFactory;
import org.cytoscape.task.edit.GroupNodesTaskFactory;
import org.cytoscape.task.edit.MapGlobalToLocalTableTaskFactory;
import org.cytoscape.task.edit.MapTableToNetworkTablesTaskFactory;
import org.cytoscape.task.edit.RenameColumnTaskFactory;
import org.cytoscape.task.edit.UnGroupNodesTaskFactory;
import org.cytoscape.task.edit.UnGroupTaskFactory;
import org.cytoscape.task.hide.HideSelectedEdgesTaskFactory;
import org.cytoscape.task.hide.HideSelectedNodesTaskFactory;
import org.cytoscape.task.hide.HideSelectedTaskFactory;
import org.cytoscape.task.hide.UnHideAllEdgesTaskFactory;
import org.cytoscape.task.hide.UnHideAllNodesTaskFactory;
import org.cytoscape.task.hide.UnHideAllTaskFactory;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.task.read.LoadNetworkURLTaskFactory;
import org.cytoscape.task.read.LoadTableFileTaskFactory;
import org.cytoscape.task.read.LoadTableURLTaskFactory;
import org.cytoscape.task.read.LoadVizmapFileTaskFactory;
import org.cytoscape.task.read.OpenSessionTaskFactory;
import org.cytoscape.task.select.DeselectAllEdgesTaskFactory;
import org.cytoscape.task.select.DeselectAllNodesTaskFactory;
import org.cytoscape.task.select.DeselectAllTaskFactory;
import org.cytoscape.task.select.InvertSelectedEdgesTaskFactory;
import org.cytoscape.task.select.InvertSelectedNodesTaskFactory;
import org.cytoscape.task.select.SelectAdjacentEdgesTaskFactory;
import org.cytoscape.task.select.SelectAllEdgesTaskFactory;
import org.cytoscape.task.select.SelectAllNodesTaskFactory;
import org.cytoscape.task.select.SelectAllTaskFactory;
import org.cytoscape.task.select.SelectConnectedNodesTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsNodeViewTaskFactory;
import org.cytoscape.task.select.SelectFirstNeighborsTaskFactory;
import org.cytoscape.task.select.SelectFromFileListTaskFactory;
import org.cytoscape.task.visualize.ApplyPreferredLayoutTaskFactory;
import org.cytoscape.task.visualize.ApplyVisualStyleTaskFactory;
import org.cytoscape.task.write.ExportNetworkImageTaskFactory;
import org.cytoscape.task.write.ExportNetworkViewTaskFactory;
import org.cytoscape.task.write.ExportSelectedTableTaskFactory;
import org.cytoscape.task.write.ExportTableTaskFactory;
import org.cytoscape.task.write.ExportVizmapTaskFactory;
import org.cytoscape.task.write.SaveSessionAsTaskFactory;
import org.cytoscape.view.layout.CyLayoutAlgorithmManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.presentation.RenderingEngineManager;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.properties.TunablePropertySerializerFactory;
import org.cytoscape.work.undo.UndoSupport;


/**
 * A Java-only api providing access to Cytoscape functionality.
 * This class will provide access the various core Manager and 
 * Factory interfaces defined in different API jars that are
 * normally made available to apps as OSGi services. 
 * Through
 * these interfaces developers will have access to most management
 * and creational facilities defined in the Cytoscape API, however
 * this interface omits all Swing or GUI specific services so that
 * apps may be written to be independent of the UI. For Swing
 * and GUI related services see CySwingAppAdapter.
 * This is a convenience interface intended make app development
 * as simple as possible.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule app-api
 * 
 * @deprecated (As of Cytoscape 3.7) 
 * Support for simple apps will be removed in a future version 
 * of Cytoscape, please provide an OSGi bundle app instead.
 */
@Deprecated
public interface CyAppAdapter {

	CyApplicationConfiguration getCyApplicationConfiguration();

	/**
	 * Returns an instance of {@link CyNetworkFactory}.
	 * @return an instance of {@link CyNetworkFactory}.
	 */
	CyNetworkFactory getCyNetworkFactory(); 

	/**
	 * Returns an instance of {@link CyTableFactory}.
	 * @return an instance of {@link CyTableFactory}.
	 */
	CyTableFactory getCyTableFactory(); 

	/**
	 * Returns an instance of {@link CyTableManager}.
	 * @return an instance of {@link CyTableManager}.
	 */
	CyTableManager getCyTableManager();

	/**
	 * Returns an instance of {@link CyRootNetworkManager}.
	 * @return an instance of {@link CyRootNetworkManager}.
	 */
	CyRootNetworkManager getCyRootNetworkManager(); 

	/**
	 * Returns an instance of {@link CyEventHelper}.
	 * @return an instance of {@link CyEventHelper}.
	 */
	CyEventHelper getCyEventHelper(); 

	/**
	 * Returns an instance of {@link CyNetworkManager}.
	 * @return an instance of {@link CyNetworkManager}.
	 */
	CyNetworkManager getCyNetworkManager(); 

	//
	// group api
	//

	/**
	 * Returns an instance of {@link CyGroupFactory}.
	 * @return an instance of {@link CyGroupFactory}.
	 */
	CyGroupFactory getCyGroupFactory(); 

	/**
	 * Returns an instance of {@link CyGroupAggregationManager}.
	 * @return an instance of {@link CyGroupAggregationManager}.
	 */
	CyGroupAggregationManager getCyGroupAggregationManager(); 

	/**
	 * Returns an instance of {@link CyGroupManager}.
	 * @return an instance of {@link CyGroupManager}.
	 */
	CyGroupManager getCyGroupManager(); 

	//
	// viewmodel api
	//

	/**
	 * Returns an instance of {@link CyNetworkViewFactory}.
	 * @return an instance of {@link CyNetworkViewFactory}.
	 */
	CyNetworkViewFactory getCyNetworkViewFactory();

	/**
	 * Returns an instance of {@link CyNetworkViewManager}.
	 * @return an instance of {@link CyNetworkViewManager}.
	 */
	CyNetworkViewManager getCyNetworkViewManager();

	//
	// session api
	//

	/**
	 * Returns an instance of {@link CyApplicationManager}.
	 * @return an instance of {@link CyApplicationManager}.
	 */
	CyApplicationManager getCyApplicationManager();

	/**
	 * Returns an instance of {@link CySessionManager}.
	 * @return an instance of {@link CySessionManager}.
	 */
	CySessionManager getCySessionManager();

	//
	// work api
	//

	/**
	 * Returns an instance of {@link TaskManager}.
	 * @return an instance of {@link TaskManager}.
	 */
	//TODO should this be here?  Do we want to allow an unalloyed TaskManager?
	TaskManager getTaskManager();

	/**
	 * Returns an instance of {@link UndoSupport}.
	 * @return an instance of {@link UndoSupport}.
	 */
	UndoSupport getUndoSupport();
	
	/**
	 * Returns an instance of {@link TunablePropertySerializerFactory}.
	 * @return an instance of {@link TunablePropertySerializerFactory}.
	 */
	TunablePropertySerializerFactory getTunablePropertySerializerFactory();
	
	//
	// presentation api
	//

	/**
	 * Returns an instance of {@link RenderingEngineManager}.
	 * @return an instance of {@link RenderingEngineManager}.
	 */
	RenderingEngineManager getRenderingEngineManager();

	//
	// vizmap api
	//

	/**
	 * Returns an instance of {@link VisualMappingManager}.
	 * @return an instance of {@link VisualMappingManager}.
	 */
	VisualMappingManager getVisualMappingManager();
	
	/**
	 * Returns an instance of {@link VisualStyleFactory}.
	 * @return an instance of {@link VisualStyleFactory}.
	 */
	VisualStyleFactory getVisualStyleFactory();
	
	/**
	 * Returns an instance of {@link VisualMappingFunctionFactory}.
	 * @return an instance of {@link VisualMappingFunctionFactory}.
	 */
	VisualMappingFunctionFactory getVisualMappingFunctionContinuousFactory();
	
	/**
	 * Returns an instance of {@link VisualMappingFunctionFactory}.
	 * @return an instance of {@link VisualMappingFunctionFactory}.
	 */
	VisualMappingFunctionFactory getVisualMappingFunctionDiscreteFactory();
	
	/**
	 * Returns an instance of {@link VisualMappingFunctionFactory}.
	 * @return an instance of {@link VisualMappingFunctionFactory}.
	 */
	VisualMappingFunctionFactory getVisualMappingFunctionPassthroughFactory();

	//
	// layout api
	//
	/**
	 * Returns an instance of {@link CyLayoutAlgorithmManager}.
	 * @return an instance of {@link CyLayoutAlgorithmManager}.
	 */
	CyLayoutAlgorithmManager getCyLayoutAlgorithmManager();

	//
	// property api
	//
	/**
	 * Returns an instance of {@link CyProperty} of type Properties.
	 * @return an instance of {@link CyProperty} of type Properties.
	 */
	CyProperty<Properties> getCoreProperties();

	//
	// io api
	//
	/**
	 * Returns an instance of {@link CyNetworkReaderManager}.
	 * @return an instance of {@link CyNetworkReaderManager}.
	 */
	CyNetworkReaderManager getCyNetworkViewReaderManager();

	/**
	 * Returns an instance of {@link CyPropertyReaderManager}.
	 * @return an instance of {@link CyPropertyReaderManager}.
	 */
	CyPropertyReaderManager getCyPropertyReaderManager();

	/**
	 * Returns an instance of {@link CySessionReaderManager}.
	 * @return an instance of {@link CySessionReaderManager}.
	 */
	CySessionReaderManager getCySessionReaderManager();

	/**
	 * Returns an instance of {@link CyTableReaderManager}.
	 * @return an instance of {@link CyTableReaderManager}.
	 */
	CyTableReaderManager getCyTableReaderManager();

	/**
	 * Returns an instance of {@link CyNetworkViewWriterManager}.
	 * @return an instance of {@link CyNetworkViewWriterManager}.
	 */
	CyNetworkViewWriterManager getCyNetworkViewWriterManager();

	/**
	 * Returns an instance of {@link CyPropertyWriterManager}.
	 * @return an instance of {@link CyPropertyWriterManager}.
	 */
	CyPropertyWriterManager getCyPropertyWriterManager();

	/**
	 * Returns an instance of {@link CySessionWriterManager}.
	 * @return an instance of {@link CySessionWriterManager}.
	 */
	CySessionWriterManager getCySessionWriterManager();

	/**
	 * Returns an instance of {@link CyTableWriterManager}.
	 * @return an instance of {@link CyTableWriterManager}.
	 */
	CyTableWriterManager getCyTableWriterManager();

	/**
	 * Returns an instance of {@link PresentationWriterManager}.
	 * @return an instance of {@link PresentationWriterManager}.
	 */
	PresentationWriterManager getPresentationWriterManager();

	/**
	 * Returns an instance of {@link StreamUtil}.
	 * @return an instance of {@link StreamUtil}.
	 */
	StreamUtil getStreamUtil();
	
	
	//
	// service util
	//
	/**
	 * Returns an instance of {@link CyServiceRegistrar}.
	 * @return an instance of {@link CyServiceRegistrar}.
	 */
	CyServiceRegistrar getCyServiceRegistrar();
	
	
	/**
	 * Returns an instance of {@link CyVersion}.
	 * @return an instance of {@link CyVersion}.
	 */
	CyVersion getCyVersion();
	
	
	// Datasource API
	
	/**
	 * Returns an instance of {@link DataSourceManager}.
	 * @return an instance of {@link DataSourceManager}.
	 */
	DataSourceManager getDataSourceManager();

	//
	// commands api
	//
	/**
	 * Returns an instance of {@link AvailableCommands}.
	 * @return an instance of {@link AvailableCommands}.
	 */
	AvailableCommands getAvailableCommands();

	/**
	 * Returns an instance of {@link CommandExecutorTaskFactory} which can be used
	 * to execute commands.
	 * @return an instance of {@link CommandExecutorTaskFactory}.
	 */
	CommandExecutorTaskFactory getCommandExecutorTaskFactory();
	
	//////////////////////////// core-task API ///////////////////////////////////////////////////////////

	// core-task API
	
	/**
	 * Returns an instance of {@link ApplyVisualStyleTaskFactory}.
	 * @return an instance of {@link ApplyVisualStyleTaskFactory}.
	 */
	ApplyVisualStyleTaskFactory get_ApplyVisualStyleTaskFactory();
	
	
	/**
	 * Returns an instance of {@link MapGlobalToLocalTableTaskFactory}.
	 * @return an instance of {@link MapGlobalToLocalTableTaskFactory}.
	 */
	MapGlobalToLocalTableTaskFactory get_MapGlobalToLocalTableTaskFactory();
	
	/**
	 * Returns an instance of {@link LoadNetworkFileTaskFactory}.
	 * @return an instance of {@link LoadNetworkFileTaskFactory}.
	 */
	LoadNetworkFileTaskFactory get_LoadNetworkFileTaskFactory();

	/**
	 * Returns an instance of {@link LoadNetworkURLTaskFactory}.
	 * @return an instance of {@link LoadNetworkURLTaskFactory}.
	 */
	LoadNetworkURLTaskFactory get_LoadNetworkURLTaskFactory();

	
	/**
	 * Returns an instance of {@link LoadVizmapFileTaskFactory}.
	 * @return an instance of {@link LoadVizmapFileTaskFactory}.
	 */
	LoadVizmapFileTaskFactory get_LoadVizmapFileTaskFactory();

	/**
	 * Returns an instance of {@link LoadTableFileTaskFactory}.
	 * @return an instance of {@link LoadTableFileTaskFactory}.
	 */
	LoadTableFileTaskFactory get_LoadTableFileTaskFactory();

	/**
	 * Returns an instance of {@link LoadTableURLTaskFactory}.
	 * @return an instance of {@link LoadTableURLTaskFactory}.
	 */
	LoadTableURLTaskFactory get_LoadTableURLTaskFactory();

	
	/**
	 * Returns an instance of {@link DeleteSelectedNodesAndEdgesTaskFactory}.
	 * @return an instance of {@link DeleteSelectedNodesAndEdgesTaskFactory}.
	 */
	DeleteSelectedNodesAndEdgesTaskFactory get_DeleteSelectedNodesAndEdgesTaskFactory();

	/**
	 * Returns an instance of {@link SelectAllTaskFactory}.
	 * @return an instance of {@link SelectAllTaskFactory}.
	 */
	SelectAllTaskFactory get_SelectAllTaskFactory();

	/**
	 * Returns an instance of {@link SelectAllEdgesTaskFactory}.
	 * @return an instance of {@link SelectAllEdgesTaskFactory}.
	 */
	SelectAllEdgesTaskFactory get_SelectAllEdgesTaskFactory();

	/**
	 * Returns an instance of {@link SelectAllNodesTaskFactory}.
	 * @return an instance of {@link SelectAllNodesTaskFactory}.
	 */
	SelectAllNodesTaskFactory get_SelectAllNodesTaskFactory();

	/**
	 * Returns an instance of {@link SelectAdjacentEdgesTaskFactory}.
	 * @return an instance of {@link SelectAdjacentEdgesTaskFactory}.
	 */
	SelectAdjacentEdgesTaskFactory get_SelectAdjacentEdgesTaskFactory();

	/**
	 * Returns an instance of {@link SelectConnectedNodesTaskFactory}.
	 * @return an instance of {@link SelectConnectedNodesTaskFactory}.
	 */
	SelectConnectedNodesTaskFactory get_SelectConnectedNodesTaskFactory();

	/**
	 * Returns an instance of {@link SelectFirstNeighborsTaskFactory}.
	 * @return an instance of {@link SelectFirstNeighborsTaskFactory}.
	 */
	SelectFirstNeighborsTaskFactory get_SelectFirstNeighborsTaskFactory();

	/**
	 * Returns an instance of {@link SelectFirstNeighborsTaskFactory}.
	 * @return an instance of {@link SelectFirstNeighborsTaskFactory}.
	 */
	SelectFirstNeighborsTaskFactory get_SelectFirstNeighborsTaskFactoryInEdge();

	/**
	 * Returns an instance of {@link SelectFirstNeighborsTaskFactory}.
	 * @return an instance of {@link SelectFirstNeighborsTaskFactory}.
	 */
	SelectFirstNeighborsTaskFactory get_SelectFirstNeighborsTaskFactoryOutEdge();

	/**
	 * Returns an instance of {@link DeselectAllTaskFactory}.
	 * @return an instance of {@link DeselectAllTaskFactory}.
	 */
	DeselectAllTaskFactory get_DeselectAllTaskFactory();

	/**
	 * Returns an instance of {@link DeselectAllEdgesTaskFactory}.
	 * @return an instance of {@link DeselectAllEdgesTaskFactory}.
	 */
	DeselectAllEdgesTaskFactory get_DeselectAllEdgesTaskFactory();

	/**
	 * Returns an instance of {@link DeselectAllNodesTaskFactory}.
	 * @return an instance of {@link DeselectAllNodesTaskFactory}.
	 */
	DeselectAllNodesTaskFactory get_DeselectAllNodesTaskFactory();

	/**
	 * Returns an instance of {@link InvertSelectedEdgesTaskFactory}.
	 * @return an instance of {@link InvertSelectedEdgesTaskFactory}.
	 */
	InvertSelectedEdgesTaskFactory get_InvertSelectedEdgesTaskFactory();

	/**
	 * Returns an instance of {@link InvertSelectedNodesTaskFactory}.
	 * @return an instance of {@link InvertSelectedNodesTaskFactory}.
	 */
	InvertSelectedNodesTaskFactory get_InvertSelectedNodesTaskFactory();

	/**
	 * Returns an instance of {@link SelectFromFileListTaskFactory}.
	 * @return an instance of {@link SelectFromFileListTaskFactory}.
	 */
	SelectFromFileListTaskFactory get_SelectFromFileListTaskFactory();

	/**
	 * Returns an instance of {@link SelectFirstNeighborsNodeViewTaskFactory}.
	 * @return an instance of {@link SelectFirstNeighborsNodeViewTaskFactory}.
	 */
	SelectFirstNeighborsNodeViewTaskFactory get_SelectFirstNeighborsNodeViewTaskFactory();

	/**
	 * Returns an instance of {@link HideSelectedTaskFactory}.
	 * @return an instance of {@link HideSelectedTaskFactory}.
	 */
	HideSelectedTaskFactory get_HideSelectedTaskFactory();

	/**
	 * Returns an instance of {@link HideSelectedNodesTaskFactory}.
	 * @return an instance of {@link HideSelectedNodesTaskFactory}.
	 */
	HideSelectedNodesTaskFactory get_HideSelectedNodesTaskFactory();

	/**
	 * Returns an instance of {@link HideSelectedEdgesTaskFactory}.
	 * @return an instance of {@link HideSelectedEdgesTaskFactory}.
	 */
	HideSelectedEdgesTaskFactory get_HideSelectedEdgesTaskFactory();

	/**
	 * Returns an instance of {@link UnHideAllTaskFactory}.
	 * @return an instance of {@link UnHideAllTaskFactory}.
	 */
	UnHideAllTaskFactory get_UnHideAllTaskFactory();

	/**
	 * Returns an instance of {@link UnHideAllNodesTaskFactory}.
	 * @return an instance of {@link UnHideAllNodesTaskFactory}.
	 */
	UnHideAllNodesTaskFactory get_UnHideAllNodesTaskFactory();

	/**
	 * Returns an instance of {@link UnHideAllEdgesTaskFactory}.
	 * @return an instance of {@link UnHideAllEdgesTaskFactory}.
	 */
	UnHideAllEdgesTaskFactory get_UnHideAllEdgesTaskFactory();

	/**
	 * Returns an instance of {@link NewEmptyNetworkViewFactory}.
	 * @return an instance of {@link NewEmptyNetworkViewFactory}.
	 */
	NewEmptyNetworkViewFactory get_NewEmptyNetworkViewFactory();

	/**
	 * Returns an instance of {@link NewNetworkSelectedNodesAndEdgesTaskFactory}.
	 * @return an instance of {@link NewNetworkSelectedNodesAndEdgesTaskFactory}.
	 */
	NewNetworkSelectedNodesAndEdgesTaskFactory get_NewNetworkSelectedNodesAndEdgesTaskFactory();

	/**
	 * Returns an instance of {@link NewNetworkSelectedNodesOnlyTaskFactory}.
	 * @return an instance of {@link NewNetworkSelectedNodesOnlyTaskFactory}.
	 */
	NewNetworkSelectedNodesOnlyTaskFactory get_NewNetworkSelectedNodesOnlyTaskFactory();

	/**
	 * Returns an instance of {@link CloneNetworkTaskFactory}.
	 * @return an instance of {@link CloneNetworkTaskFactory}.
	 */
	CloneNetworkTaskFactory get_CloneNetworkTaskFactory();

	/**
	 * Returns an instance of {@link DestroyNetworkTaskFactory}.
	 * @return an instance of {@link DestroyNetworkTaskFactory}.
	 */
	DestroyNetworkTaskFactory get_DestroyNetworkTaskFactory();

	/**
	 * Returns an instance of {@link DestroyNetworkViewTaskFactory}.
	 * @return an instance of {@link DestroyNetworkViewTaskFactory}.
	 */
	DestroyNetworkViewTaskFactory get_DestroyNetworkViewTaskFactory();


	/**
	 * Returns an instance of {@link EditNetworkTitleTaskFactory}.
	 * @return an instance of {@link EditNetworkTitleTaskFactory}.
	 */
	EditNetworkTitleTaskFactory get_EditNetworkTitleTaskFactory();

	/**
	 * Returns an instance of {@link CreateNetworkViewTaskFactory}.
	 * @return an instance of {@link CreateNetworkViewTaskFactory}.
	 */
	CreateNetworkViewTaskFactory get_CreateNetworkViewTaskFactory();

	/**
	 * Returns an instance of {@link ExportNetworkImageTaskFactory}.
	 * @return an instance of {@link ExportNetworkImageTaskFactory}.
	 */
	ExportNetworkImageTaskFactory get_ExportNetworkImageTaskFactory();

	/**
	 * Returns an instance of {@link ExportNetworkViewTaskFactory}.
	 * @return an instance of {@link ExportNetworkViewTaskFactory}.
	 */
	ExportNetworkViewTaskFactory get_ExportNetworkViewTaskFactory();

	/**
	 * Returns an instance of {@link ExportSelectedTableTaskFactory}.
	 * @return an instance of {@link ExportSelectedTableTaskFactory}.
	 */
	ExportSelectedTableTaskFactory get_ExportSelectedTableTaskFactory();
	
	/**
	 * Returns an instance of {@link ExportTableTaskFactory}.
	 * @return an instance of {@link ExportTableTaskFactory}.
	 */
	ExportTableTaskFactory get_ExportTableTaskFactory();
	
	/**
	 * Returns an instance of {@link ExportVizmapTaskFactory}.
	 * @return an instance of {@link ExportVizmapTaskFactory}.
	 */
	ExportVizmapTaskFactory get_ExportVizmapTaskFactory();

	/**
	 * Returns an instance of {@link NewSessionTaskFactory}.
	 * @return an instance of {@link NewSessionTaskFactory}.
	 */
	NewSessionTaskFactory get_NewSessionTaskFactory();

	/**
	 * Returns an instance of {@link OpenSessionTaskFactory}.
	 * @return an instance of {@link OpenSessionTaskFactory}.
	 */
	OpenSessionTaskFactory get_OpenSessionTaskFactory();

	
	/**
	 * Returns an instance of {@link SaveSessionAsTaskFactory}.
	 * @return an instance of {@link SaveSessionAsTaskFactory}.
	 */
	SaveSessionAsTaskFactory get_SaveSessionAsTaskFactory();

	
	
	/**
	 * Returns an instance of {@link ApplyPreferredLayoutTaskFactory}.
	 * @return an instance of {@link ApplyPreferredLayoutTaskFactory}.
	 */
	ApplyPreferredLayoutTaskFactory get_ApplyPreferredLayoutTaskFactory();

	/**
	 * Returns an instance of {@link DeleteColumnTaskFactory}.
	 * @return an instance of {@link DeleteColumnTaskFactory}.
	 */
	DeleteColumnTaskFactory get_DeleteColumnTaskFactory();

	/**
	 * Returns an instance of {@link RenameColumnTaskFactory}.
	 * @return an instance of {@link RenameColumnTaskFactory}.
	 */
	RenameColumnTaskFactory get_RenameColumnTaskFactory();
	
	/**
	 * Returns an instance of {@link DeleteTableTaskFactory}.
	 * @return an instance of {@link DeleteTableTaskFactory}.
	 */
	DeleteTableTaskFactory get_DeleteTableTaskFactory();

	/**
	 * Returns an instance of {@link ConnectSelectedNodesTaskFactory}.
	 * @return an instance of {@link ConnectSelectedNodesTaskFactory}.
	 */
	ConnectSelectedNodesTaskFactory get_ConnectSelectedNodesTaskFactory();

	/**
	 * Returns an instance of {@link GroupNodesTaskFactory}.
	 * @return an instance of {@link GroupNodesTaskFactory}.
	 */
	GroupNodesTaskFactory get_GroupNodesTaskFactory();

	/**
	 * Returns an instance of {@link UnGroupTaskFactory}.
	 * @return an instance of {@link UnGroupTaskFactory}.
	 */
	UnGroupTaskFactory get_UnGroupTaskFactory();

	/**
	 * Returns an instance of {@link 	CollapseGroupTaskFactory}.
	 * @return an instance of {@link 	CollapseGroupTaskFactory}.
	 */
	CollapseGroupTaskFactory get_CollapseGroupTaskFactory();

	/**
	 * Returns an instance of {@link ExpandGroupTaskFactory}.
	 * @return an instance of {@link ExpandGroupTaskFactory}.
	 */
	ExpandGroupTaskFactory get_ExpandGroupTaskFactory();

	/**
	 * Returns an instance of {@link UnGroupNodesTaskFactory}.
	 * @return an instance of {@link UnGroupNodesTaskFactory}.
	 */
	UnGroupNodesTaskFactory get_UnGroupNodesTaskFactory();

	/**
	 * Returns an instance of {@link MapTableToNetworkTablesTaskFactory}.
	 * @return an instance of {@link MapTableToNetworkTablesTaskFactory}.
	 */
	MapTableToNetworkTablesTaskFactory get_MapTableToNetworkTablesTaskFactory();
}
