package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/** 
 * Provides access to a TunableInterceptor to all derived classes and a 
 * utility method to determine if an object has been annotated with Tunables.
 * @CyAPI.Abstract.Class 
 * @CyAPI.InModule work-api
 */
public abstract class AbstractTaskManager<T,C> implements TaskManager<T,C> {

	/**
	 * The single tunable mutator that will be used by this task manager.
	 */
	protected final TunableMutator tunableMutator;

	/**
	 * The list of tunable recorders that will be used by this task manager.
	 * Tunable recorders can be added and removed from the list with the
	 * add/remove methods below.
	 */
	protected final List<TunableRecorder> tunableRecorders;

	/**
	 * The execution context of the task manager. This can be set
	 * with the setExecutionContext method, assuming that the setExecutionContext
	 * method hasn't been overridden to do something different.
	 */
	protected C executionContext;

	/**
	 * Initializes an <code>AbstractTaskManager</code> object by setting 
	 * its <code>TunableInterceptor</code>.
	 *
	 * @param tunableMutator The <code>TunableMutator</code> to be 
	 * used by this <code>TaskManager</code>.
	 */
	public AbstractTaskManager(final TunableMutator tunableMutator) {
		this.tunableMutator = tunableMutator;
		tunableRecorders = new ArrayList<TunableRecorder>();
	}

	/**
	 * Adds the specified TunableRecorder service to the task manager. 
	 * @param tunableRecorder The TunableRecorder service to be added to this task manager.
	 * @param props The service properties associated with the tunableRecorder service. 
	 */
	public final void addTunableRecorder(TunableRecorder tunableRecorder, Map props) {
		if ( tunableRecorder != null )
			tunableRecorders.add(tunableRecorder);			
	}

	/**
	 * Removes the specified TunableRecorder service from the task manager. 
	 * @param tunableRecorder The TunableRecorder service to be removed from this task manager.
	 * @param props The service properties associated with the tunableRecorder service. 
	 */
	public final void removeTunableRecorder(TunableRecorder tunableRecorder, Map props) {
		if ( tunableRecorder != null )
			tunableRecorders.remove(tunableRecorder);			
	}

	/**
	 * Simple sets the executionContext to the specified input value. This
	 * may be overridden.
	 * @param context The execution context to be set.
	 */
	public void setExecutionContext(C context) {
		executionContext = context;
	}

	/**
	 * Returns the {@link TunableMutator} for this {@link TaskManager} or
	 * <b>null</b> if there is none.
	 *
	 * @return the {@link TunableMutator} or <b>none</b>
	 */
	@Override
	public TunableMutator<?,?> getTunableMutator() {
		return tunableMutator;
	}
}
