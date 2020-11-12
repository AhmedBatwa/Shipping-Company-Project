package company;

import java.util.ArrayList;

import shipment.Shipment;

public class Carrier {
private Shipment[][] assignedShipments;       
private int[] workingHours;          
private int hourlyDelivered;
private int hourlyFailed;




enum Shift {
	MORNING_SHIFT(8,16),EVENING_SHIFT(16,24),NIGHT_SHIFT(0,8);
	private int startHour,endHour;
	
	private Shift(int startHour,int endHour) {
		this.startHour=startHour;
		this.endHour=endHour;
	}

	public int getStartHour() {
		return startHour;
	}
	public int getEndHour() {
		return endHour;
	}
}




public Carrier(Shift shift) {
	assignedShipments = new Shipment[24][3];
	initWorkingHours(shift);
	
}




public Shipment[][] getAssignedShipments() {
	return assignedShipments;
}





public int[] getWorkingHours() {
	return workingHours;
}




public ArrayList<Shipment> deliver(int hour) {
	
	/*
	 * loops over the assigned shipments and calls .deliver method
	 */
	
	/*
	 * time (hour,min)   min=i*20; using a pre-defined assumption
	 * a carrier can deliver 3 shipments per hour thus each shipment require(20 min) as average
     */
	
	ArrayList<Shipment> updatedShipments = new ArrayList<>();
	//init counters to start a new hourly report
	hourlyDelivered=0;
	hourlyFailed=0;
	
	for(int i=0; i<assignedShipments[hour].length;i++) {
		
		if(assignedShipments[hour][i]!=null) {
			
		if(assignedShipments[hour][i].deliver(hour, i*20)) {
			hourlyDelivered++;
		}else {
			hourlyFailed++;
		}
		updatedShipments.add(assignedShipments[hour][i]);
		}
		
		
		
	}
	
	return updatedShipments;
	
	
}



public int[] getHourlyReport() {
	return new int[] {hourlyDelivered,hourlyFailed};
}


public boolean assignShipment(Shipment shipment,int currentHour,int simulatedPhase) {
	
	
	
	/*
	   *element by element multiplication for prefieredDeliveryTime for(shipment,receiver,carrierWorkingHours)
       *the result is the best suit delivery time
	   *check if the best suit time not equipped in assignedShipment array of this carrier
	   *return T/F
	 */
	
	
	
	//================================================================[Phase1]=========================================================================
	//a carrier will accept the shipment as long as it is in the working hours for the carrier , and he has empty slots in assigned shipments
	
	
	
	
	if(simulatedPhase==1) {
		for(int hour = currentHour;hour<workingHours.length;hour++) {
			if(workingHours[hour]==1) {         
				for(int min=0;min<assignedShipments[hour].length;min++) {
					if(assignedShipments[hour][min]==null) {       //find avalible time slot in the carrier's assigned shipments 
						assignedShipments[hour][min]=shipment;
						shipment.setRegisteredDeliveryTime(new int[] {hour,min});
						shipment.setCarrier(this);						
						return true;
					}
				}
			}
		}
	}
	
	
	
	
	//================================================================[Phase2]=========================================================================
	//interescts the preffered delivery times for shipment & recevier & Working hours for the carrier
	
	
	
	
	else if(simulatedPhase==2) {
		
		//get shipment preffered Deleivery Time & the reciever preffered time
		int[] shipmentPrefferedTime= shipment.getPrefferedDeliveryTime();
		int[] receiverPrefferedTime= shipment.getReciever().getPreferredDeliveryTime();
		
		
		for(int hour = currentHour;hour<workingHours.length;hour++) {
			
			//finds the intersection between the shipment & reciever preffered times & the Working hours of the carrier
			if(workingHours[hour]==1 && shipmentPrefferedTime[hour]==1  && receiverPrefferedTime[hour]==1) {         
				for(int min=0;min<assignedShipments[hour].length;min++) {
					if(assignedShipments[hour][min]==null) {       //find avalible time slot in the carrier's assigned shipments 
						assignedShipments[hour][min]=shipment;
						shipment.setRegisteredDeliveryTime(new int[] {hour,min});
						shipment.setCarrier(this);


						return true;
					}
				}
			}
		}
	}
	
	
	
	return false;
	

}

public void dailyCleanUp() {
	assignedShipments=new Shipment[24][3];
}


//drop normal through force assignment
public void dropShipment(Shipment shipment) {
	
	//takes the registeredDeliveryTime  {coulmn,row}={hour,min/20} and drop the shipment from this carrier
	int assignedCoulmn = shipment.getRegisteredDeliveryTime()[0];
	int assignedRow=shipment.getRegisteredDeliveryTime()[1];
	assignedShipments[assignedCoulmn][assignedRow]=null;
	
	
}






private void initWorkingHours(Shift shift) {
		workingHours=new int[24];
		for(int i =shift.startHour;i<shift.endHour;i++) {
			workingHours[i]=1;
		}
}
}
