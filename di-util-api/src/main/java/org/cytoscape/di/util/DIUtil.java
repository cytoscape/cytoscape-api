package org.cytoscape.di.util;


import org.springframework.core.InfrastructureProxy;


public final class DIUtil {
	private DIUtil() { }

	/** This method is typically used for performance optimisations to remove the overhead of indirect
	 *  method calls via infrastructure proxies.
	 *
	 *  @param  possibleProxy  an object reference that may or may not be a proxied object
	 *  @return If "possibleProxy" was a proxy, the underlying wrapped object otherwise "possibleProxy" itself.
	 */
	public static <T> T stripProxy(final T possibleProxy) {
		if (possibleProxy instanceof InfrastructureProxy)
			return (T)((InfrastructureProxy)possibleProxy).getWrappedObject();
		else
			return possibleProxy;
	}

	/** This method is typically used for performance optimisations to remove the overhead of indirect
	 *  method calls via infrastructure proxies.
	 *
	 *  @param  possibleProxies  one or more object references that may or may not be a proxied objects
	 *  @return the unwrapped proxy objects if they were proxies otherwise the original objects
	 */
	public static <T> T[] stripProxies(final T... possibleProxies) {
		final Object[] unwrappedObjs = new Object[possibleProxies.length];
		int i = 0;
		for (final T possibleProxy : possibleProxies)
			unwrappedObjs[i++] = stripProxy(possibleProxy);

		return (T[])unwrappedObjs;
	}
}
