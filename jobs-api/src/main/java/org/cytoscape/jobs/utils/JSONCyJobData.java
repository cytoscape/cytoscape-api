package org.cytoscape.jobs.utils;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2015 The Cytoscape Consortium
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cytoscape.jobs.CyJobData;

/**
 * JSONCyJobData provides a default implementation of CyJobData
 * that can be used with simple Map-like implementations.  The
 * returned serialized object is a JSON string.
 *
 * @CyAPI.Class
 * @CyAPI.InModule jobs-api
 */
public class JSONCyJobData implements CyJobData {
	Map<String, Object> data;

	public JSONCyJobData() {
		data = new HashMap<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRaw(String key) {
		if (data.containsKey(key)) 
			return data.get(key);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> keySet() {
		return data.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSet(String key) {
		return data.containsKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getAllValues() {
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <R> R getData(String key, Class<? extends R> type) throws IllegalArgumentException {
		if (!data.containsKey(key)) return null;
		Object obj = data.get(key);
		if (obj.getClass().isAssignableFrom(type))
			return (R)obj;
		throw new IllegalArgumentException("Data for key '"+key+"' is of type '"+
										                   obj.getClass().getSimpleName()+
																			 "', which can't be cast to '"+type.getSimpleName()+"'");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <R> List<R> getListData(String key, 
	                               Class<? extends R> listElementType) throws IllegalArgumentException {
		if (!data.containsKey(key)) return null;
		List<?> listObj;
		try {
			listObj = (List)data.get(key);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Data for key '"+key+"' is not a list!");
		}
		if (listObj.size() == 0) return (List<R>)listObj;

		Object obj = listObj.get(0);
		if (obj.getClass().isAssignableFrom(listElementType))
			return (List<R>)listObj;

		throw new IllegalArgumentException("List for key '"+key+"' is of type '"+
										                   obj.getClass().getSimpleName()+
																			 "', which can't be cast to '"+listElementType.getSimpleName()+"'");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R> void setData(String key, R value) {
		data.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeData(String key) {
		data.remove(key);
	}

	/**
	 * Return the JSON string for transmission to the remote service.
	 *
	 * @return serialized JSON string
	 */
	@Override
	public Object getSerializedObject() {
		StringBuilder sb = new StringBuilder();
		// We start off as a map
		sb.append("{");
		for (String key: data.keySet()) {
			sb.append(quotedString(key)+":");
			convertData(data.get(key), sb);
			sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * For the JSON serializer it's useful to be
	 * able to merge data sets together.  Note that
	 * this may not make sense for all implementations
	 * of CyJobData.
	 *
	 * @param newData the data to merge in
	 * @return this CyJobData with the new data merged in.  Note
	 * that this does *not* create a copy.
	 */
	public CyJobData merge(CyJobData newData) {
		for (String key: newData.keySet()) {
			Object rawValue = newData.getRaw(key);
			data.put(key, rawValue);
		}
		return this;
	}

	private String quotedString(String str) {
		return "\""+str+"\"";
	}

	private void convertData(Object value, StringBuilder sb) {
		if (value instanceof JSONCyJobData) {
			String map = (String)((JSONCyJobData)value).getSerializedObject();
			sb.append(map);
		} else if (value instanceof String) {
			sb.append(quotedString((String)value));
		} else if (value instanceof List) {
			List<?> list = (List<?>)value;
			sb.append("[");
			for (Object a: list) {
				convertData(a, sb);
				sb.append(",");
			}
			sb.append("]");
		} else {
			sb.append(value.toString());
		}
		return;
	}
}
