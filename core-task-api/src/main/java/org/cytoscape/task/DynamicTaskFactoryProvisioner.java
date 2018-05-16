package org.cytoscape.task;

import org.cytoscape.work.TaskFactory;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2018 The Cytoscape Consortium
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

/**
 * TODO: Missing documentation
 * @CyAPI.InModule core-task-api
 */
public interface DynamicTaskFactoryProvisioner {
	
	TaskFactory createFor(NetworkTaskFactory factory);

	TaskFactory createFor(NetworkViewTaskFactory factory);

	TaskFactory createFor(NetworkCollectionTaskFactory factory);

	TaskFactory createFor(NetworkViewCollectionTaskFactory factory);

	TaskFactory createFor(TableTaskFactory factory);
}
