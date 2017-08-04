package org.cytoscape.model.json;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

public interface CyJSONUtil {
	
	public String toJson(CyIdentifiable cyIdentifiable);

	public String toJson(Collection<CyIdentifiable> collection);
	
	public String toJson(CyNetwork network, CyNode cyNode);
	
	public String toJson(CyNetwork network, CyEdge cyEdge);
	
	public String toJson(CyNetwork cyNetwork);
	
	public String toJson(CyTable cyTable);
	
	public String toJson(CyColumn cyColumn);
	
	public String toJson(CyRow cyRow);
}
