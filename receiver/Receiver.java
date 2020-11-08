package receiver;

import java.util.ArrayList;

import shipment.Shipment;
import shipment.Status;

public abstract class Receiver {
	
	private ArrayList<Shipment> shipments;
	private int[] preferredDeliveryTime;
	public Receiver(int[] preferredDeliveryTime) {
		shipments = new ArrayList<Shipment>();
		this.preferredDeliveryTime=preferredDeliveryTime;
	}
	public boolean Receive(Shipment shipment) {
		boolean bool=false;
		for (int i=0;i<=getPreferredDeliveryTime().length;i++) {
			if(shipment.getPrefferedDeliveryTime()[i]==getPreferredDeliveryTime()[i]) {
				shipment.setStatus(Status.DELIVERED);
				bool=true;
				break;
			}
			else{
				shipment.setStatus(Status.DELIVERY_FAILED);
				bool=false;
			}	
			}
		return bool;
		}
	public ArrayList<Shipment> getShipments() {
		return shipments;
	}

	public void addShipment(Shipment shipment) {
		shipments.add(shipment);
	}

	public int[] getPreferredDeliveryTime() {
		return preferredDeliveryTime;
	}

	
	
}


