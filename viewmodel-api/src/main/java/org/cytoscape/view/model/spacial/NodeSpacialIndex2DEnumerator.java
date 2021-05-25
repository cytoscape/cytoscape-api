package org.cytoscape.view.model.spacial;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.View;

/**
 * Enumerator returned when querying a {@link NetworkSpacialIndex2D}, used to iterate over the results of the query.
 * <br><br>
 * Example usage:
 * 
 * <pre>
 * NetworkSpacialIndex2D spacialIndex = networkViewSnapshot.getSpacialIndex2D();
 * NodeSpacialIndex2DEnumerator enumerator = spacialIndex.queryAllNodes(null);
 * float[] extents = new float[4];
 * while(enumerator.hasNext()) {
 *     // extents will contain the coordinates of the bottom left and top right corners of the rectangle
 *     View&lt;CyNode&gt; nodeView = enumerator.nextNodeExtents(extents);
 *     float bottomLeftX = extents[SpacialIndex2D.X_MIN];
 *     float bottomLeftY = extents[SpacialIndex2D.Y_MIN];
 *     float topRightX = extents[SpacialIndex2D.X_MAX];
 *     float topRightY = extents[SpacialIndex2D.Y_MAX];
 * }
 * </pre>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface NodeSpacialIndex2DEnumerator extends SpacialIndex2DEnumerator<Long> {

	/**
	 * Returns the next node.
	 */
	public View<CyNode> nextNode();
	
	/**
	 * Returns the next node.
	 * 
	 * @param extents Output parameter that will be loaded with the extents of the node. 
	 * Must have size at least 4. May be null.
	 * 
	 * @see SpacialIndex2D#X_MIN
	 * @see SpacialIndex2D#X_MAX
	 * @see SpacialIndex2D#Y_MIN
	 * @see SpacialIndex2D#Y_MAX
	 */
	public View<CyNode> nextNodeExtents(float[] extents);
	
}
