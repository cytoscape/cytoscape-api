package org.cytoscape.dnd;


import java.awt.geom.Point2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A stateless, static utility class that provides a few methods useful for
 * interacting with Drag-n-Drop related objects.
 * @CyAPI.Static.Class
 */
public final class DropUtil {

	private static final Logger logger = LoggerFactory.getLogger(DropUtil.class);
	private DropUtil() {};

	/**
	 * A utility method to check whether the Transferable is of the type
	 * we care about.
	 * @param t The transferable object containing the data we will check.
	 * @param type The string representation of the data that we're hoping to
	 * match in the transferable.
	 * @return True if the transferable data flavors contain data that matches
	 * the specified type, false otherwise.
	 */
	public static boolean transferableMatches(Transferable t, String type) { 
		try {
			if ( t==null) 
				return false;

			DataFlavor[] dfl = t.getTransferDataFlavors();

			if ( dfl==null) 
				return false;

			for (DataFlavor d : dfl) {
				if ( GraphicalEntity.class.isAssignableFrom(d.getRepresentationClass()) ) {
					String myShape = t.getTransferData(d).toString();
					if ( myShape.equals(type) ) {
						return true;
					} 
				}
			}
		} catch (Exception e) { 
			logger.warn("Exception matching transferable",e);
		}
		return false;
	}

	/**
	 * Returns an array containing strings describing the transfer data 
	 * contained in each DataFlavor specified by the Transferable. 
	 * @param t The Transferable object to be processed.
	 * @return An array containing strings describing the transfer data 
	 * contained in each DataFlavor specified by the Transferable. Will
	 * never return null, but may return an empty array.
	 */
	public static String[] getTransferableDataStrings(Transferable t) { 
        try {
            DataFlavor[] dfl = t.getTransferDataFlavors();

            if ( dfl==null) {
				logger.warn("DataFlavors is null for specified Transferable.");
                return new String[0];
            }

			String[] names = new String[dfl.length]; 

			for (int i = 0; i < dfl.length; i++ ) {
				DataFlavor d = dfl[i];
                if ( GraphicalEntity.class.isAssignableFrom(d.getRepresentationClass()) ) {
					names[i] = t.getTransferData(d).toString();
                }
            }

			return names;

        } catch (Exception e) { 
			logger.warn("Exception accessing transferable data",e);
			return new String[0];
		}
	}
}
