package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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



import java.util.*;


/**
 * This class provides a comparator to order the {@link Tunable}s based on their
 * gravity value.
 * <br/>
 * @CyAPI.Class
 * @CyAPI.InModule work-api
 */

public class TunableGravityOrderer implements Comparator<TunableHandler> {
	
	@Override
	 public int compare(TunableHandler t1, TunableHandler t2) {
		 String g1 = t1.getParams().getProperty(AbstractTunableHandler.GRAVITY, "999.0");
		 String g2 = t2.getParams().getProperty(AbstractTunableHandler.GRAVITY, "999.0");
		 if(Double.valueOf(g1) > Double.valueOf(g2))
			 return 1;
		 else if(Double.valueOf(g1).equals(Double.valueOf(g2)))
			 return 0;
		 else
			 return -1;
	 }
}
