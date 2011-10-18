package cytoscape.util.swing;


import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;


public class NetworkSelectorPanel extends JPanel implements
		PropertyChangeListener
{
	private static final long serialVersionUID = 8694272457769377810L;
	
	protected final JComboBox networkComboBox;

	public NetworkSelectorPanel() {
		super();
		this.setLayout(new BorderLayout());
		networkComboBox = new JComboBox();

		//This should help to limit the length of combobox if the network name is too long
		networkComboBox.setPreferredSize(new java.awt.Dimension(networkComboBox.getPreferredSize().width, 
				networkComboBox.getPreferredSize().height));

		add(networkComboBox, BorderLayout.CENTER);
		updateNetworkList();
		
		Cytoscape.getPropertyChangeSupport().addPropertyChangeListener(this);
	}
	
	/**
	 * If selected, return selected network.
	 * Otherwise, return null.
	 * 
	 * @return
	 */
	public CyNetwork getSelectedNetwork() {
		for (CyNetwork net : Cytoscape.getNetworkSet()) {
			if (net.getTitle().equals(networkComboBox.getSelectedItem()))
				return net;
		}
		
		return null;
	}

	private void updateNetworkList() {
		final Set<CyNetwork> networks = Cytoscape.getNetworkSet();
		final SortedSet<String> networkNames = new TreeSet<String>();

		for (CyNetwork net : networks)
			networkNames.add(net.getTitle());

		networkComboBox.removeAllItems();
		for (String name : networkNames)
			networkComboBox.addItem(name);

		networkComboBox.setSelectedItem(Cytoscape.getCurrentNetwork()
				.getTitle());
	}

	public void propertyChange(PropertyChangeEvent evt) {

		final String propName = evt.getPropertyName();
		
		if (propName.equals(Cytoscape.NETWORK_CREATED)||propName.equals(Cytoscape.NETWORK_TITLE_MODIFIED)){
			updateNetworkList();
		}
		else if (propName.equals(Cytoscape.NETWORK_DESTROYED))
			networkComboBox.removeItem(Cytoscape.getNetwork(
					(String) evt.getNewValue()).getTitle());

	}

	/**
	 *  Installs a new item listener for the embedded combo box.
	 */
	public void addItemListener(final ItemListener newListener) {
		networkComboBox.addItemListener(newListener);
	}
}
