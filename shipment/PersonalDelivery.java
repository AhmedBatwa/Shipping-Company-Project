package shipment;

import receiver.Receiver;
import sender.Sender;

public class PersonalDelivery extends Shipment{

	public PersonalDelivery(Sender sender, Receiver receiver) {
		super(new int[] {1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, sender, receiver);
		
	}
	
	

}
