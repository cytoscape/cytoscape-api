package org.cytoscape.work;


/** Provides access to a TunableInterceptor to all derived classes and a utility method to determine
 *  if an object has been annotated with {@link Tunable}s.
 */
public abstract class AbstractTaskManager implements TaskManager {
	protected final TunableInterceptor tunableInterceptor;

	/**
	 *  Initializes an <code>AbstractTaskManager</code> object by setting its <code>TunableInterceptor</code>.
	 *
	 *  @param tunableInterceptor The <code>TunableInterceptor</code> to be used by this <code>TaskManager</code>.
	 */
	public AbstractTaskManager(final TunableInterceptor tunableInterceptor) {
		this.tunableInterceptor = tunableInterceptor;
	}

	final public boolean hasTunables(final Object o) {
		return tunableInterceptor.hasTunables(o);
	}

	abstract public void execute(TaskFactory factory);
}
