package org.cytoscape.service.util.internal;

import java.util.Dictionary;

import org.osgi.framework.Bundle;

/*
 * #%L
 * Cytoscape Service API (service-api)
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

import org.osgi.framework.ServiceReference;

public class FakeServiceReference implements ServiceReference {

	private final Object obj;

	public FakeServiceReference(Object o) {
		this.obj = o;
	}
	public int compareTo(Object reference) {
		return 0;	
	}
	public Bundle getBundle() {
		return null;
	}
	public Object getProperty(String key) {
		return null;
	}
	public String[] getPropertyKeys() {
		return null;
	}
	public Bundle[] getUsingBundles() {
		return null;
	}
	public boolean isAssignableTo(Bundle bundle, String className) {
		return false;
	}
	Object getObject() {
		return obj;
	}
	@Override
	public Dictionary getProperties() {
		return null;
	}
}
