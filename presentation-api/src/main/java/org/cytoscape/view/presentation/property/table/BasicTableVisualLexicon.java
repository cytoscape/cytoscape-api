package org.cytoscape.view.presentation.property.table;

import java.awt.Paint;

import javax.swing.UIManager;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.model.Visualizable;
import org.cytoscape.view.presentation.property.AbstractVisualLexicon;
import org.cytoscape.view.presentation.property.BooleanVisualProperty;
import org.cytoscape.view.presentation.property.DefaultVisualizableVisualProperty;
import org.cytoscape.view.presentation.property.DoubleVisualProperty;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.cytoscape.view.presentation.property.PaintVisualProperty;
import org.cytoscape.view.presentation.property.StringVisualProperty;

public class BasicTableVisualLexicon extends AbstractVisualLexicon {
	
	// TEMPORARY, the renderer should extend the visual lexicon and define the root VP
	public static final VisualProperty<NullDataType> TEMP_ROOT = new NullVisualProperty(
			"TABLE_VISUAL_LEXICON_ROOT",
			"blah");
	
	
	// Four Categories of VisualProperty
	
	public static final VisualProperty<Visualizable> TABLE = new DefaultVisualizableVisualProperty("Table",
			"Table Visual Property", CyTable.class);
	
//	public static final VisualProperty<Visualizable> ROW = new DefaultVisualizableVisualProperty("Row",
//			"Row Visual Property", CyRow.class);
	
	public static final VisualProperty<Visualizable> COLUMN = new DefaultVisualizableVisualProperty("Column",
			"Column Visual Property", CyColumn.class);
	
	public static final VisualProperty<Visualizable> CELL = new DefaultVisualizableVisualProperty("Cell",
			"Cell Visual Property", CyColumn.class);
	
	
//	// VPs that apply to the entire table
//	public static final VisualProperty<Paint> TABLE_BACKGROUND_PAINT = new PaintVisualProperty(new Color(120, 120, 120),
//			PAINT_RANGE, "TABLE_BACKGROUND_PAINT", "Table Background Paint", CyTable.class);
//	
//	public static final VisualProperty<Boolean> TABLE_PRIVATE = new BooleanVisualProperty(false, 
//			"TABLE_PRIVATE", "Table Private", CyTable.class);
	
	public static final VisualProperty<TableMode> TABLE_VIEW_MODE = new TableModeVisualProperty(TableModeVisualProperty.AUTO, 
			"TABLE_VIEW_MODE", "View Mode", CyTable.class);
	
//	
//	
//	// VPs that apply to rows
//	public static final VisualProperty<Boolean> ROW_HIGHLIGHT = new BooleanVisualProperty(false, 
//			"ROW_HIGHLIGHT", "Row Highlight", CyRow.class);
//	
//	public static final VisualProperty<Boolean> ROW_SELECTED = new BooleanVisualProperty(false, 
//			"ROW_SELECTED", "Row Selection", CyRow.class);
//	
//	
//	// VPs that apply to an entire column or to the column header
//	public static final VisualProperty<Paint> COLUMN_HEADER_PAINT = new PaintVisualProperty(new Color(120, 120, 120),
//			PAINT_RANGE, "COLUMN_HEADER_PAINT", "Column Header Paint", CyColumn.class);
	
	public static final VisualProperty<Boolean> COLUMN_VISIBLE = new BooleanVisualProperty(true, 
			"COLUMN_VISIBLE", "Column Visibility", CyColumn.class);
	
	public static final VisualProperty<String> COLUMN_FORMAT = new StringVisualProperty("", ARBITRARY_STRING_RANGE,
			"COLUMN_FORMAT", "Column Number Format", CyColumn.class);
	
	// MKTODO treat this like an index for now...
	public static final VisualProperty<Double> COLUMN_GRAVITY = new DoubleVisualProperty(1.0, 
			NONE_ZERO_POSITIVE_DOUBLE_RANGE, "COLUMN_GRAVITY", "Column Gravity", CyColumn.class);
	
	
	// VPs that apply to cells within columns
	public static final VisualProperty<Paint> CELL_BACKGROUND_PAINT = new PaintVisualProperty(UIManager.getColor("Table.background"),
			PAINT_RANGE, "CELL_BACKGROUND_PAINT", "Cell Background Paint", CyColumn.class);
	
//	public static final VisualProperty<Font> CELL_FONT_FACE = new FontVisualProperty(new Font("SansSerif", Font.PLAIN, 12), 
//			"CELL_FONT_FACE", "Cell Font Face", CyColumn.class);
	
	
	
	private static BasicTableVisualLexicon instance;
	
	/** @deprecated this is temporary */
	@Deprecated
	public static synchronized BasicTableVisualLexicon getInstance() {
		if(instance == null) {
			instance = new BasicTableVisualLexicon(TEMP_ROOT);
		}
		return instance; 
	}
	
	
	
	public BasicTableVisualLexicon(VisualProperty<NullDataType> root) {
		super(root);
	}


	@Override
	protected Class<?>[] getTypes() {
		return new Class<?>[] {
			CyTable.class,
//			CyRow.class,
			CyColumn.class
		};
	}
	
	
	@Override
	protected void addVisualProperties(final VisualProperty<NullDataType> root) {
		addVisualProperty(TABLE, root);
		
//		addVisualProperty(ROW, TABLE);
		addVisualProperty(COLUMN, TABLE);
		addVisualProperty(CELL, TABLE);
		
//		addVisualProperty(TABLE_BACKGROUND_PAINT, TABLE);
//		addVisualProperty(TABLE_PRIVATE, TABLE);
//		
//		addVisualProperty(ROW_HIGHLIGHT, ROW);
//		addVisualProperty(ROW_SELECTED, ROW);
//		
//		addVisualProperty(COLUMN_HEADER_PAINT, COLUMN);
//		addVisualProperty(COLUMN_VISIBLE, COLUMN);
//		addVisualProperty(COLUMN_GRAVITY, COLUMN);
		
		addVisualProperty(CELL_BACKGROUND_PAINT, CELL);
//		addVisualProperty(CELL_FONT_FACE, CELL);
	}
	

}
