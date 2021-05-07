package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
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
 * A simple implementation of CyProperty suitable for general purpose use. 
 * @param <P>
 * @CyAPI.Final.Class
 */
public final class SimpleCyProperty<P> implements CyProperty<P> {
	
	/** Core Cytoscape Property (Cytoscape System Property) */
	public static final String CORE_PROPRERTY_NAME = "cytoscape 3";
	
	private final String name;
	private final P properties;
	private final Class<? extends P> propertyType;
	private final CyProperty.SavePolicy savePolicy;

	/**
	 * Properties is a non-null Properties object that this CyProperty object
	 * should encapsulate.
	 * @param properties The non-null Properties object this CyProperty object
	 * should encapsulate.  Throws NullPointerException if Properties is null.
	 * @param savePolicy the {@link CyProperty.SavePolicy} of this CyProperty object.
	 */
	public SimpleCyProperty(final String name,
							final P properties,
							final Class<? extends P> propertyType,
							final CyProperty.SavePolicy savePolicy) {
		if (name == null)
			throw new NullPointerException("\"name\" parameter is null.");
		if (properties == null)
			throw new NullPointerException("\"properties\" parameter is null.");
		if (propertyType == null)
			throw new NullPointerException("\"propertyType\" parameter is null.");
		if (savePolicy == null)
			throw new NullPointerException("\"savePolicy\" parameter is null.");

		this.name = name;
		this.properties = properties;
		this.propertyType = propertyType;
		this.savePolicy = savePolicy;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return name;
	}
	
	/** {@inheritDoc} */
	@Override
	public P getProperties() {
		return properties;
	}

	/** {@inheritDoc} */
	@Override
	public CyProperty.SavePolicy getSavePolicy() {
		return savePolicy;
	}

	/** {@inheritDoc} */
	@Override
	public Class<? extends P> getPropertyType() {
		return propertyType;
	}

	@Override
	public String toString() {
		return "SimpleCyProperty [name=" + name + ", propertyType=" + propertyType + ", savePolicy=" + savePolicy + "]";
	}
}
