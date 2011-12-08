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

/**
 * A convenience JPanel for selecting networks.
 * @CyAPI.Final.Class 
 */
public final class NetworkSelectorPanel extends JPanel implements NetworkAddedListener, NetworkDestroyedListener
{
	private static final long serialVersionUID = 8694272457769377810L;
	
	protected final JComboBox networkComboBox;
	private CyNetworkManager cyNetworkManager;
	private CyApplicationManager cyApplicationManager;

	/**
	 * Constructor.
	 * @param cyApplicationManager The application manager used for tracking the current network.
	 * @param cyNetworkManager The network manager used for accessing all available networks.
	 */
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
	 * @return The network that was selected.
	 */
	public CyNetwork getSelectedNetwork() {
		for (CyNetwork net : this.cyNetworkManager.getNetworkSet()) {
			String networkTitle = net.getCyRow(net).get("name", String.class);
			if (networkTitle.equals(networkComboBox.getSelectedItem()))
				return net;
		}
		
		return null;
	}

	private void updateNetworkList() {
		final Set<CyNetwork> networks = this.cyNetworkManager.getNetworkSet();
		final SortedSet<String> networkNames = new TreeSet<String>();

		for (CyNetwork net : networks)
			networkNames.add(net.getCyRow(net).get("name", String.class));

		// Clear the comboBox
		networkComboBox.setModel(new DefaultComboBoxModel());

		for (String name : networkNames)
			networkComboBox.addItem(name);

		CyNetwork currNetwork = this.cyApplicationManager.getCurrentNetwork();
		if (currNetwork != null) {
			String networkTitle = currNetwork.getCyRow(currNetwork).get("name", String.class);
			networkComboBox.setSelectedItem(networkTitle);			
		}
	}

	/**
	 * Updates the list based on network added events.
	 * @param e The network added event.
	 */
	public void handleEvent(NetworkAddedEvent e){
		updateNetworkList();
	}

	/**
	 * Updates the list based on network destroyed events.
	 * @param e The network destroyed event.
	 */
	public void handleEvent(NetworkDestroyedEvent e){
		updateNetworkList();
	}
	
	/**
	 * Installs a new item listener for the embedded combo box.
	 * @param newListener The new item listener to be added.
	 */
	public void addItemListener(final ItemListener newListener) {
		networkComboBox.addItemListener(newListener);
	}

	/**
	 * Returns the network combobox.
	 * @return The network combobox.
	 */
	public JComboBox getJCombobox(){
		return this.networkComboBox;
	}
}
