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
	
	
	public boolean receive(Shipment shipment) {
		
		boolean isShipmentPrefferedTime=shipment.getPrefferedDeliveryTime()[shipment.getRegisteredDeliveryTime()[0]]==1;
		boolean isReceiverPrefferedTime=getPreferredDeliveryTime()[shipment.getRegisteredDeliveryTime()[0]]==1;
		
		if( isShipmentPrefferedTime && isReceiverPrefferedTime ) {
			return true;
		}
		
		
		return false;
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


