package org.cytoscape.io.read;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.io.InputStream;

import org.cytoscape.io.CyFileFilterProvider;
import org.cytoscape.work.TaskIterator;

/**
 * A super interface that allows the input stream to be set for reader
 * task factories.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface InputStreamTaskFactory extends CyFileFilterProvider {

	/**
	 * Sets the input stream that will be read by the Reader created from
	 * this factory.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input. 
	 */
	TaskIterator createTaskIterator(InputStream is, String inputName);
	
	/**
	 * Returns true if the factory is ready to be produce a TaskIterator and false otherwise.
	 * @param is The {@link java.io.InputStream} to be read.
	 * @param inputName The name of the input.
	 * @return true if the factory is ready to be produce a TaskIterator and false otherwise.
	 */
	boolean isReady(InputStream is, String inputName);
}
