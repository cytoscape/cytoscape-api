package org.cytoscape.application.swing.search;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.JComponent;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskObserver;

/**
 * This abstract implementation of {@link NetworkSearchTaskFactory} makes the method {@link #getQueryComponent()}
 * optional. If your Network Search provider extends this class and doesn't overwrite the {@link #getQueryComponent()}
 * method, Cytoscape will use a default text field instead. It will also pass the new query text to the
 * {@link #setQuery(String)} method every time the content of the default text field changes.
 */
public abstract class AbstractNetworkSearchTaskFactory extends AbstractTaskFactory implements NetworkSearchTaskFactory {
	
	private String query;
	
	private final String id;
	private final String name;
	private final String description;
	private final Icon icon;
	private final URL website;
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, Icon icon) {
		this(id, name, null, icon, null);
	}
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, String description, Icon icon) {
		this(id, name, description, icon, null);
	}
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, String description, Icon icon, URL website) {
		if (id == null || id.trim().isEmpty())
			throw new IllegalArgumentException("'id' must not be null or blank.");
		if (name == null || name.trim().isEmpty())
			throw new IllegalArgumentException("'name' must not be null or blank.");
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.website = website;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public URL getWebsite() {
		return website;
	}
	
	public String getQuery() {
		return query;
	}
	
	/**
	 * This method is called by Cytoscape whenever the user changes the query text.
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Override
	public TaskObserver getTaskObserver() {
		return null;
	}
	
	/**
	 * This implementation simply returns null, which means Cytoscape will provide a default query component,
	 * unless the subclass overwrites this method to return a custom component.
	 */
	@Override
	public JComponent getQueryComponent() {
		return null;
	}
	
	/**
	 * This implementation simply returns null, which means that no options component will be displayed,
	 * unless the subclass overwrites this method to return a custom component.
	 */
	@Override
	public JComponent getOptionsComponent() {
		return null;
	}
	
	/**
	 * A default implementation that simply checks whether or not the String returned by {@link #getQuery()} is empty.
	 */
	@Override
	public boolean isReady() {
		String s = getQuery();
		
		return s != null && !s.trim().isEmpty();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 11;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNetworkSearchTaskFactory other = (AbstractNetworkSearchTaskFactory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
