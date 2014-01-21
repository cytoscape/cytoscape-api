/**
 * Provides interfaces and classes for defining {@link org.cytoscape.filter.model.Transformer}s and
 * {@link org.cytoscape.filter.model.Filter}s.  Transformations can be applied to a stream of elements
 * typically provided by a {@link org.cytoscape.filter.model.TransformerSource}.  The output of a
 * transformation is collected into a {@link org.cytoscape.filter.model.TransformerSink}.  There are three
 * main types of {@link org.cytoscape.filter.model.Transformer}s, listed in order of increasing generality:
 * 
 * <dl>
 * <dt>{@link org.cytoscape.filter.model.Filter}</dt>
 * <dd>This transformer narrows an element stream by deciding whether or not the
 * element makes it to the sink.  The binary nature of this transformation allows
 * users to compose multiple filters together into a {@link org.cytoscape.filter.model.CompositeFilter},
 * which combines their result using Boolean logic.  In the Cytoscape GUI, these
 * transformers appear in the "Filter" section of the "Select" tab.
 * </dd>
 *
 * <dt>{@link org.cytoscape.filter.model.ElementTransformer}</dt>
 * <dd>This transformer takes a single element from a specific context as
 * input and decides what elements from that same context make it to the sink.
 * These may or may not include the original element.  In the Cytoscape GUI,
 * these transformers appear in the "Chain" section of the "Select" tab.</dd>
 *
 * <dt>{@link org.cytoscape.filter.model.HolisticTransformer}</dt>
 * <dd>This transformer takes multiple elements from a specific context as input
 * and decides what elements from that same context make it to the sink.
 * These may or may not include the original element.  In the Cytoscape GUI,
 * these transformers appear in the "Chain" section of the "Select" tab.</dd>
 * 
 * </dl>
 * 
 * To define a custom {@link org.cytoscape.filter.model.Transformer}, extend {@link org.cytoscape.filter.model.AbstractTransformer}
 * and implement one of the three interfaces listed above.  Finally, create
 * another class that implements either {@link org.cytoscape.filter.model.FilterFactory},
 * {@link org.cytoscape.filter.model.ElementTransformerFactory}, or {@link org.cytoscape.filter.model.HolisticTransformerFactory},
 * which creates instances of your custom transformer.  Finally, register that
 * factory as an OSGi service.
 * 
 * If your transformer is parameterized, declare the parameters as get/set
 * method pairs.  Use the {@link org.cytoscape.work.Tunable} annotation on the
 * get method to allow clients of your code to adjust the parameters using
 * {@link org.cytoscape.work.TunableSetter} without having to expose any API.
 * When the set method is called, your implementation should
 * notify all registered {@link org.cytoscape.filter.model.TransformerListener}s of the change in
 * parameters (e.g. by calling {@link org.cytoscape.filter.model.AbstractTransformer#notifyListeners()}.
 * 
 * To define a user interface for your transformer, see the {@link org.cytoscape.filter.view view package}.
 */
package org.cytoscape.filter.model;
