package org.cytoscape.dnd;

import java.awt.Image;
import java.awt.dnd.DragSource;
import javax.swing.Icon;


/**
 *
 * Interface for defining draggable/droppable visual components in the
 * Cytoscape editor framework.  The framework provides for dragging and dropping graphical
 * entities from palette onto the canvas.   Graphical entities are associated with
 * semantic objects, i.e. nodes and edges, that are created when the graphical entities
 * are dropped onto the canvas.
 *
 * @author Allan Kuchinsky, Agilent Technologies
 */
public interface GraphicalEntity {

	/**
	 * Get the Title of this GraphicalEntity.
	 * @return the Title of this GraphicalEntity.
	 */
	public String getTitle();

	/**
	 * Retrieves the source of the drag operation, used when dragging a GraphicalEntity from the palette onto
	 * the canvas.
	 * @return the the source of the drag operation, used when dragging a GraphicalEntity from the palette onto
	 * the canvas.
	 */
	public DragSource getMyDragSource();

	/**
	 * Get the image for the icon used on the palette to represent the GraphicalEntity.
	 * @return the image for the icon used on the palette to represent the GraphicalEntity.
	 */
	public Image getImage();

	/**
	 * Get the icon used on the palette to represent this GraphicalEntity.
	 * @return the icon used on the palette to represent this GraphicalEntity.
	 */
	public Icon getIcon();

	/**
	 * Returns the name of the attribute associated with this GraphicalEntity.
	 * This is used to determine whether a Node or an Edge has been dropped on the canvas.
	 * This attribute will also be set for the CyNode or CyEdge created as a result of the drop operation.
	 * @return the name of the attribute associated with this GraphicalEntity.
	 */
	public String getAttributeName();

	/**
	 * Returns the value of the attribute associated with this GraphicalEntity.
	 * This attribute will be set for the CyNode or CyEdge created as a result of the drop operation.
	 * @return the value of the attribute associated with this GraphicalEntity.
	 */
	public String getAttributeValue();

	/**
	 * Returns descriptive text for this GraphicalEntity.  May be used as tool tip text.
	 * @return descriptive text for this GraphicalEntity.
	 */
	public String getDescription();
}
