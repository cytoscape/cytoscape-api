package org.cytoscape.work.spring;


import org.cytoscape.work.AbstractTunableInterceptor;
import org.cytoscape.work.TunableHandler;

import org.springframework.core.InfrastructureProxy;


/**
 * This hack exists to handle Spring's proxy framework.  Since Spring returns
 * a proxy object rather than the original object when requesting an OSGi
 * service, we need this check to get at the original object where tunables
 * are actually defined.  This code can be safely omitted if this class isn't
 * being used with Spring.
 */
public abstract class SpringTunableInterceptor<T extends TunableHandler> 
	extends AbstractTunableInterceptor<T> {

	/**
	 * Constructor.
	 */
	public SpringTunableInterceptor() {
		super();
	}

	/**
	 * This method calls {@link AbstractTunableInterceptor.loadTunables} with the
	 * unwrapped object instead of the Spring proxy object, which is provided as
	 * an argument.
	 * @param obj The Spring proxy object from which we'd like the raw object.
	 */
	final public void loadTunables(final Object obj) {
		if (obj instanceof InfrastructureProxy)
			super.loadTunables(((InfrastructureProxy)obj).getWrappedObject());
		else
			super.loadTunables( obj );
	}

	/**
	 * This method returns the raw, unwrapped object from the Spring proxy object. 
	 * @param o The Spring proxy object from which we'd like the raw object.
	 * @return The raw, unwrapped object from the Spring proxy object. 
	 */
	final protected Object convertSpringProxyObj(final Object o) {
		if (o instanceof InfrastructureProxy)
			return ((InfrastructureProxy)o).getWrappedObject();
		else
			return o;
	}

	/**
	 * This method returns the raw, unwrapped objects from the Spring proxy objects. 
	 * @param proxyObjs The Spring proxy objects from which we'd like the raw objects.
	 * @return An array of raw, unwrapped objects from the Spring proxy objects. 
	 */
	final protected Object[] convertSpringProxyObjs(final Object... proxyObjs) {
		final Object[] objs = new Object[proxyObjs.length];
		int i = 0;
		for (final Object o : proxyObjs)
			objs[i++] = convertSpringProxyObj(o);

		return objs;
	}
}
