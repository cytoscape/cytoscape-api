package org.cytoscape.work;

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
}
