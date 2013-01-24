

package org.cytoscape.work;

/**
 * TunableRecorder is a special type of tunable interceptor that 
 * reads the state of the tunables <b>but does not modify the 
 * value of the tunables</b>. There may be many TunableRecorder
 * services registered at once.  TunableRecorders will be
 * called by the {@link TaskManager} <i>after</i> the 
 * {@link TunableMutator} is called, but <i>before</i> the
 * {@link Task} is executed.
 * </br>
 * TunableRecorders are designed to help record the state of
 * Cytoscape for provenance and other tracability purposes.
 * </br>
 * When implementing this interface, it is recommended that you
 * use the {@link AbstractTunableInterceptor} class to do so.
 * @param <T> The generic TunableHandler type of this TunableRecorder.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TunableRecorder<T extends TunableHandler> {

	/**
	 * The method called for each tunable object processed
	 * by a {@link TaskManager}. This method may observe
	 * and record the state of tunables associated with the argument
	 * object, but may <b>NOT</b> modify the tunable values.
	 * <br/>
	 * To prevent any performance problems TunableRecorders should
	 * return as fast as possible.  This might mean spawning a new
	 * thread to do the actual recording so as not to interrupt the
	 * the system.
	 * @param objs The object containing fields and methods annotated
	 * with the {@link Tunable} annotation.
	 */
	void recordTunableState(Object objs);
}
