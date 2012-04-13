package org.cytoscape.view.vizmap.gui.util;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;

/**
 * Utility class to provide access to basic set of Visual Properties.
 *
 */
public final class PropertySheetUtil {
	
	private static final Set<VisualProperty<?>> BASIC_PROPS = new HashSet<VisualProperty<?>>();
	
	private static boolean mode = false;
	
	// Preset Basic Properties
	static {
		BASIC_PROPS.add(BasicVisualLexicon.NODE_FILL_COLOR);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_SHAPE);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_WIDTH);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_HEIGHT);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_LABEL);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_BORDER_PAINT);
		BASIC_PROPS.add(BasicVisualLexicon.NODE_BORDER_WIDTH);
		
		BASIC_PROPS.add(BasicVisualLexicon.EDGE_STROKE_UNSELECTED_PAINT);
		BASIC_PROPS.add(BasicVisualLexicon.EDGE_WIDTH);
		BASIC_PROPS.add(BasicVisualLexicon.EDGE_LABEL);
		BASIC_PROPS.add(BasicVisualLexicon.EDGE_LINE_TYPE);
		
		BASIC_PROPS.add(BasicVisualLexicon.NETWORK_BACKGROUND_PAINT);
	}


	/**
	 * Check whether given Visual Property is categorized as Basic or not.
	 * @param vp Visual Property to be checked
	 * 
	 * @return true if it is a part of preset basic visual properties.
	 */
	public static final boolean isBasic(final VisualProperty<?> vp) {
		if(BASIC_PROPS.contains(vp))
			return true;
		else
			return false;
	}
	
	/**
	 * Check the status of display mode
	 * 
	 * @return true if it is in advanced mode.
	 */
	public static final boolean isAdvancedMode() {
		return mode;
	}
	
	/**
	 * Switch the current display mode.
	 * 
	 * @param advanced If true, it is in advanced mode.
	 */
	public static final void setMode(boolean advanced) {
		mode = advanced;
	}
	
	
	/**
	 * Add a new basic visual property.  This will be used by Rendering Engine developers.
	 * 
	 * @param vp New Visual Property to be set as basic set.
	 */
	public static final void addBasicVisualProperty(final VisualProperty<?> vp) {
		// Ignore null values.
		if(vp == null)
			return;
		
		BASIC_PROPS.add(vp);
	}
}