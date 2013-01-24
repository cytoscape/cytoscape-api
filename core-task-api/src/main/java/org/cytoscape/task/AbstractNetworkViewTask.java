package org.cytoscape.task;

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

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;

/** The base class for all tasks that need to operate on a network view.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkViewTask extends AbstractTask {
	/** The network view that descendants of this class will operate on. */
	protected final CyNetworkView view; // TODO: should be renamed to networkView

	/** A base class for tasks that need to operate on a network view.
	 *  @param view  must be a non-empty network view for descendants to operate on
	 */
	public AbstractNetworkViewTask(final CyNetworkView view) {
		if (view == null)
			throw new NullPointerException("CyNetworkView is null");

		this.view = view;	
	}
}
