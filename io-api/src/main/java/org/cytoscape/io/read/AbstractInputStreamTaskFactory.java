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

import org.cytoscape.io.CyFileFilter;

/**
 * TODO: Missing documentation
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule io-api
 */
public abstract class AbstractInputStreamTaskFactory implements InputStreamTaskFactory {
	
	private CyFileFilter fileFilter;

	public AbstractInputStreamTaskFactory(final CyFileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReady(InputStream is, String inputName) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CyFileFilter getFileFilter() {
		return fileFilter;
	}
}
