package org.cytoscape.cycl;

/***
 * Specifies the amount of local memory in an argument of a kernel call.
 * 
 * @author Dimitry Tegunov
 *
 */
public interface CyCLLocalSize 
{
	/***
	 * Gets the amount of bytes specified by the object.
	 * 
	 * @return Size in bytes
	 */
	public Long getSize();
}
