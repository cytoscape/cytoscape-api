
package org.cytoscape.work;


/**
 * This is a type of tunable interceptor that reads <b>and
 * modifies</b> the values annotated with the {@link Tunable}
 * annotation.  Only one TunableMutator will be used by a given
 * {@link TaskManager}, and that is defined by the TaskManager
 * itself. You control which TunableMutator gets used by chosing
 * the appropriate TaskManager.
 * </br>
 * When implementing this interface, it is recommended that you
 * use the {@link AbstractTunableInterceptor} class to do so.
 *
 * @param T The specific type of {@link TunableHandler} used to 
 * process the tunables.
 * @param S The type of configuration object returned by this
 * TunableMutator.  
 */
public interface TunableMutator<T extends TunableHandler, S> {

	/**
	 * Used configure the TunableMutator so that it builds its
	 * configuration object in the correct location. For instance,
	 * a GUI based TunableMutator might call this method with a
	 * JPanel, indicating that the TunableMutator should build its
	 * configuration within that JPanel.  This method may be a 
	 * no-op depending on the type of configuration.
	 * @param o The context object in which the configuraiton will be built.
	 */
	void setConfigurationContext(Object o);

	/**
	 * Returns the configuration object used to mutate the tunables.
	 * @param o The object containing the fields and methods annotated
	 * with the {@link Tunable} annotation.
	 * @return the configuration object used to mutate the tunables.
	 */
	S buildConfiguration(Object o);

	/**
	 * This method is called to validate and then set the values for
	 * fields and methods annotated with the {@link Tunable} annotation.
	 * @param o The object containing the fields and methods annotated
	 * with the {@link Tunable} annotation.
	 * @return Whether the tunables were successfully changed.
	 */
	boolean validateAndWriteBack(Object o);
}
