/*
  File: OpenBrowserImpl.java

  Copyright (c) 2006, The Cytoscape Consortium (www.cytoscape.org)

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

//-------------------------------------------------------------------------
// $Revision: 13206 $
// $Date: 2008-02-26 16:37:29 -0800 (Tue, 26 Feb 2008) $
// $Author: kono $
//-------------------------------------------------------------------------
package org.cytoscape.util.swing.internal;


import java.io.IOException;
import java.util.Properties;

import org.cytoscape.property.CyProperty;
import org.cytoscape.util.swing.OpenBrowser;

public class OpenBrowserImpl implements OpenBrowser {

	private final Properties props;

	private final static String UNIX_PATH = "htmlview";
	private final static String MAC_PATH = "open";
	private final static String WIN_PATH = "rundll32 url.dll,FileProtocolHandler";

	public OpenBrowserImpl(CyProperty<Properties> cyProps) {
		if ( cyProps == null )
			throw new NullPointerException("Properties is null");	
		this.props = cyProps.getProperties();
	}

	/**
	 *  DOCUMENT ME!
	 *
	 * @param url DOCUMENT ME!
	 */
	public void openURL(String url) {
		String defBrowser = props.getProperty(OpenBrowser.DEF_WEB_BROWSER_PROP_NAME);
		String osName = System.getProperty("os.name");

		try {
			String cmd;

			if (osName.startsWith("Windows")) {
				cmd = WIN_PATH + " " + url;
			} else if (osName.startsWith("Mac")) {
				cmd = MAC_PATH + " " + url;
			} else {
				if (defBrowser != null && !defBrowser.equals("")) {
					cmd = defBrowser + " " + url;
				} else {
					cmd = UNIX_PATH + " " + url;
				}
			}

			System.out.println("Opening URL by command \"" + cmd + "\"");

			Process p = Runtime.getRuntime().exec(cmd);

			try {
				int exitCode = p.waitFor();

				if (exitCode != 0)
					System.err.println("Open browser command (" + cmd + ") failed!");

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
