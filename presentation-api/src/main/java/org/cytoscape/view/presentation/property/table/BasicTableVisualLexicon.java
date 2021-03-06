package org.cytoscape.view.presentation.property.table;

import java.awt.Font;
import java.awt.Paint;

import javax.swing.UIManager;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.util.swing.LookAndFeelUtil;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.model.Visualizable;
import org.cytoscape.view.presentation.property.AbstractVisualLexicon;
import org.cytoscape.view.presentation.property.BooleanVisualProperty;
import org.cytoscape.view.presentation.property.DefaultVisualizableVisualProperty;
import org.cytoscape.view.presentation.property.DoubleVisualProperty;
import org.cytoscape.view.presentation.property.FontVisualProperty;
import org.cytoscape.view.presentation.property.IntegerVisualProperty;
import org.cytoscape.view.presentation.property.PaintVisualProperty;
import org.cytoscape.view.presentation.property.StringVisualProperty;

public class BasicTableVisualLexicon extends AbstractVisualLexicon {
	
	private static final Font DEF_FONT = new Font("SansSerif", Font.PLAIN, (int) LookAndFeelUtil.getSmallFontSize());
	private static final int DEF_FONT_SIZE = (int) LookAndFeelUtil.getSmallFontSize();
	
	private static final Range<Integer> ROW_HEIGHT_RANGE = new ContinuousRange<>(Integer.class, 1, 800, true, true);
	private static final Range<Integer> NONE_ZERO_POSITIVE_INT_RANGE = new ContinuousRange<>(Integer.class, 1, Integer.MAX_VALUE, true, true);
	
	// Categories of VisualProperty ====================================================================================
	
	public static final VisualProperty<Visualizable> TABLE = new DefaultVisualizableVisualProperty("Table",
			"Table Visual Property", CyTable.class);
	
	public static final VisualProperty<Visualizable> ROW = new DefaultVisualizableVisualProperty("Row",
			"Row Visual Property", CyRow.class);
	
	public static final VisualProperty<Visualizable> COLUMN = new DefaultVisualizableVisualProperty("Column",
			"Column Visual Property", CyColumn.class);
	
	public static final VisualProperty<Visualizable> CELL = new DefaultVisualizableVisualProperty("Cell",
			"Cell Visual Property", CyColumn.class);
	
	// VPs that apply to the entire TABLE ==============================================================================
	
	public static final VisualProperty<TableMode> TABLE_VIEW_MODE = new TableModeVisualProperty(TableModeVisualProperty.AUTO, 
			"TABLE_VIEW_MODE", "View Mode", CyTable.class);
	
	public static final VisualProperty<Boolean> TABLE_ALTERNATE_ROW_COLORS = new BooleanVisualProperty(false, 
			"TABLE_ALTERNATE_ROW_COLORS", "Alternate Row Colors", CyTable.class);
	
	public static final VisualProperty<Boolean> TABLE_GRID_VISIBLE = new BooleanVisualProperty(false, 
			"TABLE_GRID_VISIBLE", "Table Grid Visible", CyTable.class);
	
	/**
	 * Can be used to set the same height to all rows in a table. It can be overridden by {@link #ROW_HEIGHT}.
	 */
	public static final VisualProperty<Integer> TABLE_ROW_HEIGHT = new RowHeightVisualProperty(ROW_HEIGHT_RANGE,
			"TABLE_ROW_HEIGHT", "Table Row Height", CyTable.class);
	
	// VPs that apply to ROWs ==========================================================================================
	
	/**
	 * Change the height of specific rows in a table, which means this property must be used when a table requires
	 * one or more rows to have a different height. When set, it overrides the value set to {@link #TABLE_ROW_HEIGHT}.
	 */
	public static final VisualProperty<Integer> ROW_HEIGHT = new RowHeightVisualProperty(ROW_HEIGHT_RANGE, "ROW_HEIGHT",
			"Row Height", CyRow.class);
	
	public static final VisualProperty<Boolean> ROW_SELECTED = new BooleanVisualProperty(false, "ROW_SELECTED",
			"Row Selected", CyRow.class);
	
	// VPs that apply to an entire COLUMN or to the column header ======================================================
	
	public static final VisualProperty<Boolean> COLUMN_EDITABLE = new BooleanVisualProperty(true, "COLUMN_EDITABLE",
			"Column Editable", CyColumn.class);

	public static final VisualProperty<Boolean> COLUMN_VISIBLE = new BooleanVisualProperty(true, "COLUMN_VISIBLE",
			"Column Visible", CyColumn.class);

	public static final VisualProperty<Boolean> COLUMN_SELECTED = new BooleanVisualProperty(false, "COLUMN_SELECTED",
			"Column Selected", CyColumn.class);
	
	public static final VisualProperty<Double> COLUMN_GRAVITY = new DoubleVisualProperty(1.0, 
			NONE_ZERO_POSITIVE_DOUBLE_RANGE, "COLUMN_GRAVITY", "Column Gravity", CyColumn.class);
	
	public static final VisualProperty<Integer> COLUMN_WIDTH = new IntegerVisualProperty(1,
			NONE_ZERO_POSITIVE_INT_RANGE, "COLUMN_WIDTH", "Column Width", CyColumn.class);
	
	public static final VisualProperty<CellFormat> COLUMN_FORMAT = new CellFormatVisualProperty(new CellFormat(""),
			"COLUMN_FORMAT", "Cell Number Format", CyColumn.class);
	
	public static final VisualProperty<Boolean> COLUMN_TEXT_WRAPPED = new BooleanVisualProperty(false, 
			"COLUMN_TEXT_WRAPPED", "Cell Text Wrapped", CyColumn.class);
	
	// VPs that apply to CELLs within columns ==========================================================================
	// (these show up in the vizmapper and can have mappings)
	
	public static final VisualProperty<Paint> CELL_BACKGROUND_PAINT = new PaintVisualProperty(UIManager.getColor("Table.background"),
			PAINT_RANGE, "CELL_BACKGROUND_PAINT", "Cell Background Paint", CyColumn.class);
	
	public static final VisualProperty<Font> CELL_FONT_FACE = new FontVisualProperty(DEF_FONT, 
			"CELL_FONT_FACE", "Cell Font Face", CyColumn.class);
	
	public static final VisualProperty<Integer> CELL_FONT_SIZE = new IntegerVisualProperty(DEF_FONT_SIZE,
			new ContinuousRange<>(Integer.class, 1, Integer.MAX_VALUE, true, true), "CELL_FONT_SIZE",
			"Cell Font Size", CyColumn.class);
	
	public static final VisualProperty<Paint> CELL_TEXT_COLOR = new PaintVisualProperty(UIManager.getColor("Table.foreground"),
			PAINT_RANGE, "CELL_TEXT_COLOR", "Cell Text Paint", CyColumn.class);
	
	public static final VisualProperty<String> CELL_TOOLTIP = new StringVisualProperty("",
			ARBITRARY_STRING_RANGE, "CELL_TOOLTIP", "Cell Tooltip", CyColumn.class);
	
	public BasicTableVisualLexicon(VisualProperty<NullDataType> root) {
		super(root);
	}
	
	@Override
	protected Class<?>[] getTypes() {
		return new Class<?>[] {
			CyTable.class,
			CyRow.class,
			CyColumn.class
		};
	}
	
	@Override
	protected void addVisualProperties(VisualProperty<NullDataType> root) {
		addVisualProperty(TABLE, root);
		
		addVisualProperty(ROW, TABLE);
		addVisualProperty(COLUMN, TABLE);
		addVisualProperty(CELL, TABLE);
		
		addVisualProperty(TABLE_VIEW_MODE, TABLE);
		addVisualProperty(TABLE_ALTERNATE_ROW_COLORS, TABLE);
		addVisualProperty(TABLE_GRID_VISIBLE, TABLE);
		addVisualProperty(TABLE_ROW_HEIGHT, TABLE);
		
		addVisualProperty(ROW_HEIGHT, ROW);
		addVisualProperty(ROW_SELECTED, ROW);
		
		addVisualProperty(COLUMN_EDITABLE, COLUMN);
		addVisualProperty(COLUMN_VISIBLE, COLUMN);
		addVisualProperty(COLUMN_SELECTED, COLUMN);
		addVisualProperty(COLUMN_GRAVITY, COLUMN);
		addVisualProperty(COLUMN_WIDTH, COLUMN);
		addVisualProperty(COLUMN_FORMAT, COLUMN);
		addVisualProperty(COLUMN_TEXT_WRAPPED, COLUMN);
		
		addVisualProperty(CELL_BACKGROUND_PAINT, CELL);
		addVisualProperty(CELL_FONT_FACE, CELL);
		addVisualProperty(CELL_FONT_SIZE, CELL);
		addVisualProperty(CELL_TEXT_COLOR, CELL);
		addVisualProperty(CELL_TOOLTIP, CELL);
	}
	
	/**
	 * The only reason for the existence of this class is that the default value must be retrieved
	 * from the current look and feel property "Table.rowHeight".
	 */
	private final static class RowHeightVisualProperty extends AbstractVisualProperty<Integer> {

		public RowHeightVisualProperty(
				Range<Integer> range,
				String id, 
				String displayName,
				Class<? extends CyIdentifiable> targetObjectDataType
		) {
			super(0, range, id, displayName, targetObjectDataType);
		}
		
		@Override
		public Integer getDefault() {
			return UIManager.getInt("Table.rowHeight");
		}
		
		@Override
		public String toSerializableString(Integer value) {
			try {
				return value.toString();
			} catch (ClassCastException ex) {
				System.err.println("ClassCast: " + value);
				ex.printStackTrace();
				return "";
			}
		}

		@Override
		public Integer parseSerializableString(String text) {
			// Cytoscape 2.x serializes integer attributes as decimals (e.g."1.0")!
			return Double.valueOf(text).intValue();
		}
	}
}
