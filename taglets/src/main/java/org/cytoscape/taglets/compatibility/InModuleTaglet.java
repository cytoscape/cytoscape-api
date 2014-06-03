package org.cytoscape.taglets.compatibility;

/*
 * #%L
 * Cytoscape Documentation Taglets
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.util.Arrays;
import java.util.Map;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

@SuppressWarnings("restriction")
public class InModuleTaglet implements Taglet {
	public static final String NAME = "CyAPI.InModule";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean inConstructor() {
		return false;
	}

	@Override
	public boolean inField() {
		return false;
	}

	@Override
	public boolean inMethod() {
		return false;
	}

	@Override
	public boolean inOverview() {
		return false;
	}

	@Override
	public boolean inPackage() {
		return true;
	}

	@Override
	public boolean inType() {
		return true;
	}

	@Override
	public boolean isInlineTag() {
		return false;
	}

	@Override
	public String toString(Tag tag) {
		return "<hr/><p><b>Module:</b> <code>"
				+ tag.text()
				+ "</code></p>"
				+ "<p>To use this in your app, include the following dependency in your POM:</p>"
				+ "<pre>&lt;dependency>\n    &lt;groupId>org.cytoscape&lt;/groupId>\n    &lt;artifactId><b>"
				+ tag.text() + "</b>&lt;/artifactId>\n&lt;/dependency></pre>";
	}

	@Override
	public String toString(Tag[] tags) {
		System.out.println(Arrays.asList(tags));
		if (tags.length == 0) {
			return "";
		}
		System.out.println(System.getProperties());
		return toString(tags[0]);
	}

	public static void register(Map<String, Taglet> tagletMap) {
		if (tagletMap.containsKey(NAME))
			tagletMap.remove(NAME);
		Taglet tag = new InModuleTaglet();
		tagletMap.put(NAME, tag);
	}
}
