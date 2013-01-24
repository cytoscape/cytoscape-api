package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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


import org.cytoscape.work.Tunable;


public class HasAnnotatedMethod {
	@Tunable(description="An annotated field", groups={"group1"}, dependsOn="other=false",
		 params="input=true;alignments=horizontal;displayState=uncollapsed;groupTitles=hidden,displayed")
	public int getAnnotatedInt() { return value; }

	public void setAnnotatedInt(int x) { value = x; }

	@Tunable(description="Another annotated field", listenForChange={"AnnotatedInt"}) 
	public String getAnnotatedString() { return svalue; }

	public void setAnnotatedString(String x) { svalue = x; }

	private int value = 47; 

	private String svalue = "hello";
}
