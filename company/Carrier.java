package company;

import shipment.Shipment;

public class Carrier {
private Shipment[][] assignedShipments;
private int[] workingHours;          //24 hour array and 2d array because the 2nd array is for 1/3 of the hours like hour 10.20
enum Shift {
	MORNING_SHIFT,EVENING_SHIFT,NIGHT_SHIFT
}

public Carrier(Shift shift) {
	
}


public Shipment[][] getAssignedShipments() {
	return assignedShipments;
}


public int[] getWorkingHours() {
	return workingHours;
}


public void deliver(int hours) {
	
	/*TODO
	 * loops over the assigned shipments and calls .deliver method
	 */
	
}

public void assignShipment(Shipment shipment) {
	/*TODO
	   *element by element multiplication for prefieredDeliveryTime for(shipment,receiver,carrierWorkingHours)
       *the result is the best suit delivery time
	   *check if the best suit time not equipped in assignedShipment array of this carrier
	   *return T/F
	 */
	
	

}

     
}
