

package org.cytoscape.work;

/**
 * TunableRecorder is a special type of tunable interceptor that 
 * reads the state of the tunables <b>but does not modify the 
 * value of the tunables</b>.
 * </br>
 * When implementing this interface, it is recommended that you
 * use the {@link AbstractTunableInterceptor} class to do so.
 */
public interface TunableRecorder<T extends TunableHandler> {

	/**
	 * The method called for each tunable object processed
	 * by a {@link TaskManager}. This method may observe
	 * and record the state of tunables associated with the argument
	 * object, but may <b>NOT</b> modify the tunable values.
	 * @param obj The object containing fields and methods annotated
	 * with the {@link Tunable} annotation.
	 */
	void recordTunableState(Object objs);
}
