package org.cytoscape.property;

import java.util.Properties;


/**
 * A simple implementation of CyProperty&lt;Properties&gt; suitable for 
 * general purpose use. 
 * @CyAPI.Final.Class
 */
public final class SimpleCyProperty implements CyProperty<Properties> {
	
	/** Core Cytoscape Property (Cytoscape System Property) */
	public static final String CORE_PROPRERTY_NAME = "cytoscape 3";
	
	private final String name;
	private final Properties properties;
	private final CyProperty.SavePolicy savePolicy;

	/**
	 * Properties is a non-null Properties object that this CyProperty object
	 * should encapsulate.
	 * @param properties The non-null Properties object this CyProperty object
	 * should encapsulate.  Throws NullPointerException if Properties is null.
	 * @param savePolicy the {@link CyProperty.SavePolicy} of this CyProperty object.
	 */
	public SimpleCyProperty(final String name, final Properties properties, final CyProperty.SavePolicy savePolicy) {
		if (name == null)
			throw new NullPointerException("\"name\" parameter is null!");
		if (properties == null)
			throw new NullPointerException("\"properties\" parameter is null!");
		if (savePolicy == null)
			throw new NullPointerException("\"savePolicy\" parameter is null!");

		this.name = name;
		this.properties = properties;
		this.savePolicy = savePolicy;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return name;
	}
	
	/** {@inheritDoc} */
	@Override
	public Properties getProperties() {
		return properties;
	}

	/** {@inheritDoc} */
	@Override
	public CyProperty.SavePolicy getSavePolicy() {
		return savePolicy;
	}
}
