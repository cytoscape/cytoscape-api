package org.cytoscape.util.swing;

import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.events.NetworkAddedEvent;
import org.cytoscape.model.events.NetworkDestroyedEvent;
import org.cytoscape.model.events.NetworkDestroyedListener;
import org.cytoscape.model.events.NetworkAddedListener;
import javax.swing.DefaultComboBoxModel;

public class NetworkSelectorPanel extends JPanel implements NetworkAddedListener, NetworkDestroyedListener
{
	private static final long serialVersionUID = 8694272457769377810L;
	
	protected final JComboBox networkComboBox;
	private CyNetworkManager cyNetworkManager;
	private CyApplicationManager cyApplicationManager;

	public NetworkSelectorPanel(CyApplicationManager cyApplicationManager, CyNetworkManager cyNetworkManager) {
		super();
		this.setLayout(new BorderLayout());
		networkComboBox = new JComboBox();

		this.cyNetworkManager = cyNetworkManager;
		this.cyApplicationManager = cyApplicationManager;
		
		//This should help to limit the length of combobox if the network name is too long
		networkComboBox.setPreferredSize(new java.awt.Dimension(networkComboBox.getPreferredSize().width, 
				networkComboBox.getPreferredSize().height));

		add(networkComboBox, BorderLayout.CENTER);
		updateNetworkList();
	}
	
	/**
	 * If selected, return selected network.
	 * Otherwise, return null.
	 * 
	 * @return
	 */
	public CyNetwork getSelectedNetwork() {
		for (CyNetwork net : this.cyNetworkManager.getNetworkSet()) {
			String networkTitle = net.getCyRow().get("name", String.class);
			if (networkTitle.equals(networkComboBox.getSelectedItem()))
				return net;
		}
		
		return null;
	}

	private void updateNetworkList() {
		final Set<CyNetwork> networks = this.cyNetworkManager.getNetworkSet();
		final SortedSet<String> networkNames = new TreeSet<String>();

		for (CyNetwork net : networks)
			networkNames.add(net.getCyRow().get("name", String.class));

		// Clear the comboBox
		networkComboBox.setModel(new DefaultComboBoxModel());

		for (String name : networkNames)
			networkComboBox.addItem(name);

		if (this.cyApplicationManager.getCurrentNetwork() != null){
			String networkTitle = this.cyApplicationManager.getCurrentNetwork().getCyRow().get("name", String.class);
			networkComboBox.setSelectedItem(networkTitle);			
		}
	}

//	public void propertyChange(PropertyChangeEvent evt) {
//
//		final String propName = evt.getPropertyName();
//		
//		if (propName.equals(Cytoscape.NETWORK_CREATED)||propName.equals(Cytoscape.NETWORK_TITLE_MODIFIED)){
//			updateNetworkList();
//		}
//		else if (propName.equals(Cytoscape.NETWORK_DESTROYED))
//			networkComboBox.removeItem(Cytoscape.getNetwork(
//					(String) evt.getNewValue()).getTitle());
//
//	}
	
	public void handleEvent(NetworkAddedEvent e){
		updateNetworkList();
	}

	public void handleEvent(NetworkDestroyedEvent e){
		updateNetworkList();
	}
	
	/**
	 *  Installs a new item listener for the embedded combo box.
	 */
	public void addItemListener(final ItemListener newListener) {
		networkComboBox.addItemListener(newListener);
	}
	
	public JComboBox getJCombobox(){
		return this.networkComboBox;
	}
}
