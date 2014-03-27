
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

import com.sun.tools.doclets.Taglet;
import com.sun.javadoc.Tag;
import java.util.Map;

/**
 * An abstract taglet for specifying the Cytoscape API documentation
 * taglet.
 */
abstract class AbstractApiTaglet implements Taglet {
    
    private final String name; 
    private final String header;
    private final String desc;
  
  	/**
	 * Constructor.
	 * @param name The name (tag) of the taglet.
	 * @param header The header for the html output.
	 * @param desc The description for the html output. 
	 */
	AbstractApiTaglet(String name, String header, String desc) {
		this.name = name;
		this.header = header;
		this.desc = desc;
	}

	/**
	 * Returns the name of the taglet found in the javadoc description.
	 * @return The name of the taglet found in the javadoc description.
	 */
    public String getName() { return name; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean inField() { return false; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean inConstructor() { return false; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean inMethod() { return false; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean inOverview() { return false; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean inPackage() { return false; }

	/**
	 * Returns true because this taglet only appears in the type (class) description. 
	 * @return true because this taglet  only appears in the type (class) description.
	 */
    public boolean inType() { return true; }

	/**
	 * Returns false because this taglet should not appear in this context.
	 * @return false because this taglet should not appear in this context.
	 */
    public boolean isInlineTag() { return false; }

    /**
	 * Can be used by children to easily implement register(tagletMap).
	 */
	@SuppressWarnings("unchecked")
	static void registerTaglet(Map tagletMap, Taglet tag) {
		if ( tagletMap.containsKey( tag.getName() ) )
			tagletMap.remove(tag.getName());
		tagletMap.put(tag.getName(), tag);
	}

	/**
	 * Actually generates the html included in the javadoc for this tag.
	 * @return A string of HTML to be inserted into the javadoc.
	 */
    public String toString(Tag tag) {
		String result = "<br/><b>Cytoscape Backwards Compatibility (" + header + ")</b>:  " + desc +"<br/>";
		if ( tag.text() != null && !tag.text().equals("") )
			result += tag.text() + "<br/>";
		return result;
    }
   
   	/**
	 * Simply calls toString(tag) for tag found and concatenates the result.
	 * @return A string of HTML to be inserted into the javadoc.
	 */
    public String toString(Tag[] tags) {
        String result = ""; 
		for ( Tag t : tags ) 
            result += toString(t); 
        return result; 
    }
}
