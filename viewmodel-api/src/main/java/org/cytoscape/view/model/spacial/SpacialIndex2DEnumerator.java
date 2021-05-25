package org.cytoscape.view.model.spacial;

/**
 * Enumerator returned when querying a {@link SpacialIndex2D}, used to iterate over the results of the query.
 * <br><br>
 * Example usage:
 * 
 * <pre>

 * SpacialIndex2D spacialIndex = networkViewSnapshot.getSpacialIndex2D();
 * SpacialIndex2DEnumerator&lt;Long&gt; enumerator = spacialIndex.queryAll();
 * float[] extents = new float[4];
 * while(enumerator.hasNext()) {
 *     // extents will contain the coordinates of the bottom left and top right corners of the rectangle
 *     Long suid = enumerator.nextExtents(extents);
 *     float bottomLeftX = extents[SpacialIndex2D.X_MIN];
 *     float bottomLeftY = extents[SpacialIndex2D.Y_MIN];
 *     float topRightX = extents[SpacialIndex2D.X_MAX];
 *     float topRightY = extents[SpacialIndex2D.Y_MAX];
 *     // suid can be used to look up the View<CyNode> from CyNetworkView or CyNetworkViewSnapshot
 *     View&lt;CyNode&gt; nodeView = networkViewSnapshot.getNodeView(suid);
 * }
 * </pre>
 * 
 * @param <K> The key type, inherited from the SpacialIndex2D
 * 
 * @see SpacialIndex2D#queryOverlap(float, float, float, float)
 * @see SpacialIndex2D#queryAll()
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.8
 */
public interface SpacialIndex2DEnumerator<K> {

	/**
	 * Number of elements to be returned by the enumerator.
	 */
	int size();

	/**
	 * Returns true if this enumerator contains more elements.
	 */
	boolean hasNext();

	/**
	 * Returns then next element.
	 * 
	 * @param extents Output parameter that will be loaded with the extents of the 2D object. 
	 * Must have size at least 4. May be null.
	 * 
	 * @return The key associated with the 2D object.
	 * 
	 * @see SpacialIndex2D#X_MIN
	 * @see SpacialIndex2D#X_MAX
	 * @see SpacialIndex2D#Y_MIN
	 * @see SpacialIndex2D#Y_MAX
	 */
	K nextExtents(float[] extents);
	
	/**
	 * Returns then next element.
	 * 
	 * @return The key associated with the 2D object.
	 */
	default K next() {
		return nextExtents(null);
	}
	
}
