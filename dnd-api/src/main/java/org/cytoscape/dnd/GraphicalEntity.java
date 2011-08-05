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
	 * get the Title of the graphical entity
	 * @return the Title
	 */
	public String getTitle();

	/**
	 * retrieves the source of the drag operation, used when dragging a graphical entity from the palette onto
	 * the canvas
	 * @return the drag source
	 */
	public DragSource getMyDragSource();

	/**
	 * get the image for the icon used on the palette to represent the graphical entity
	 * @return the image
	 */
	public Image getImage();

	/**
	 * get the icon used on the palette to represent the graphical entity
	 * @return the icon used on the palette to represent the graphical entity
	 */
	public Icon getIcon();

	/**
	 * returns the name of the attribute associated with the Graphical Entity.
	 * This is used to determine whether a Node or an Edge has been dropped on the canvas.
	 * This attribute will also be set for the CyNode or CyEdge created as a result of the drop operation.
	 * @return the attribute name
	 */
	public String getAttributeName();

	/**
	 * returns the value of the attribute associated with the Graphical Entity.
	 * This attribute will be set for the CyNode or CyEdge created as a result of the drop operation.
	 * @return the attribute value
	 */
	public String getAttributeValue();
}
