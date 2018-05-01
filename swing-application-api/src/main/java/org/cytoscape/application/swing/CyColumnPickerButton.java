package org.cytoscape.application.swing;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.util.swing.DropDownMenuButton;
import org.cytoscape.util.swing.IconManager;

@SuppressWarnings("serial")
public class CyColumnPickerButton extends DropDownMenuButton {

	
	public static final String PROPERTY_SELECTED_COLUMNS = "selectedColumns";
	
	
	public CyColumnPickerButton(
			IconManager iconManager, CyColumnPresentationManager columnPresentationManager, 
			Collection<CyColumn> columns, Collection<String> selectedColumnNames) {
		
//		setText("HEllo");
//		
//		CyColumnPicker columnPicker = new CyColumnPicker(iconManager, columnPresentationManager, columns, selectedColumnNames);
//		
//		JPopupMenu columnSelectorPopupMenu = new JPopupMenu();
//		columnSelectorPopupMenu.add(columnPicker);
//		
//		
//		columnSelectorPopupMenu.addPopupMenuListener(new PopupMenuListener() {
//			
//			Collection<String> prevSelectedNames;
//			
//			@Override
//			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
//				Collection<String> selectedNames = columnPicker.getSelectedColumnNames();
//				firePropertyChange(PROPERTY_SELECTED_COLUMNS, prevSelectedNames, selectedNames);
//				prevSelectedNames = null;
//			}
//			
//			@Override
//			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//				prevSelectedNames = columnPicker.getSelectedColumnNames();
//			}
//			@Override
//			public void popupMenuCanceled(PopupMenuEvent e) {
//				
//			}
//		});
//		
//		
//		columnSelectorPopupMenu.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (SwingUtilities.isRightMouseButton(e)) {
//					columnSelectorPopupMenu.setVisible(false);
//				}
//			}
//		});
	}
	
	
}
