package org.cytoscape.work;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/** 
 * Provides access to a TunableInterceptor to all derived classes and a 
 * utility method to determine if an object has been annotated with Tunables.
 */
public abstract class AbstractTaskManager<T,C> implements TaskManager<T,C> {

	protected final TunableMutator tunableMutator;
	protected final List<TunableRecorder> tunableRecorders;
	protected C executionContext;

	/**
	 * Initialises an <code>AbstractTaskManager</code> object by setting 
	 * its <code>TunableInterceptor</code>.
	 *
	 * @param tunableMutator The <code>TunableMutator</code> to be 
	 * used by this <code>TaskManager</code>.
	 */
	public AbstractTaskManager(final TunableMutator tunableMutator) {
		this.tunableMutator = tunableMutator;
		tunableRecorders = new ArrayList<TunableRecorder>();
	}

	final public void addTunableRecorder(TunableRecorder roti, Map props) {
		if ( roti != null )
			tunableRecorders.add(roti);			
	}

	final public void removeTunableRecorder(TunableRecorder roti, Map props) {
		if ( roti != null )
			tunableRecorders.remove(roti);			
	}

	public void setExecutionContext(C context) {
		executionContext = context;
	}
}
