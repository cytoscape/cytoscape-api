package org.cytoscape.task.read;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

import java.util.Set;
import java.io.File;
import java.io.InputStream;

/**
 * An interface for loading styles from files. 
 * This interface also provides a task iterator for loading files into visual styles.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadVizmapFileTaskFactory extends TaskFactory{

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param f The file containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(File f);
	

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param is The inputStream containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(InputStream is);

	/**
	 * Creates a task iterator for loading files into visual styles.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file containing visual styles to be read.
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(File file);
}
