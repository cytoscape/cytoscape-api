
package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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


/**
 * This is a type of tunable interceptor that reads <b>and
 * modifies</b> the values annotated with the {@link Tunable}
 * annotation.  Only one TunableMutator will be used by a given
 * {@link TaskManager}, and that is defined by the TaskManager
 * itself. You control which TunableMutator gets used by choosing
 * the appropriate TaskManager.
 * </br>
 * When implementing this interface, it is recommended that you
 * use the {@link AbstractTunableInterceptor} class to do so.
 *
 * @param <T> The specific type of {@link TunableHandler} used to 
 * process the tunables.
 * @param <S> The type of configuration object returned by this
 * TunableMutator.  
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TunableMutator<T extends TunableHandler, S> {

	/**
	 * Used configure the TunableMutator so that it builds its
	 * configuration object in the correct location. For instance,
	 * a GUI based TunableMutator might call this method with a
	 * JPanel, indicating that the TunableMutator should build its
	 * configuration within that JPanel.  This method may be a 
	 * no-op depending on the type of configuration.
	 * @param o The context object in which the configuration will be built.
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
