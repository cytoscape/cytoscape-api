
/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/

package org.cytoscape.work.util;

/**
 * A Long object which has <i>low</i> and <i>up</i> bounds
 */
public class BoundedLong extends AbstractBounded<Long> {

	/**
	 * Creates a new Bounded Long object.
	 *
	 * @param lower  The lower bound value
	 * @param initValue  Initial of default value for the Long
	 * @param upper  The upper bound value
	 * @param lowerStrict	True means that the value cannot be equal to the lower bound
	 * @param upperStrict	True means that the value cannot be equal to the upper bound
	 */
	public BoundedLong(final Long lower, final Long initValue, final Long upper, boolean lowerStrict, boolean upperStrict) {
		super(lower,initValue,upper,lowerStrict,upperStrict);
	}
	
	/**
	 * Set a new value to the BoundedLong object
	 * @param s String converted into Long
	 */
	public void setValue(String s) {
		setValue( Long.valueOf(s) );
	}
}
