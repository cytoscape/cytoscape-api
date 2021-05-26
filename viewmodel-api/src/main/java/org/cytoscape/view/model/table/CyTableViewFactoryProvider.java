package org.cytoscape.view.model.table;

import org.cytoscape.view.model.VisualLexicon;


/**
 * <p>
 * A singleton factory that is used to create {@link CyTableViewFactory} objects. 
 * </p>
 * <p>
 * Apps that contribute renderers (via TableViewRenderer) may provide their own
 * implementation of {@link CyTableViewFactory}, or they may acquire a reusable 
 * instance of {@link CyTableViewFactory} from this service. The {@link CyTableViewFactory}
 * provided by this service may be configured using a provided {@link VisualLexicon}.
 * </p>
 * <p>
 * The CyTableViewFactoryProvider is available as an OSGi service.
 * </p>
 * 
 * <p>
 * Example of creating a CyTableViewFactory for a standard renderer:
 * <pre>
 * CyTableViewViewFactoryProvider provider = getService(bc, CyTableViewViewFactoryProvider.class);
 * CyTableViewFactory viewFactory = provider.createTableViewFactory(myLexicon, MyRenderer.ID);
 * </pre>
 * </p>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 * 
 * @since 3.9
 */
public interface CyTableViewFactoryProvider {

	/**
	 * Returns a CyTableViewFactory for the given lexicon and with the given ID.
	 */
	CyTableViewFactory createTableViewFactory(VisualLexicon lexicon, String rendererID);
	
}
