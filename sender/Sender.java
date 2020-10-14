package sender;

import java.util.ArrayList;

import shipment.Shipment;
public abstract class Sender {
	ArrayList<Shipment> shipments= new ArrayList<Shipment>();
	
	
	public void addShipment(Shipment shipment) {
		shipments.add(shipment);
	}
	
}