package company;

import receiver.Receiver;
import sender.Sender;
import shipment.Shipment;
import shipment.Undelayable;

public class ShippingCompany {
	
	private Carrier[] carriers;
	private Shipment[] shipments;
	private Sender[] senders;
	private Receiver[] receives;

	
	public static void main(String args[]) {
		//simulation of the two phases
		simulatePhase1();
		simulatePhase2();
	}
	
	private static void simulatePhase1() {
		/*TODO
		 * loop to generate carriers ,loop shipments,assign to carriers directly
		 */	
	}
	
	private static void simulatePhase2() {
		/*TODO
		 * generate carriers through the method generateCarriers()
		 * generateShipments  
		 * startDelivering
		 */
	}
	private static boolean receive(int a) {
		//random receive for phase 1 
		return false;
		
	}
	private static boolean receive(Shipment shipment , Sender sender,Receiver reciever) {
		 
		/*TODO
		 * Receives the shipment from the sender
		 * will pass the shipment to assignToCarrier 
		 * if succeed -> add to arrays otherwise->false
		 */
		return false;
	}
	
	
	private static boolean assignToCarrier(Shipment shipment) {
		
		/*TODO
		 * loops through all the carriers call carrier.assignShipment see if accepted
		 * returns true if carrier accepts shipment
		 * if undelayable hasn't been assigned call forceAssign 
		 */
		return false;
	}
	
	//puts the undelayable shipment in place of a normal shipment
	private static boolean forceAssignToCarrier(Undelayable shipment) {
		
		
		/*TODO
		 * loop through the shipments array to find a normal shipment at the preferred time of the undelayable shipment
		 * then access its carrier + registeredDeliveryTime 
		 * remove it from shipment array , carrier assignedShipment 
		 * assign this shipment to the carrier, and add it shipments array , sender ,receiver details
		 */
		return false;
	}

	private static boolean generateCarries(int numberOfCarriers) {
		/*TODO
		*loop to generate carrier
		*/
		return false;
	}
	
	private static boolean generateShipments(int numberOfShipments) {
		/*TODO
		*loop to receive(  shipment ,  sender, receiver)
		*/
		return false;
	}
	
	private static void startDelivering() {
		
		/*TODO
		*loop calls deliver(hour)
		*/
		
	}
	
	private static void deliver(int hour) {
		
		/*TODO
		*loop through all the carriers array and call method deliver(hour) from carrier class
		*/
		
	}
	
	
	
	
	
	
}













