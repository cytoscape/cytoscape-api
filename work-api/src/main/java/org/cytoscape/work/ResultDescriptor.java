package org.cytoscape.work;

import java.util.List;

/**
 * Implementations of this interface provide information about the results provided via 
 * {@link org.cytoscape.work.ObservableTask#getResults(Class)}.
 * 
 * @author David Otasek (dotasek.dev@gmail.com)
 *
 */
public interface ResultDescriptor {
	
	/**
	 * Returns the Classes that can be obtained from {@link org.cytoscape.work.ObservableTask#getResults(Class)}.
	 * 
	 * @return A list of Classes 
	 */
	public List<Class<?>> getResultTypes();
	
	/**
	 * For each class obtained from {@link org.cytoscape.work.ObservableTask#getResults(Class)}, this method provides a 
	 * valid example instance.
	 * 
	 * @param type
	 * @return A valid example instance.
	 */
	public <K> K getResultExample(Class<K> type);
}
